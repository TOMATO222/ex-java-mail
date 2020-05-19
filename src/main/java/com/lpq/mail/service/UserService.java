package com.lpq.mail.service;

import com.lpq.mail.entity.UserInfo;
import com.lpq.mail.exception.GlobalException;
import com.lpq.mail.vo.LoginVO;

public interface UserService {

    /**
     * description: 登录
     * @author: Wei Yuyang
     * @time: 2020.05.19
     */
    String login(LoginVO loginVO) throws GlobalException;

    /**
     * description: 修改用户密码
     * @author: Wei Yuyang
     * @time: 2020.05.19
     */
    boolean changePassword();

    /**
     * description: 修改用户状态
     * @author: Wei Yuyang
     * @time: 2020.05.19
     */
    boolean changeState();

    /**
     * description: 修改昵称
     * @author: Wei Yuyang
     * @time: 2020.05.19
     */
    boolean changeNickName();

    /**
     * description: 通过id获取用户信息
     * @author: Wei Yuyang
     * @time: 2020.05.20
     */
    UserInfo info(Integer userId);
}
