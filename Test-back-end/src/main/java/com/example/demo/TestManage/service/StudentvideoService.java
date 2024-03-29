package com.example.demo.TestManage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.TestManage.entity.Studentvideo;
import com.example.demo.TestManage.entity.vo.StudentVideoVO;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
public interface StudentvideoService extends IService<Studentvideo> {
    Page<StudentVideoVO> getStudentPicture(Page<StudentVideoVO> page, String phone);
}
