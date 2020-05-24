package com.lpq.mail.dao;

import com.lpq.mail.entity.MailInfo;
import com.lpq.mail.entity.MailInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MailInfoDao {
    long countByExample(MailInfoExample example);

    int deleteByExample(MailInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MailInfo record);

    int insertSelective(MailInfo record);

    int insertByList(List<MailInfo> mails);

    List<MailInfo> selectByExample(MailInfoExample example);

    MailInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MailInfo record, @Param("example") MailInfoExample example);

    int updateByExample(@Param("record") MailInfo record, @Param("example") MailInfoExample example);

    int updateByPrimaryKeySelective(MailInfo record);

    int updateByPrimaryKey(MailInfo record);
}