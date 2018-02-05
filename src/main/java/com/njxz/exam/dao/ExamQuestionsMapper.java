package com.njxz.exam.dao;

import com.njxz.exam.modle.ExamQuestions;
import com.njxz.exam.modle.ExamQuestionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamQuestionsMapper {
    long countByExample(ExamQuestionsExample example);

    int deleteByExample(ExamQuestionsExample example);

    int deleteByPrimaryKey(Long eQId);

    int insert(ExamQuestions record);

    int insertSelective(ExamQuestions record);

    List<ExamQuestions> selectByExample(ExamQuestionsExample example);

    ExamQuestions selectByPrimaryKey(Long eQId);

    int updateByExampleSelective(@Param("record") ExamQuestions record, @Param("example") ExamQuestionsExample example);

    int updateByExample(@Param("record") ExamQuestions record, @Param("example") ExamQuestionsExample example);

    int updateByPrimaryKeySelective(ExamQuestions record);

    int updateByPrimaryKey(ExamQuestions record);
    
    int insertList(@Param("list")List<ExamQuestions> list);
}