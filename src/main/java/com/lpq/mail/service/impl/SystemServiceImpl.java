package com.lpq.mail.service.impl;

import com.lpq.mail.entity.SystemParamsExample;
import com.lpq.mail.exception.GlobalException;
import com.lpq.mail.service.SystemService;
import com.lpq.mail.vo.SystemSettingsVO;
import org.springframework.stereotype.Service;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/23
 * 注释：null
 **/
@Service
public class SystemServiceImpl implements SystemService {
    @Override
    public boolean changeSystem(SystemSettingsVO systemSettingsVO) throws GlobalException {
        //todo 更改系统设置 需要Dao重建后再写
        return false;
    }
}
