package com.lpq.mail.service;

import com.lpq.mail.exception.GlobalException;

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
    boolean send();

    /**
     * description: 读取邮件，存入数据库
     * @author: Wei Yuyang
     * @time: 2020.05.19
     */
    boolean receiveMail(Integer userId) throws GlobalException;
}
