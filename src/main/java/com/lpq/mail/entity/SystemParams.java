package com.lpq.mail.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * local_mail_info
 * @author 
 */
@Data
public class SystemParams implements Serializable {
    private Integer id;

    private Integer userId;

    private String subject;

    private String from;

    private String to;

    private String content;

    private Date date;

    private static final long serialVersionUID = 1L;
}