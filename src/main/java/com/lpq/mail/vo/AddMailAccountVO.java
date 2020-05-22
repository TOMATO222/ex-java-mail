package com.lpq.mail.vo;

import lombok.Data;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/22
 * 注释：null
 **/
@Data
public class AddMailAccountVO {
    private String mail_account ;
    private String mail_password;
    private String mail_pop_address;
    private String mail_pop_port;
    private String mail_smtp_address;
    private String mail_smtp_port;
}
