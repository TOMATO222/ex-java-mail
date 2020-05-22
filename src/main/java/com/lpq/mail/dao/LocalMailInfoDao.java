package com.lpq.mail.dao;

import com.lpq.mail.entity.LocalMailInfo;
import com.lpq.mail.entity.LocalMailInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LocalMailInfoDao {
    long countByExample(LocalMailInfoExample example);

    int deleteByExample(LocalMailInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LocalMailInfo record);

    int insertSelective(LocalMailInfo record);

    List<LocalMailInfo> selectByExample(LocalMailInfoExample example);

    LocalMailInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LocalMailInfo record, @Param("example") LocalMailInfoExample example);

    int updateByExample(@Param("record") LocalMailInfo record, @Param("example") LocalMailInfoExample example);

    int updateByPrimaryKeySelective(LocalMailInfo record);

    int updateByPrimaryKey(LocalMailInfo record);
}