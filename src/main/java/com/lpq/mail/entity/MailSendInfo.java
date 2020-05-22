package com.lpq.mail.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * mail_send_info
 * @author 
 */
@Data
public class MailSendInfo implements Serializable {
    private Integer id;

    private Integer userId;

    private String subject;

    private String from;

    private String content;

    private Date date;

    private static final long serialVersionUID = 1L;
}