package com.lpq.mail.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * log_info
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogInfo implements Serializable {
    /**
     * 日志id
     */
    private Integer id;

    /**
     * 操作名称
     */
    private String operationName;

    /**
     * 操作方法
     */
    private String method;

    /**
     * 日期
     */
    private Date date;

    private static final long serialVersionUID = 1L;

    public LogInfo(String operationName, String method) {
        this.operationName = operationName;
        this.method = method;
    }
}