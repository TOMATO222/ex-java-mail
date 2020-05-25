package com.lpq.mail.service.impl;

import com.lpq.mail.dao.IpInfoDao;
import com.lpq.mail.entity.IpInfo;
import com.lpq.mail.exception.GlobalException;
import com.lpq.mail.result.CodeMessage;
import com.lpq.mail.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Wei yuyaung
 * @date 2020.05.25 2:14
 */
@Service
public class IpServiceImpl implements IpService {

    private IpInfoDao ipInfoDao;

    @Autowired
    public IpServiceImpl(IpInfoDao ipInfoDao) {
        this.ipInfoDao = ipInfoDao;
    }

    @Override
    public boolean add(IpInfo ipInfo) throws GlobalException {
        IpInfo ipInfo1 = ipInfoDao.selectByPrimaryKey(ipInfo.getIp());
        if(ipInfo1 == null){
            ipInfo.setUpdateTime(new Date());
            int insert = ipInfoDao.insertInfo(ipInfo);
            return insert > 0;
        }else {
            throw  new GlobalException(new CodeMessage(500,"当前ip已存在"));
        }
    }

    @Override
    public boolean delete(IpInfo ipInfo) {
        int i = ipInfoDao.deleteByPrimaryKey(ipInfo.getIp());
        return i > 0;
    }

    @Override
    public List<IpInfo> select() {
        return ipInfoDao.selectByExample(null);
    }
}
