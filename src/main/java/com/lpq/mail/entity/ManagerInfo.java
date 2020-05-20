package com.lpq.mail.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/20
 * 注释：null
 **/
@Data
public class ManagerInfo implements Serializable {
    /**
     * 用户id
     */
    private Integer user_id ;
    /**
     * 用户名
     */
    private String username ;
    /**
     * 密码
     */
    private String password ;
    /**
     * 昵称
     */
    private Integer role_id ;
    /**
     * 权限
     */
    private Integer user_type ;
}
