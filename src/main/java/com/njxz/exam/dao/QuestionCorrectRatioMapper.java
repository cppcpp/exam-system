package com.njxz.exam.dao;

import com.njxz.exam.modle.QuestionCorrectRatio;
import com.njxz.exam.modle.QuestionCorrectRatioExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionCorrectRatioMapper {
    long countByExample(QuestionCorrectRatioExample example);

    int deleteByExample(QuestionCorrectRatioExample example);

    int deleteByPrimaryKey(Long qResponseId);

    int insert(QuestionCorrectRatio record);

    int insertSelective(QuestionCorrectRatio record);

    List<QuestionCorrectRatio> selectByExample(QuestionCorrectRatioExample example);

    QuestionCorrectRatio selectByPrimaryKey(Long qResponseId);

    int updateByExampleSelective(@Param("record") QuestionCorrectRatio record, @Param("example") QuestionCorrectRatioExample example);

    int updateByExample(@Param("record") QuestionCorrectRatio record, @Param("example") QuestionCorrectRatioExample example);

    int updateByPrimaryKeySelective(QuestionCorrectRatio record);

    int updateByPrimaryKey(QuestionCorrectRatio record);
}