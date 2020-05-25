package com.lpq.mail.service;

import com.lpq.mail.entity.LogInfo;

import java.util.List;

public interface LogService {

    /**
     * description: 新增日志记录 <br>
     * version: 1.0 <br>
     * date: 2020.05.25 15:17 <br>
     * author: Dominikyang <br>
     *
     * @param logInfo
     * @return boolean
     */
    boolean insert(LogInfo logInfo);

    /**
     * description: 获取日志列表 <br>
     * version: 1.0 <br>
     * date: 2020.05.25 15:21 <br>
     * author: Dominikyang <br>
     *
     * @param
     * @return java.util.List<com.lpq.mail.entity.LogInfo>
     */
    List<LogInfo> select();
}
