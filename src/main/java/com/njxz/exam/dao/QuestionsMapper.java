package com.njxz.exam.dao;

import com.njxz.exam.modle.Questions;
import com.njxz.exam.modle.QuestionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionsMapper {
    long countByExample(QuestionsExample example);

    int deleteByExample(QuestionsExample example);

    int deleteByPrimaryKey(Long qId);

    int insert(Questions record);

    int insertSelective(Questions record);

    List<Questions> selectByExample(QuestionsExample example);

    Questions selectByPrimaryKey(Long qId);

    int updateByExampleSelective(@Param("record") Questions record, @Param("example") QuestionsExample example);

    int updateByExample(@Param("record") Questions record, @Param("example") QuestionsExample example);

    int updateByPrimaryKeySelective(Questions record);

    int updateByPrimaryKey(Questions record);
    
    public int countgetBy(Long qId);
    
    public List<Questions> getQuestionsByEIdAndEQTId(@Param("eId")Long eId,@Param("eqtId") Long eqtId,@Param("eType")int eType);
}