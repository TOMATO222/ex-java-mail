package com.lpq.mail.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * mail_info
 * @author 
 */
@Data
public class MailInfo implements Serializable {
    /**
     * 邮件id
     */
    private Integer id;

    /**
     * 邮件所属的用户
     */
    private Integer userId;

    /**
     * 主题
     */
    private String subject;

    /**
     * 发送方
     */
    private String from;

    /**
     * 接收方
     */
    private String to;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 邮件日期
     */
    private Date date;

    private static final long serialVersionUID = 1L;
}