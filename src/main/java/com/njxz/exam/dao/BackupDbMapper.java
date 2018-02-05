package com.njxz.exam.dao;

import com.njxz.exam.modle.BackupDb;
import com.njxz.exam.modle.BackupDbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BackupDbMapper {
    long countByExample(BackupDbExample example);

    int deleteByExample(BackupDbExample example);

    int deleteByPrimaryKey(Long bId);

    int insert(BackupDb record);

    int insertSelective(BackupDb record);

    List<BackupDb> selectByExample(BackupDbExample example);

    BackupDb selectByPrimaryKey(Long bId);

    int updateByExampleSelective(@Param("record") BackupDb record, @Param("example") BackupDbExample example);

    int updateByExample(@Param("record") BackupDb record, @Param("example") BackupDbExample example);

    int updateByPrimaryKeySelective(BackupDb record);

    int updateByPrimaryKey(BackupDb record);
}