package com.lpq.mail.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * user_info
 * @author 
 */
@Data
public class UserInfo implements Serializable {
    /**
     * 主键id，自动递增
     */
    private Integer id;

    /**
     * 用户登录账号
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 账号状态
     */
    private Integer state;

    private static final long serialVersionUID = 1L;
}