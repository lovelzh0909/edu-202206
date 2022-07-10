package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.TeacherRelClass;
import com.example.demo.entity.User;
import com.example.demo.service.TeacherrelclassService;
import com.example.demo.service.UserService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-28
 */
@RestController
@RequestMapping("/TeacherRelClass")
public class TeacherRelClassController {
    @Autowired
    TeacherrelclassService teacherRelClassService;
    @Autowired
    UserService userService;

    /**
     * 根据教师手机获得班级名称
     * @param teacherphone
     * @return
     */
    @PostMapping("/getClass/byTeacher")
    public CommonReturnType getTeacherClass(@RequestParam String teacherphone) {

        return CommonReturnType.create(teacherRelClassService.list( new QueryWrapper<TeacherRelClass>().eq("teacher",teacherphone)));
    }

    /**
     * 根据班级名称（list）获得其中的同学
     * @param t
     * @return
     */
    @PostMapping("/getStudent/byclass")
    public CommonReturnType getStudentbyclass(@RequestParam List<String> t) {

        List<User> student = userService.list(new QueryWrapper<User>().in("classroom", t).ne("role", "teacher"));
        System.out.println(student);
//        for (TeacherRelClass TeacherRelClass : teacher) {
//            list.add(userService.list(new QueryWrapper<User>().eq("class", TeacherRelClass.getClassroom()))
//            );
//        }
        return CommonReturnType.create(student);
    }
}

