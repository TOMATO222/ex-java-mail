package com.lpq.mail.service.impl;

import com.lpq.mail.dao.SystemParamsDao;
import com.lpq.mail.entity.SystemParams;
import com.lpq.mail.entity.SystemParamsExample;
import com.lpq.mail.exception.GlobalException;
import com.lpq.mail.service.SystemService;
import com.lpq.mail.vo.SystemSettingsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/23
 * 注释：null
 **/
@Service
public class SystemServiceImpl implements SystemService {
    private SystemParamsDao systemParamsDao ;
    @Autowired
    public SystemServiceImpl(SystemParamsDao systemParamsDao){
        this.systemParamsDao = systemParamsDao ;
    }

    @Override
    public boolean changeSystem(SystemSettingsVO systemSettingsVO) throws GlobalException {
        SystemParamsExample example = new SystemParamsExample();
        //popstatus
        example.createCriteria().andIdEqualTo(1);
        SystemParams systemParams = new SystemParams();
        systemParams.setId(1);
        systemParams.setDescription("pop3服务");
        systemParams.setName("pop3_state");
        systemParams.setValue(systemSettingsVO.getPop3Status());
        systemParamsDao.updateByExample(systemParams,example);
        example.clear();
        //popport
        example.createCriteria().andIdEqualTo(3);
        systemParams.setId(3);
        systemParams.setDescription("pop3端口");
        systemParams.setName("pop3_port");
        systemParams.setValue(systemSettingsVO.getPop3Port());
        systemParamsDao.updateByExample(systemParams,example);
        example.clear();
        //smtpstatus
        example.createCriteria().andIdEqualTo(2);
        systemParams.setId(2);
        systemParams.setDescription("smtp服务");
        systemParams.setName("smtp_state");
        systemParams.setValue(systemSettingsVO.getSmtpStatus());
        systemParamsDao.updateByExample(systemParams,example);
        example.clear();
        //smtpport
        example.createCriteria().andIdEqualTo(4);
        systemParams.setId(4);
        systemParams.setDescription("smtp端口");
        systemParams.setName("smtp_port");
        systemParams.setValue(systemSettingsVO.getSmtpPort());
        systemParamsDao.updateByExample(systemParams,example);
        example.clear();
        return true;
    }
}
