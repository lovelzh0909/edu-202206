package com.example.demo.controller;


import java.time.LocalDate;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.*;
import com.example.demo.service.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/showpaper")
@Slf4j
public class ShowpaperController {
    @Autowired
    PapersService papersService;
    @Autowired
    QuestionService questionService;
    @Autowired
    TestService testService;
    @Autowired
    PaperJustifyService paperJustifyService;
    @Autowired
    TestRelStudentService testRelStudentService;
    @Autowired
    ScoreService scoreService;
    @Autowired
    QuestionRelScoreService QuestionRelScoreService;

    @PostMapping("/getQuestion/bytestId")
    public CommonReturnType getStudentvideo(@RequestParam Integer testId) {
        Test t = testService.getById(testId);
        if(t==null){
            return CommonReturnType.create(null,"没有该考试");
        }
        Papers p = papersService.getById(t.getPaperId());
        if(p==null){
            return CommonReturnType.create(null,"没有该考试试卷");
        }
        List<String> qs = stringToList(p.getPapercontext());
        List<Question> q = new ArrayList<>();
        for (String s : qs) {
            Question question = questionService.getById(Integer.valueOf(s));
            if (question == null) {
                return CommonReturnType.create(null, "某题目已被删除");
            }
            question.setAnswer(null);
            QuestionRelScore QuestionRelScore = QuestionRelScoreService.getOne(new QueryWrapper<QuestionRelScore>().eq("question_id", question.getId()).eq("paper_id", p.getPaperId()));
            question.setScore(QuestionRelScore.getScore());
            q.add(question);
        }
        Collections.shuffle(q);
        if (q.size() == 0) {
            return CommonReturnType.create("没有找到该试卷");
        }
        return CommonReturnType.create(q);
    }

    @PostMapping("/getQuestion/bytestId/grant")
    public CommonReturnType getpaperforteachergrant(@RequestParam Integer testId) {
        Test t = testService.getById(testId);
        Papers p = papersService.getById(t.getPaperId());
        List<String> qs = stringToList(p.getPapercontext());
        List<Question> q = new ArrayList<>();
        Map<String, Object> m = new HashMap<>();
        List<TestRelStudent> TestRelStudent = testRelStudentService.list(new QueryWrapper<TestRelStudent>()
                .eq("test_id", testId).eq("status", 3));
        if (TestRelStudent.size() == 0) {
            testService.update(new UpdateWrapper<Test>().set("test_status", 4).eq("test_id", testId));
            return CommonReturnType.create(null, "没有待批阅学生");
        }
        for (String s : qs) {
            m.put("student_phone", TestRelStudent.get(0).getStudentPhone());
            Question tempq = questionService.getById(Integer.valueOf(s));
            if(tempq==null){
                return CommonReturnType.create(null, "该题目已经被删除");
            }
            tempq.setScore(QuestionRelScoreService.getOne(new QueryWrapper<QuestionRelScore>().eq("question_id", tempq.getId()).eq("paper_id", t.getPaperId())).getScore());
            log.info(String.valueOf(tempq.getId()));
            PaperJustify paperJustify = paperJustifyService.getOne(new QueryWrapper<PaperJustify>()
                    .eq("test_id", testId)
                    .eq("student_phone", TestRelStudent.get(0).getStudentPhone())
                    .eq("question_id", tempq.getId()));
            if(paperJustify==null){
                tempq.setGetScore(0.0);
                tempq.setStudentAnswer("");
            }
            else {
                tempq.setGetScore(paperJustify.getScore());
                tempq.setStudentAnswer(paperJustify.getExmaineAnswer());
            }
//            if (paperJustify == null) {
//                return CommonReturnType.create(null, "没有该测试的答卷信息");
//            }
            q.add(tempq);
        }
        log.info("-----------------log---------------");
        log.info(q.toString());
        if (q.size() == 0) {
            return CommonReturnType.create("没有找到该试卷");
        }
        return CommonReturnType.create(q, m);
    }

    @PostMapping("/getQuestion/bypaperId")
    public CommonReturnType getQuestion(@RequestParam Integer paperId) {
        Papers p = papersService.getById(paperId);
        List<String> qs = stringToList(p.getPapercontext());
        List<Question> q = new ArrayList<>();
        for (String s : qs) {
            Question question = questionService.getById(Integer.valueOf(s));
            if (question == null) {
                return CommonReturnType.create(null, "某题目已被删除");
            }
            QuestionRelScore QuestionRelScore = QuestionRelScoreService.getOne(new QueryWrapper<QuestionRelScore>().eq("question_id", question.getId()).eq("paper_id", p.getPaperId()));
            question.setScore(QuestionRelScore.getScore());
            q.add(question);
        }
        for (Question qq : q) {
            qq.setScore(QuestionRelScoreService.getOne(new QueryWrapper<QuestionRelScore>().eq("question_id", qq.getId()).eq("paper_id", paperId)).getScore());
        }

        if (q.size() == 0) {
            return CommonReturnType.create("没有找到该试卷");
        }
        return CommonReturnType.create(q);
    }


    @PostMapping("/getpaper/bytestId")
    public CommonReturnType getStudentpaper(@RequestParam Integer testId) {
        Test t = testService.getById(testId);
        Papers p = papersService.getById(t.getPaperId());
        if (p == null) {
            return CommonReturnType.create("没有找到该试卷");
        }
        return CommonReturnType.create(p);
    }

    @PostMapping("/marking/finish/{studentphone}/{teacherPhone}/{testId}")
    public CommonReturnType savescore(@RequestBody List<Question> questionList, @PathVariable String studentphone, @PathVariable Integer testId, @PathVariable String teacherPhone) {
        Double sum = 0.0;
        for (Question q : questionList) {
            PaperJustify paperJustify = paperJustifyService.getOne(new QueryWrapper<PaperJustify>().eq("student_phone", studentphone).eq("test_id", testId)
                    .eq("question_id", q.getId()));
            if (paperJustify == null) {
                return CommonReturnType.create("没找到该学生某题目答案");
            }
            sum += paperJustify.setScore(q.getGetScore()).getScore();
        }
//        TestRelStudent stu = TestRelStudentService.getOne(new QueryWrapper<TestRelStudent>().eq("student_phone", studentphone).eq("test_id", testId));
//        stu.setStatus(3);
        testRelStudentService.update(new UpdateWrapper<TestRelStudent>().set("status", 4).eq("student_phone", studentphone).eq("test_id", testId));
        Test t = testService.getById(testId);
        Papers p = papersService.getById(t.getPaperId());
        if (p == null) {
            return CommonReturnType.create("没有找到该试卷");
        }
        Score score = new Score();
        score.setEtScore(sum);
        score.setTeacherPhone(teacherPhone);
        score.setAnswerDate(String.valueOf(LocalDate.now()));
        score.setExamCode(testId);
        score.setSubject(t.getCourseName());
        score.setStudentId(studentphone);
        scoreService.save(score);
        return CommonReturnType.create(score);
    }

    @PostMapping("/getpaper/bypaperId")
    public CommonReturnType getpaper(@RequestParam Integer paperId) {
        Papers p = papersService.getById(paperId);

        if (p == null) {
            return CommonReturnType.create("没有找到该试卷");
        }
        return CommonReturnType.create(p);
    }

    private List<String> stringToList(String strs) {
        String[] str = strs.split(",");
        return Arrays.asList(str);
    }
}

