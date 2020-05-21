package com.lpq.mail.vo;

import lombok.Data;

import java.util.Date;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/21
 * 注释：null
 **/
@Data
public class MailVO {
    private String subject;
    private String from;
    private String to;
    private String content;

}
