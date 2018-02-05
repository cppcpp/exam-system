package com.njxz.exam.dao;

import com.njxz.exam.modle.UserSubject;
import com.njxz.exam.modle.UserSubjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserSubjectMapper {
    long countByExample(UserSubjectExample example);

    int deleteByExample(UserSubjectExample example);

    int deleteByPrimaryKey(Long uSId);

    int insert(UserSubject record);

    int insertSelective(UserSubject record);

    List<UserSubject> selectByExample(UserSubjectExample example);

    UserSubject selectByPrimaryKey(Long uSId);

    int updateByExampleSelective(@Param("record") UserSubject record, @Param("example") UserSubjectExample example);

    int updateByExample(@Param("record") UserSubject record, @Param("example") UserSubjectExample example);

    int updateByPrimaryKeySelective(UserSubject record);

    int updateByPrimaryKey(UserSubject record);
}