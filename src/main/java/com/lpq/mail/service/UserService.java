package com.lpq.mail.service;

import com.lpq.mail.entity.MailAccountInfo;
import com.lpq.mail.entity.MailInfo;
import com.lpq.mail.entity.UserInfo;
import com.lpq.mail.exception.GlobalException;
import com.lpq.mail.vo.*;

import java.util.List;

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


    /**
     * description: 根据账号查询用户信息 <br>
     * version: 1.0 <br>
     * date: 2020.05.25 1:48 <br>
     * author: Dominikyang <br>
     *
     * @param username
     * @return com.lpq.mail.entity.UserInfo
     */
    UserInfo info(String username) throws GlobalException;


    /**
     * description: mailInfo <br>
     * version: 1.0 <br>
     * date: 2020.05.21 14:35 <br>
     * author: Dominikyang <br>
     * 
     * @param userId
     * @return java.util.List<com.lpq.mail.entity.MailAccountInfo>
     */ 
    List<MailAccountInfo> mailInfo(Integer userId) throws GlobalException;

    /**
     * description: 添加邮箱 <br>
     * version: 1.0 <br>
     * date: 2020.05.23 11:24 <br>
     * author: Dominikyang <br>
     *
     * @param userId
     * @param addMailAccountVO
     * @return boolean
     */
    boolean addMailAccount(Integer userId , AddMailAccountVO addMailAccountVO) throws GlobalException;

    /**
     * description: 新增用户 <br>
     * version: 1.0 <br>
     * date: 2020.05.23 11:25 <br>
     * author: Dominikyang <br>
     *
     * @param registerVO
     * @return boolean
     */
    boolean addUser(RegisterVO registerVO) throws GlobalException;

    /**
     * description: 删除用户 <br>
     * version: 1.0 <br>
     * date: 2020.05.23 13:17 <br>
     * author: Dominikyang <br>
     * 
     * @param userId
     * @return boolean
     */ 
    boolean deleteUser(Integer userId);

    /**
     * description: 修改用户角色 <br>
     * version: 1.0 <br>
     * date: 2020.05.25 1:24 <br>
     * author: Dominikyang <br>
     *
     * @param userId
     * @return boolean
     */
    boolean changeRole(Integer userId) throws GlobalException;

    /**
     * description: 修改用户基本信息 <br>
     * version: 1.0 <br>
     * date: 2020.05.25 1:59 <br>
     * author: Dominikyang <br>
     *
     * @param userInfo
     * @return boolean
     */
    boolean modifyUserMessage(UserInfo userInfo);
}