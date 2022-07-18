package com.example.demo.TestManage.service.impl;

import com.example.demo.TestManage.entity.Test;
import com.example.demo.TestManage.mapper.TestMapper;
import com.example.demo.TestManage.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

}
