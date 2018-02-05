package com.njxz.exam.dao;

import com.njxz.exam.modle.KnowledgePoints;
import com.njxz.exam.modle.KnowledgePointsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KnowledgePointsMapper {
    long countByExample(KnowledgePointsExample example);

    int deleteByExample(KnowledgePointsExample example);

    int deleteByPrimaryKey(Long kId);

    int insert(KnowledgePoints record);

    int insertSelective(KnowledgePoints record);

    List<KnowledgePoints> selectByExample(KnowledgePointsExample example);

    KnowledgePoints selectByPrimaryKey(Long kId);

    int updateByExampleSelective(@Param("record") KnowledgePoints record, @Param("example") KnowledgePointsExample example);

    int updateByExample(@Param("record") KnowledgePoints record, @Param("example") KnowledgePointsExample example);

    int updateByPrimaryKeySelective(KnowledgePoints record);

    int updateByPrimaryKey(KnowledgePoints record);
    
    List<KnowledgePoints> getKnowledgePointsBySId(@Param("sId")Short sId);
}