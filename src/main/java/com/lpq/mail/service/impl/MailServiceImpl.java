package com.lpq.mail.service.impl;

import com.lpq.mail.dao.MailInfoDao;
import com.lpq.mail.entity.MailInfoExample;
import com.lpq.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wei yuyaung
 * @date 2020.05.19 17:17
 */
@Service
public class MailServiceImpl implements MailService {

    private MailInfoDao mailInfoDao;

    @Autowired
    public MailServiceImpl(MailInfoDao mailInfoDao) {
        this.mailInfoDao = mailInfoDao;
    }

    @Override
    public boolean send() {
        return false;
    }

    @Override
    public boolean receiveMail(Integer userId) {
        //先删后存，防止数据库里面邮件重复
        MailInfoExample example = new MailInfoExample();
        example.createCriteria().andUserIdEqualTo(userId);
        mailInfoDao.deleteByExample(example);



        return false;
    }


}
