package com.lpq.mail.dao;

import com.lpq.mail.entity.IpInfo;
import com.lpq.mail.entity.IpInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IpInfoDao {
    long countByExample(IpInfoExample example);

    int deleteByExample(IpInfoExample example);

    int deleteByPrimaryKey(String ip);

    int insert(IpInfo record);

    int insertSelective(IpInfo record);

    List<IpInfo> selectByExample(IpInfoExample example);

    IpInfo selectByPrimaryKey(String ip);

    int updateByExampleSelective(@Param("record") IpInfo record, @Param("example") IpInfoExample example);

    int updateByExample(@Param("record") IpInfo record, @Param("example") IpInfoExample example);

    int updateByPrimaryKeySelective(IpInfo record);

    int updateByPrimaryKey(IpInfo record);
}