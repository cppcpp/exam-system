package com.njxz.exam.dao;

import com.njxz.exam.modle.SystemConfig;
import com.njxz.exam.modle.SystemConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SystemConfigMapper {
	
	@Select("select * from system_config")
	List<SystemConfig> getAll();
	
	long countByExample(SystemConfigExample example);

    int deleteByExample(SystemConfigExample example);

    int deleteByPrimaryKey(Byte id);

    int insert(SystemConfig record);

    int insertSelective(SystemConfig record);

    List<SystemConfig> selectByExample(SystemConfigExample example);

    SystemConfig selectByPrimaryKey(Byte id);

    int updateByExampleSelective(@Param("record") SystemConfig record, @Param("example") SystemConfigExample example);

    int updateByExample(@Param("record") SystemConfig record, @Param("example") SystemConfigExample example);

    int updateByPrimaryKeySelective(SystemConfig record);

    int updateByPrimaryKey(SystemConfig record);
    
    @Update("update `system_config` set `key` = #{key},`value` = #{value} where `id` = #{id}")
    int modify(SystemConfig systemConfig);
}