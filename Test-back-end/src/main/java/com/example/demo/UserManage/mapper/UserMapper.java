package com.example.demo.UserManage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.UserManage.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
