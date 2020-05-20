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
     * pop地址
     */
    private String mailPopAddress;

    /**
     * pop端口号
     */
    private String mailPopPort;

    /**
     * smtp地址
     */
    private String mailSmtpAddress;

    /**
     * smtp端口号
     */
    private String mailSmtpPort;

    private static final long serialVersionUID = 1L;
}