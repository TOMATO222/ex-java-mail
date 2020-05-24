package com.lpq.mail.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * ip_info
 * @author 
 */
@Data
public class IpInfo implements Serializable {
    /**
     * ip地址
     */
    private String ip;

    /**
     * 类型 0为黑名单 1为白名单
     */
    private Integer type;

    /**
     * 时间戳
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}