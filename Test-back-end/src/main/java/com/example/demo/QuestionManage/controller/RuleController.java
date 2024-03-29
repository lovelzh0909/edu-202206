package com.example.demo.QuestionManage.controller;


import com.example.demo.PaperManage.entity.Rule;
import com.example.demo.PaperManage.service.RuleService;
import com.example.demo.Response.CommonReturnType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/rule")
public class RuleController {
    private final RuleService ruleService;

    @Autowired
    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping("/save")
    public CommonReturnType saveQuestion(@RequestBody Rule rule) {
        // if(rule.ge)
        // return CommonReturnType.create(null,"信息不全");
        if (rule.getDifficulty() == 0) {
            rule.setDifficulty(3);
            //添加时间
            // Date d= new Date();
        }
        Boolean data = ruleService.save(rule);
        if (data == false)
            return CommonReturnType.create(null, "添加失败");
        return CommonReturnType.create(null);
    }
}

