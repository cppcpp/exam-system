package com.njxz.exam.dao;

import com.njxz.exam.modle.ExamQuestiontype;
import com.njxz.exam.modle.ExamQuestiontypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamQuestiontypeMapper {
    long countByExample(ExamQuestiontypeExample example);

    int deleteByExample(ExamQuestiontypeExample example);

    int deleteByPrimaryKey(Long eTId);

    int insert(ExamQuestiontype record);

    int insertSelective(ExamQuestiontype record);

    List<ExamQuestiontype> selectByExample(ExamQuestiontypeExample example);

    ExamQuestiontype selectByPrimaryKey(Long eTId);

    int updateByExampleSelective(@Param("record") ExamQuestiontype record, @Param("example") ExamQuestiontypeExample example);

    int updateByExample(@Param("record") ExamQuestiontype record, @Param("example") ExamQuestiontypeExample example);

    int updateByPrimaryKeySelective(ExamQuestiontype record);

    int updateByPrimaryKey(ExamQuestiontype record);
    
    int insertList(@Param("list")List<ExamQuestiontype> list);
}