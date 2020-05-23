package com.lpq.mail.service;

import com.lpq.mail.entity.MailAccountInfo;
import com.lpq.mail.entity.MailInfo;
import com.lpq.mail.entity.MailSendInfo;
import com.lpq.mail.exception.GlobalException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author Wei yuyaung
 * @date 2020.05.19 12:27
 */
public interface MailService {
    /**
     * description: 发送邮件
     * @author: Wei Yuyang
     * @time: 2020.05.19
     */
    String send(MailSendInfo mailInfo) throws GlobalException;

    /**
     * description: 读取邮件，存入数据库
     * @author: Wei Yuyang
     * @time: 2020.05.19
     */
    boolean receiveMail(Integer userId) throws GlobalException, InterruptedException, ParseException, IOException;

    List<MailInfo> takeMail(Integer userId) throws GlobalException;

    List<MailSendInfo> takeSendMail(Integer userId) throws GlobalException;
    
    /**
     * description: 删除邮件 <br>
     * version: 1.0 <br>
     * date: 2020.05.23 13:18 <br>
     * author: Dominikyang <br>
     * 
     * @param userId
     * @return boolean
     */ 
    boolean deleteMail(Integer userId);
}
