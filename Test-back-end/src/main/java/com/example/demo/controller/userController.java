package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.School;
import com.example.demo.entity.Teacherrelclass;
import com.example.demo.entity.User;
import com.example.demo.service.SchoolService;
import com.example.demo.service.TeacherrelclassService;
import com.example.demo.service.TestrelstudentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/user")
public class userController {
    UserService userService;
    SchoolService schoolService;
    TeacherrelclassService teacherrelclassService;

    @Autowired
    public userController(UserService userService, SchoolService schoolService, TeacherrelclassService teacherrelclassService) {
        this.userService = userService;
        this.schoolService = schoolService;
        this.teacherrelclassService = teacherrelclassService;
    }

    //@ResponseBody
//    @GetMapping("/user")
//    public User getById(@RequestParam("id") int id){
//
//        return userService.getUserByid(id);
//    }
    @PostMapping("/logon")
    public CommonReturnType logon(@RequestBody User user) {
        //new CommonReturnType();

        User one = userService.getOne(
                new QueryWrapper<User>()
                        .eq("phone", user.getPhone())
                        .eq("password", user.getPassword()));
                
        if(one!=null){
            log.info("--------------------logo-------------------");
            log.info("logon["+ one +"]");
            log.info(one.getPassword());
//            try {
//                log.info(encryptByAES(one.getPassword()));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            return CommonReturnType.create(one.getRole(),"success");
        }
        return CommonReturnType.create(null,"用户名或密码错误");
        
    }

//    @RequestMapping("/register")
//    public CommonReturnType register(@RequestBody User user) {
//        userService.save(user);
//        return CommonReturnType.create(null,"success");
//    }

    @PostMapping("/register")
    public CommonReturnType register(@RequestBody User user) {
        User one = userService.getOne(new QueryWrapper<User>().eq("phone", user.getPhone()));
        if(one!=null){
            return CommonReturnType.create(null,"用户名已存在");
        }else{
            if (Objects.equals(user.getRole(), "admin") || Objects.equals(user.getRole(), "teacher")){
                Teacherrelclass teacherrelclass=new Teacherrelclass();
                teacherrelclass.setTeacher(user.getPhone());
                teacherrelclass.setClassroom(user.getClassroom());
                teacherrelclassService.save(teacherrelclass);
            }

            userService.save(user);
            //查找学生所在学校
            School school=schoolService.getOne(new QueryWrapper<School>().eq("school", user.getSchool()));
            long count = userService.count(new QueryWrapper<User>().eq("role", "student").eq("school", school.getSchool()));
            long count1 = userService.count(new QueryWrapper<User>().eq("role", "teacher").eq("school", school.getSchool()));
            school.setStudentnum(Math.toIntExact(count));
            school.setTeachernum(Math.toIntExact(count1));
            schoolService.updateById(school);
            log.info("--------------------logo-------------------");
            log.info("register["+ user +"]");
            assert false;
            return CommonReturnType.create(user.getRole(),"success");
        }
    }
    @PostMapping("/delete")
    public CommonReturnType delete(@RequestBody User user) {
        boolean b=userService.remove(new QueryWrapper<User>().eq("phone", user.getPhone()));
        if(!b)
        return CommonReturnType.create("没有该用户");
        log.info("--------------------logo-------------------");
        log.info("delete["+ user +"]");
        return CommonReturnType.create(null,"success");
    }
    @PostMapping("/information")
    public CommonReturnType information(@RequestParam String phone) {
        //new CommonReturnType();
        User one = userService.getOne(
                new QueryWrapper<User>()
                        .eq("phone", phone)
                        );
        if(one!=null){
            log.info("--------------------logo-------------------");
            log.info("send["+ one +"]");
            return CommonReturnType.create(one,"success");
        }
        else{
            return CommonReturnType.create(null,"用户名错误");
        }
    }
    @PostMapping("/update")
    public CommonReturnType update(@RequestBody User user) {
        User one = userService.getOne(
                new QueryWrapper<User>()
                        .eq("phone", user.getPhone())
        );
        if(one!=null){
            if (user.getPassword()!=null){
            one.setPassword(user.getPassword());
            }
            if (user.getClassroom()!=null){
                one.setClassroom(user.getClassroom());
            }
            if (user.getName()!=null){
                one.setName(user.getName());
            }
            if (user.getRole()!=null){
                one.setRole(user.getRole());
            }
            if (user.getSchool()!=null){
                one.setSchool(user.getSchool());
            }
            if (user.getStudentId()!=null){
                one.setStudentId(user.getStudentId());
            }
            userService.update(one,new QueryWrapper<User>().eq("phone", user.getPhone()));
            log.info("--------------------logo-------------------");
            log.info("update["+ one +"]");
            return CommonReturnType.create(one.getRole(),"success");
        }
        else{
            return CommonReturnType.create(null,"用户名错误");
        }
    }

    @GetMapping("/all")
    public CommonReturnType all() {
        List<User> all = userService.list();
        log.info("--------------------logo-------------------");
        log.info("all["+ all +"]");
        return CommonReturnType.create(all,"success");
    }

//    public static final String algorithm = "AES";
//    // AES/CBC/NOPaddin
//    // AES 默认模式
//    // 使用CBC模式, 在初始化Cipher对象时, 需要增加参数, 初始化向量IV : IvParameterSpec iv = new
//    // IvParameterSpec(key.getBytes());
//    // NOPadding: 使用NOPadding模式时, 原文长度必须是8byte的整数倍
//    public static final String transformation = "AES/CBC/NOPadding";
//    public static final String key = "1234567812345678";
//
//    /***
//     * 加密
//     * @param original 需要加密的参数（注意必须是16位）
//     * @return
//     * @throws Exception
//     */
//    public static String encryptByAES(String original) throws Exception {
//        // 获取Cipher
//        Cipher cipher = Cipher.getInstance(transformation);
//        // 生成密钥
//        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), algorithm);
//        // 指定模式(加密)和密钥
//        // 创建初始化向量
//        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
//        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
//        // cipher.init(Cipher.ENCRYPT_MODE, keySpec);
//        // 加密
//        byte[] bytes = cipher.doFinal(original.getBytes());
////        Base64.getEncoder().encodeToString(bytes)
////        return Base64Util.encryptBASE64(bytes);
//        return Base64.getEncoder().encodeToString(bytes);
//    }
//
//    /**
//     * 解密
//     * @param encrypted 需要解密的参数
//     * @return
//     * @throws Exception
//     */
//    public static String decryptByAES(String encrypted) throws Exception {
//        // 获取Cipher
//        Cipher cipher = Cipher.getInstance(transformation);
//        // 生成密钥
//        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), algorithm);
//        // 指定模式(解密)和密钥
//        // 创建初始化向量
//        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
//        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
//        // cipher.init(Cipher.DECRYPT_MODE, keySpec);
//        // 解密
////        byte[] bytes = cipher.doFinal(Base64Util.decryBASE64(encrypted));
//        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(encrypted));
////        Base64.getDecoder().decode(encrypted);
//        return new String(bytes);
//    }
}
