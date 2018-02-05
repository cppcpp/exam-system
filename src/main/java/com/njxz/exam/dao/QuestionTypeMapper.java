package com.njxz.exam.dao;

import com.njxz.exam.modle.QuestionType;
import com.njxz.exam.modle.QuestionTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionTypeMapper {
	
	List<QuestionType> selectAllQuestionType();
	
    long countByExample(QuestionTypeExample example);

    int deleteByExample(QuestionTypeExample example);

    int deleteByPrimaryKey(Long tId);

    int insert(QuestionType record);

    int insertSelective(QuestionType record);

    List<QuestionType> selectByExample(QuestionTypeExample example);

    QuestionType selectByPrimaryKey(Long tId);

    int updateByExampleSelective(@Param("record") QuestionType record, @Param("example") QuestionTypeExample example);

    int updateByExample(@Param("record") QuestionType record, @Param("example") QuestionTypeExample example);

    int updateByPrimaryKeySelective(QuestionType record);

    int updateByPrimaryKey(QuestionType record);
}