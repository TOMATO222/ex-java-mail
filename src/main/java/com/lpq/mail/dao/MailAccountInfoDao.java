package com.lpq.mail.dao;

import com.lpq.mail.entity.MailAccountInfo;
import com.lpq.mail.entity.MailAccountInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MailAccountInfoDao {
    long countByExample(MailAccountInfoExample example);

    int deleteByExample(MailAccountInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MailAccountInfo record);

    int insertSelective(MailAccountInfo record);

    List<MailAccountInfo> selectByExample(MailAccountInfoExample example);

    MailAccountInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MailAccountInfo record, @Param("example") MailAccountInfoExample example);

    int updateByExample(@Param("record") MailAccountInfo record, @Param("example") MailAccountInfoExample example);

    int updateByPrimaryKeySelective(MailAccountInfo record);

    int updateByPrimaryKey(MailAccountInfo record);
}