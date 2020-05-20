package com.lpq.mail.service;

import com.lpq.mail.entity.UserInfo;
import com.lpq.mail.exception.GlobalException;
import com.lpq.mail.vo.ChangePasswordVO;
import com.lpq.mail.vo.LoginVO;
import com.lpq.mail.vo.ModifyUserInfoVO;

public interface UserService {

    /**
     * description: 登录
     * @author: Wei Yuyang
     * @time: 2020.05.19
     */
    String login(LoginVO loginVO) throws GlobalException;


    /**
     * description: changePassword <br>
     * version: 1.0 <br>
     * date: 2020.05.20 12:01 <br>
     * author: Dominikyang <br>
     * 
     * @param userId
     * @param changePasswordVO
     * @return boolean
     */ 
    boolean changePassword(Integer userId, ChangePasswordVO changePasswordVO) throws GlobalException;



    /**
     * description: changeState <br>
     * version: 1.0 <br>
     * date: 2020.05.20 12:01 <br>
     * author: Dominikyang <br>
     * 
     * @param userId
     * @return boolean
     */ 
    boolean changeState(Integer userId);



    /**
     * description: changeNickName <br>
     * version: 1.0 <br>
     * date: 2020.05.20 12:07 <br>
     * author: Dominikyang <br>
     * 
     * @param userId
     * @param modifyUserInfoVO
     * @return boolean
     */ 
    boolean changeNickName(Integer userId, ModifyUserInfoVO modifyUserInfoVO) throws GlobalException;


    /**
     * description: 获取用户信息 <br>
     * version: 1.0 <br>
     * date: 2020.05.20 11:22 <br>
     * author: Dominikyang <br>
     *
     * @param userId
     * @return com.lpq.mail.entity.UserInfo
     */
    UserInfo info(Integer userId);
}
