package com.njxz.exam.dao;

import com.njxz.exam.modle.Subject;
import com.njxz.exam.modle.SubjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

public interface SubjectMapper {
    long countByExample(SubjectExample example);

    int deleteByExample(SubjectExample example);

    int deleteByPrimaryKey(Long sId);

    int insert(Subject record);

    int insertSelective(Subject record);

    List<Subject> selectByExampleWithBLOBs(SubjectExample example);

    List<Subject> selectByExample(SubjectExample example);

    Subject selectByPrimaryKey(Long sId);

    int updateByExampleSelective(@Param("record") Subject record, @Param("example") SubjectExample example);

    int updateByExampleWithBLOBs(@Param("record") Subject record, @Param("example") SubjectExample example);

    int updateByExample(@Param("record") Subject record, @Param("example") SubjectExample example);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKeyWithBLOBs(Subject record);

    int updateByPrimaryKey(Subject record);
    
    List<Subject> selectAllSubject();
}