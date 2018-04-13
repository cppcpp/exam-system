package com.njxz.exam.dao;

import com.njxz.exam.modle.News;
import com.njxz.exam.modle.NewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface NewsMapper {
    long countByExample(NewsExample example);

    int deleteByExample(NewsExample example);

    int deleteByPrimaryKey(Long nId);

    int insert(News record);

    int insertSelective(News record);

    List<News> selectByExample(NewsExample example);

    News selectByPrimaryKey(Long nId);

    int updateByExampleSelective(@Param("record") News record, @Param("example") NewsExample example);

    int updateByExample(@Param("record") News record, @Param("example") NewsExample example);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
    
    List<News> getAll();
    
    List<News> latelyNews(@Param("count")int count);
}