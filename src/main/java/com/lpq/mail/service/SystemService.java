package com.lpq.mail.service;

import com.lpq.mail.exception.GlobalException;
import com.lpq.mail.vo.SystemSettingsVO;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/23
 * 注释：null
 **/
public interface SystemService {
    /**
     * 创建人：肖易安
     * 创建时间：  2020/5/20
     * 注释：修改系统信息
     **/
    boolean changeSystem(SystemSettingsVO systemSettingsVO) throws GlobalException;
}
