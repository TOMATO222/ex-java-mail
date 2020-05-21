package com.lpq.mail.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lpq.mail.dao.MailAccountInfoDao;
import com.lpq.mail.dao.MailInfoDao;
import com.lpq.mail.dao.UserInfoDao;
import com.lpq.mail.entity.*;
import com.lpq.mail.exception.GlobalException;
import com.lpq.mail.result.CodeMessage;
import com.lpq.mail.service.UserService;
import com.lpq.mail.vo.ChangePasswordVO;
import com.lpq.mail.vo.LoginVO;
import com.lpq.mail.vo.ModifyUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wei yuyaung
 * @date 2020.05.20 0:03
 */
@Service
public class UserServiceImpl implements UserService {

    private UserInfoDao userInfoDao;
    private MailAccountInfoDao mailAccountInfoDao;

    //用户锁定状态，state = 1
    public static final int USER_LOCKED = 1;

    @Autowired
    public UserServiceImpl(UserInfoDao userInfoDao, MailAccountInfoDao mailAccountInfoDao) {
        this.userInfoDao = userInfoDao;
        this.mailAccountInfoDao = mailAccountInfoDao;
    }

    @Override
    public String login(LoginVO loginVO) throws GlobalException {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andUsernameEqualTo(loginVO.getUsername());
        List<UserInfo> userInfos = userInfoDao.selectByExample(example);
        if(userInfos.size() > 1){
            throw new GlobalException(CodeMessage.DATABSE_ERROR);
        }else if(userInfos.size() == 0){
            throw new GlobalException(CodeMessage.ERROR_USERNAME);
        }

        if(userInfos.get(0).getPassword().equals(loginVO.getPassword())){
            //jwt生成token
            return getToken(userInfos.get(0));
        }else {
            throw new GlobalException(CodeMessage.ERROR_PASSWORD);
        }
    }


    @Override
    public boolean changePassword(Integer userId, ChangePasswordVO changePasswordVO) throws GlobalException {
        UserInfo userInfo = userInfoDao.selectByPrimaryKey(userId);
        if(userInfo == null){
            //用户不存在，并发操作可能删除了用户
            throw new GlobalException(CodeMessage.USER_NOT_EXIST);
        }

        if(userInfo.getState() == USER_LOCKED){
            //账户被锁定
            throw new GlobalException(CodeMessage.USER_LOCKED);
        }
        if (userInfo.getPassword().equals(changePasswordVO.getOldPassword())){
            userInfo.setPassword(changePasswordVO.getNewPassword());
            int i = userInfoDao.updateByPrimaryKeySelective(userInfo);
            return i > 0;
        }else {
            throw new GlobalException(CodeMessage.ERROR_PASSWORD);
        }
    }

    @Override
    public boolean changeState(Integer userId) {
        return false;
    }


    @Override
    public boolean changeNickName(Integer userId, ModifyUserInfoVO modifyUserInfoVO) throws GlobalException {
        UserInfo userInfo = userInfoDao.selectByPrimaryKey(userId);
        if(userInfo == null){
            throw new GlobalException(CodeMessage.USER_NOT_EXIST);
        }
        userInfo.setNickName(modifyUserInfoVO.getNick_name());
        int i = userInfoDao.updateByPrimaryKeySelective(userInfo);
        return i > 0;
    }

    @Override
    public UserInfo info(Integer userId) {
        return userInfoDao.selectByPrimaryKey(userId);
    }

    public String getToken(UserInfo user) {
        String token="";
        token= JWT.create().withAudience(String.valueOf(user.getId()))
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    @Override
    public List<MailAccountInfo> mailInfo(Integer userId) throws GlobalException {
        MailAccountInfoExample example = new MailAccountInfoExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<MailAccountInfo> mailInfos = mailAccountInfoDao.selectByExample(example);
        if(mailInfos.size() < 1){
            throw new GlobalException(CodeMessage.EMPTY_USER_MAIL_LIST);
        }
        return mailInfos;
    }
}
