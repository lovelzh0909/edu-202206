package com.example.demo.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Notice;
import com.example.demo.entity.Test;
import com.example.demo.entity.TestRelStudent;
import com.example.demo.entity.User;
import com.example.demo.entity.vo.StudentTestNoticeVO;
import com.example.demo.service.NoticeService;
import com.example.demo.service.TestRelStudentService;
import com.example.demo.service.TestService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-08
 */
@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    @Autowired
    TestService testService;
    @Autowired
    TestRelStudentService testrelstudentService;
    @Autowired
    UserService userService;


    @PostMapping
    public CommonReturnType notice(@RequestParam String phone) {
        log.info("获取基本通知信息");
        log.info("前端发送:"+phone);
        //new CommonReturnType();
        QueryWrapper<Notice> queryWrapper= new QueryWrapper();
        queryWrapper.select("notice");
        List<Notice> note =noticeService.list(queryWrapper.eq("phone", phone));
        log.info("后端发送:"+note);
        if(note!=null&&note.size() != 0){
            return CommonReturnType.create(note,"success");
        }
        else{
            return CommonReturnType.create(null,"暂无通知");
        }
    }

    @PostMapping("/save")
    public CommonReturnType notice(@RequestParam int testId,@RequestParam String text,@RequestParam String deadline) {
        log.info("获取基本通知信息");
        log.info("前端发送:"+testId);
        List<TestRelStudent> testrelstudent = testrelstudentService.list(new QueryWrapper<TestRelStudent>().eq("testId",testId));
        //new CommonReturnType();
        if(testrelstudent ==null){
            return CommonReturnType.create(null,"该测试没有学生参加");
        }
        for(TestRelStudent t:testrelstudent){
            Notice notice = new Notice();
            notice.setNotice(text);
            notice.setPhone(t.getStudentPhone());
//            notice.setCreateTime(String.valueOf(LocalDateTime.now()));
            log.info(deadline);
            notice.setDeadline(deadline);
            noticeService.save(notice);
        }
        log.info("后端发送:success");
        return CommonReturnType.create(null);
    }
    @PostMapping("/getStudentNotice")
    public CommonReturnType getStudentNotice(@RequestParam String phone) {
        log.info("获取考试通知信息");
        log.info("前端发送:"+phone);
        // Map<String, Object> map = new HashMap<>();
//        Page<StudentTestNoticeVO> Studentnote =new Page<>();
        List<StudentTestNoticeVO> note =new ArrayList<>();
        List<TestRelStudent> testrelstudents =testrelstudentService.list(new QueryWrapper<TestRelStudent>().eq("studentPhone",phone));
        for(TestRelStudent testrelstudent:testrelstudents){
            Test test = testService.getById(testrelstudent.getTestId());
            StudentTestNoticeVO studentTestNoticeVO =new StudentTestNoticeVO();
            studentTestNoticeVO.setTestTime(test.getTestTime());
            studentTestNoticeVO.setCoursename(test.getCourseName());
            studentTestNoticeVO.setInvigilator(test.getInvigilator());
            studentTestNoticeVO.setTestteacher(test.getTestTeacher());
            studentTestNoticeVO.setTimeLast(test.getTimeLast()+"分钟");
//            User user =userService.getById(phone);
            User user =userService.getOne( new QueryWrapper<User>().eq("phone",phone));
            studentTestNoticeVO.setName(user.getName());
            studentTestNoticeVO.setStudentId(user.getStudentId());
//            Studentnote.addOrder(studentTestNoticeVO);
            note.add(studentTestNoticeVO);
        }
        log.info("后端发送:"+note);
//        Page<StudentTestNoticeVO> Studentnote = noticeService.getStudentNote(new Page<>(),phone);
        if(note!=null&&note.size() != 0){
            return CommonReturnType.create(note,"success");
        }
        else{
            return CommonReturnType.create(null,"暂无通知");
        }
        // if (Studentnote.getRecords().size() == 0) {
        //     map.put("message", "暂无数据");
        // } else {
        //     map.put("message", "success");
        //     map.put("data", Studentnote);
        // }
        // return map;
    }
    /**
     * 
     * @param phone
     * @return 返回7天内test通知信息
     */
    @PostMapping("/closenotice")
    public CommonReturnType closenotice(@RequestParam(defaultValue = "123",required = false) String phone) {
        // Map<String, Object> map = new HashMap<>();
        //new CommonReturnType();
        log.info("返回7天内考试信息");
        log.info("前端发送"+phone);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // String dateString = "2017-01-01 11:11:11";
        Calendar calendar = Calendar.getInstance();
        long specialDate;
        double betweenDate = 0;
        long nowDate = calendar.getTime().getTime(); //Date.getTime() 获得毫秒型日期
        QueryWrapper<Test> queryWrapper = new QueryWrapper();
        queryWrapper.select("TestTime","coursename","note");
//        List<Test> note = testService.list(queryWrapper.eq("phone", phone));
        List<Test> note =new ArrayList<>();
        List<TestRelStudent> testrelstudents =testrelstudentService.list(new QueryWrapper<TestRelStudent>().eq("studentPhone",phone));
        for(TestRelStudent testrelstudent:testrelstudents){
            Test test = testService.getById(testrelstudent.getTestId());
            if(test!=null)
            note.add(test);
        }
        List<Test> notes =new ArrayList<>();
        for (int i = 0; i < note.size(); i++) {
            try {
                specialDate = sdf.parse(note.get(i).getTestTime()).getTime();
                betweenDate = (specialDate - nowDate) / (1000 * 60 * 60 * 24 *1.0);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (betweenDate > 7 || betweenDate<0) {
                log.info(String.valueOf(note.get(i)));
            }
            else {
                note.get(i).setNote(String.valueOf((int) betweenDate));
                notes.add(note.get(i));
            }
        }
        // if (note.size() == 0) {
        //     map.put("message", "暂无数据");
        // } else {
        //     map.put("message", "success");
        //     map.put("data", note);
        // }
        // return map;
        log.info("后端发送:"+notes);
        if(notes!=null&&notes.size() != 0){
            return CommonReturnType.create(notes,"success");
        }
        else{
            return CommonReturnType.create(null,"暂无通知");
        }
    }




}

