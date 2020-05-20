package com.lpq.mail.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * mail_account_info
 * @author 
 */
@Data
public class MailAccountInfo implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 邮箱账号
     */
    private String mailAccount;

    /**
     * 邮箱密码
     */
    private String mailPassword;

    /**
     * 邮箱的域名地址
     */
    private String mailAddress;

    /**
     * 端口号
     */
    private String mailPort;

    private static final long serialVersionUID = 1L;
}