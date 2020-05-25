package com.lpq.mail.service.impl;

import com.lpq.mail.dao.LogInfoDao;
import com.lpq.mail.entity.LogInfo;
import com.lpq.mail.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wei yuyaung
 * @date 2020.05.25 15:17
 */
@Service
public class LogServiceImpl implements LogService {

    private LogInfoDao logInfoDao;

    @Autowired
    public LogServiceImpl(LogInfoDao logInfoDao) {
        this.logInfoDao = logInfoDao;
    }

    @Override
    public boolean insert(LogInfo logInfo) {
        return logInfoDao.insert(logInfo) > 0;
    }

    @Override
    public List<LogInfo> select() {
        return logInfoDao.selectByExample(null);
    }
}
