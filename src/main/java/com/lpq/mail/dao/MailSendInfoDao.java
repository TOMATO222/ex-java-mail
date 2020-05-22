package com.lpq.mail.dao;

import com.lpq.mail.entity.MailSendInfo;
import com.lpq.mail.entity.MailSendInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MailSendInfoDao {
    long countByExample(MailSendInfoExample example);

    int deleteByExample(MailSendInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MailSendInfo record);

    int insertSelective(MailSendInfo record);

    List<MailSendInfo> selectByExample(MailSendInfoExample example);

    MailSendInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MailSendInfo record, @Param("example") MailSendInfoExample example);

    int updateByExample(@Param("record") MailSendInfo record, @Param("example") MailSendInfoExample example);

    int updateByPrimaryKeySelective(MailSendInfo record);

    int updateByPrimaryKey(MailSendInfo record);
}