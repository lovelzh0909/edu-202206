package com.example.demo.PaperManage.service.impl;

import com.example.demo.PaperManage.entity.Papers;
import com.example.demo.QuestionManage.entity.Question;
import com.example.demo.PaperManage.mapper.PapersMapper;
import com.example.demo.PaperManage.service.PapersService;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-17
 */
@Service
public class PapersServiceImpl extends ServiceImpl<PapersMapper, Papers> implements PapersService {

    @Override
    public List<Question> getpapersQuestions(/*Page<StudentTestNoticeVO> page*/Integer paperId){
        return this.baseMapper.getpapersQuestions(paperId);
    }

    @Override
    public Question getQuestions(Integer questionId) {
        // TODO Auto-generated method stub
        return this.baseMapper.getQuestions(questionId);
    }

    @Override
    public int findOnlyQuestionId() {
        // TODO Auto-generated method stub
        return this.baseMapper.lastQuestionId();
    }
}
