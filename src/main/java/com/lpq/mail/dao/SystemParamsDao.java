package com.lpq.mail.dao;

import com.lpq.mail.entity.SystemParams;
import com.lpq.mail.entity.SystemParamsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemParamsDao {
    long countByExample(SystemParamsExample example);

    int deleteByExample(SystemParamsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemParams record);

    int insertSelective(SystemParams record);

    List<SystemParams> selectByExample(SystemParamsExample example);

    SystemParams selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemParams record, @Param("example") SystemParamsExample example);

    int updateByExample(@Param("record") SystemParams record, @Param("example") SystemParamsExample example);

    int updateByPrimaryKeySelective(SystemParams record);

    int updateByPrimaryKey(SystemParams record);
}