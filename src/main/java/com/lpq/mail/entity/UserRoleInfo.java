package com.lpq.mail.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * user_role_info
 * @author 
 */
@Data
public class UserRoleInfo implements Serializable {
    private Integer id;

    private Integer userId;

    /**
     * 用户权限 0为普通用户 1为管理员 2为超级管理员，只有一个
     */
    private Integer userType;

    private static final long serialVersionUID = 1L;
}