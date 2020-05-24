package com.lpq.mail.service;

import com.lpq.mail.entity.IpInfo;
import com.lpq.mail.exception.GlobalException;

import java.util.List;

public interface IpService {

    /**
     * description: 新增ip表 <br>
     * version: 1.0 <br>
     * date: 2020.05.25 2:15 <br>
     * author: Dominikyang <br>
     *
     * @param ipInfo
     * @return boolean
     */
    boolean add(IpInfo ipInfo) throws GlobalException;

    /**
     * description: 删除ip表数据项 <br>
     * version: 1.0 <br>
     * date: 2020.05.25 2:16 <br>
     * author: Dominikyang <br>
     *
     * @param ipInfo
     * @return boolean
     */
    boolean delete(IpInfo ipInfo);

    /**
     * description: 获取ip表 <br>
     * version: 1.0 <br>
     * date: 2020.05.25 2:16 <br>
     * author: Dominikyang <br>
     *
     * @param
     * @return java.util.List<com.lpq.mail.entity.IpInfo>
     */
    List<IpInfo> select();
}
