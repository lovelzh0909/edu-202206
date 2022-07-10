package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Papers;
import com.example.demo.entity.Test;
import com.example.demo.entity.TestRelStudent;
import com.example.demo.entity.User;
import com.example.demo.service.PapersService;
import com.example.demo.service.TestRelStudentService;
import com.example.demo.service.TestService;
import com.example.demo.service.UserService;
import com.sun.net.httpserver.Authenticator;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;
    @Autowired
    TestRelStudentService testrelstudentService;
    @Autowired
    UserService userService;
    @Autowired
    PapersService papersService;

    @PostMapping("/all")
    public CommonReturnType listAll() {
        List<Test> l = testService.list();
        if (l == null) {
            return CommonReturnType.create("没有任何考试信息");
        }
        return CommonReturnType.create(l);
    }

    @PostMapping("/save")
    public CommonReturnType saveTest(@RequestBody Test t) {
        log.info("------------------log--------------");
        log.info(t.toString());
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        if (t.getCreateDate() == null) {
            //添加时间
            // Date d= new Date();
            t.setCreateDate(String.valueOf(LocalDateTime.now()));
        }
        if(t.getTestTime()!=null){
            String sub=t.getTestTime().substring(0,9);
        }
        // if(q.getId()==null){
        //     q.setId(questionService.lastQuestionId()+1);
        // }
        boolean data = testService.save(t);
        if (!data)
            return CommonReturnType.create(null, "添加失败");
        return CommonReturnType.create(null);
    }

    @PostMapping("/saveall")
    public CommonReturnType saveTest(@RequestBody List<Test> t) {
        log.info("发布考试");
        log.info("前端发送："+t);
        boolean data = false;
        for (Test te : t) {
            if (te.getCreateDate() == null) {

                te.setCreateDate(String.valueOf(LocalDate.now()));
            }
            te.setTestStatus(1);
//            if (te.getRoomId() == null) {
//
//                te.setRoomId(te.getTestId());
//            }
            List<TestRelStudent> tsl = new ArrayList<>();
            List<String> phone = te.getStudentPhone();
            te.setStudentPhone(null);
            List<User> userList = new ArrayList<>();
            if (te.getTestTime() != null) {
                String sub = te.getTestTime().substring(0, 10);
                log.info(sub);
//                List<User> l = userService.list(new QueryWrapper<User>().in("role", new String[]{"teacher","admin"}));
                List<User> l = userService.list(new QueryWrapper<User>().in("role", "teacher","admin"));
                for (User u : l) {
                    int flag = 1;
                    List<Test> testList = testService.list(new QueryWrapper<Test>().eq("invigilatorId", u.getPhone()));
                    log.info(testList.toString());
                    for (Test tes : testList) {
                        if (tes.getTestTime().substring(0, 10).equals(sub)) {
                            flag = 0;
                            break;
                        }
                    }
                    if (flag == 1) {
                        userList.add(u);
                    }
                }
            }
            if (userList.size() == 0) {
                return CommonReturnType.create(null, "没有空余监考老师");
            }
            Random r = new Random();
            int i = r.nextInt(userList.size());
            //设置监考老师id及姓名
            te.setInvigilatorId(userList.get(i).getPhone());
            te.setInvigilator(userList.get(i).getName());
            testService.save(te);
            Papers papers =papersService.getOne(new QueryWrapper<Papers>().eq("paperId",te.getPaperId()));
            papers.setPapernum(papers.getPapernum()+1);
            papersService.updateById(papers);
            if (te.getRoomId() == null) {
                te.setRoomId(te.getTestId());
            }
            testService.saveOrUpdate(te);
            for (String p : phone) {
                TestRelStudent trs = new TestRelStudent();
                trs.setTestId(te.getTestId());
                trs.setStudentPhone(p);
                trs.setStatus(1);
                tsl.add(trs);
            }
            data = testrelstudentService.saveBatch(tsl);
        }
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        log.info("后端发送:success");
        if (!data)
            return CommonReturnType.create(null, "添加失败");
        return CommonReturnType.create(null);
    }

    @PostMapping("/changeStatus")
    public CommonReturnType changeStatus(@RequestBody int id) {
        Test t = testService.getById(id);
        t.setPystatus("pystatus");
        t.setTestStatus(1);
        boolean data = testService.save(t);
        if (!data)
            return CommonReturnType.create(null, "添加失败");
        return CommonReturnType.create(null);
        // return testService.list(new QueryWrapper<Test>().eq("phone", test.getPhone()));
    }

    /**
     * @param
     * @return
     */
    @PostMapping("/getStudenttest/{page}/{size}")
    public CommonReturnType listOne(@RequestParam Integer phone, @PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        log.info("获取考试列表（学生）");
        log.info("前端发送："+phone);
        Page<Test> page2 = new Page<>(page, size);
        List<TestRelStudent> ts = testrelstudentService.list(new QueryWrapper<TestRelStudent>().eq("StudentPhone", phone));
//        List<Test> l=new ArrayList<Test>();
        List<Integer> ll = new ArrayList<>();
        for (TestRelStudent t : ts) {
//            l.add(testService.getById(t.getTestId())) ;
            ll.add(t.getTestId());
        }
        if (ll.size() == 0) {
            return CommonReturnType.create(null, "没有该学生考试信息");
        }

        Page<Test> page3 = testService.page(page2, new QueryWrapper<Test>().in("testId", ll));
        for(Test test:page3.getRecords()){
            TestRelStudent testrelstudent=testrelstudentService.getOne(new QueryWrapper<TestRelStudent>().eq("testId",test.getTestId()).eq("StudentPhone",phone));
            LocalDateTime localDateTime=LocalDateTime.now();
            String s= test.getTestTime().substring(0,10)+"T"+test.getTestTime().substring(11);
            log.info("-----------log--------");
            log.info(s);
            LocalDateTime localDateTime1 = LocalDateTime.parse(s);

            if(test.getTimeLast()==null){
                return CommonReturnType.create("该测试没有测试时间");
            }
            LocalDateTime localDateTime2 = localDateTime1.plusMinutes(test.getTimeLast());

            if(localDateTime.isBefore(localDateTime1)){
                test.setTestStatus(1);
            }
            else if(localDateTime.isBefore(localDateTime2)){
                test.setTestStatus(2);
            }
            else
            {
                test.setTestStatus(3);
            }
            Papers p=papersService.getById(test.getPaperId());
            test.setNote(p.getNote());
            testService.saveOrUpdate(test);
            if(testrelstudent.getStatus()==3||testrelstudent.getStatus()==4)
               test.setTestStatus(testrelstudent.getStatus());
            else
                testrelstudent.setStatus(test.getTestStatus());
            testrelstudentService.saveOrUpdate(testrelstudent);
        }

        page3.setTotal(ll.size());
        // List<Test> l =testService.list(new QueryWrapper<Test>().eq("phone", test.getPhone()));
        //     List<Pipeline> subList = newPipelineList.subList(fromIndex, toIndex);
        // Pageable pageable = new PageRequest(page, size);
        // Page<Test> pipelinePage = new PageImpl<>(l, page2, l.size());

        // return new PageInfo<>(pipelinePage);
//        if(l.size()==0){
//            return CommonReturnType.create(null,"没有该学生考试信息");
//        }
        log.info("后端发送："+page3);
        return CommonReturnType.create(page3);
        // return testService.list(new QueryWrapper<Test>().eq("phone", test.getPhone()));
    }

    /**
     * @param
     * @return
     */
    @PostMapping("/getTeachertest/{page}/{size}")
    public CommonReturnType listteacherOne(@RequestBody Test te, @PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        log.info("获取监考考试列表（教师）");
        log.info("前端发送："+te);
        List<Test> l = testService.list(new QueryWrapper<Test>().eq("invigilatorId", te.getInvigilatorId()));
        Page<Test> page2 = new Page<>(page, size);
        Page<Test> p = testService.page(page2, new QueryWrapper<Test>().eq("invigilatorId", te.getInvigilatorId()));
        for(Test test:p.getRecords()){
            LocalDateTime localDateTime=LocalDateTime.now();
            String s= test.getTestTime().substring(0,10)+"T"+test.getTestTime().substring(11);
            log.info("-----------log--------");
            log.info(s);
            LocalDateTime localDateTime1 = LocalDateTime.parse(s);
            LocalDateTime localDateTime2 = localDateTime1.plusMinutes(test.getTimeLast());

            if(localDateTime.isBefore(localDateTime1)){
                test.setTestStatus(1);
            }
            else if(localDateTime.isBefore(localDateTime2)){
                test.setTestStatus(2);
            }
            else
            {
                test.setTestStatus(3);
            }
            testService.saveOrUpdate(test);
        }
        log.info("后端发送:"+l);
        p.setTotal(l.size());
        if (l.size() == 0) {
            return CommonReturnType.create("没有该老师相关考试信息");
        }
        return CommonReturnType.create(p);
        // return testService.list(new QueryWrapper<Test>().eq("TeacherPhone", test.getTeacherPhone()));
    }

    /**
     * @param
     * @return
     */
    @PostMapping("/getTeachertest/distribute/{page}/{size}")
    public CommonReturnType listteacherOnereal(@RequestBody Test te, @PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        log.info("获取考试列表（老师）");
        log.info("前端发送："+te);
        List<Test> l = testService.list(new QueryWrapper<Test>().eq("TeacherPhone", te.getTeacherPhone()));
        Page<Test> page2 = new Page<>(page, size);
        Page<Test> p = testService.page(page2, new QueryWrapper<Test>().eq("TeacherPhone", te.getTeacherPhone()));
        for(Test test:p.getRecords()){
            //现在时间
            LocalDateTime localDateTime=LocalDateTime.now();
            String s= test.getTestTime().substring(0,10)+"T"+test.getTestTime().substring(11);
            log.info("-----------log--------");
            log.info(s);
            LocalDateTime localDateTime1 = LocalDateTime.parse(s);
            LocalDateTime localDateTime2 = localDateTime1.plusMinutes(test.getTimeLast());
            log.info("-------------log--------");
            log.info(String.valueOf(localDateTime2));
            if(localDateTime.isBefore(localDateTime1)){
                test.setTestStatus(1);
            }
            else if(localDateTime.isBefore(localDateTime2)){
                test.setTestStatus(2);
            }
            else
            {
                test.setTestStatus(3);
            }
            testService.saveOrUpdate(test);
        }
        p.setTotal(l.size());
        log.info("后端发送:"+l);
        if (l.size() == 0) {
            return CommonReturnType.create("没有该老师相关考试信息");
        }
        return CommonReturnType.create(p);
//         return testService.list(new QueryWrapper<Test>().eq("TeacherPhone", test.getTeacherPhone()));
    }

    /**
     * @param id
     * @return
     */
    @PostMapping("/remove")
    public CommonReturnType removeQuestion(@RequestBody int id) {
        log.info("删除考试");
        log.info("前端发送:"+id);
        boolean data = testService.remove(new QueryWrapper<Test>()
                .eq("testId", id)
        );
        boolean data2 =testrelstudentService.remove(new QueryWrapper<TestRelStudent>()
                .eq("testId", id));
        log.info("后端发送: success");
        if (!data) {
            return CommonReturnType.create(null,"没有该测试或已经被删除");
        }

        return CommonReturnType.create(null);
    }

    /**
     * @param t
     * @return
     */
    @PostMapping("/selectbyid")
    public CommonReturnType showTest(@RequestBody int t) {
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        // if(q.getId()==null){
        //     q.setId(questionService.lastQuestionId()+1);
        // }
        Test data = testService.getById(t);
        if (data == null)
            return CommonReturnType.create(null, "没有任何该考试信息");
        return CommonReturnType.create(null);
    }

    /**
     * 通过list id 获取相关测试信息
     *
     * @param t
     * @return
     */
    @PostMapping("/selectbylistid")
    public CommonReturnType showTest(@RequestBody List<Integer> t) {
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        // if(q.getId()==null){
        //     q.setId(questionService.lastQuestionId()+1);
        // }
        List<Test> data = testService.listByIds(t);
        if (data == null)
            return CommonReturnType.create(null, "没有任何该考试信息");
        return CommonReturnType.create(null);
    }


}

