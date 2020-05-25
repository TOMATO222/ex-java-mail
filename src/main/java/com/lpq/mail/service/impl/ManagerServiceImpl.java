package com.lpq.mail.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lpq.mail.dao.MailSendInfoDao;
import com.lpq.mail.dao.SystemParamsDao;
import com.lpq.mail.dao.UserInfoDao;
import com.lpq.mail.entity.*;
import com.lpq.mail.exception.GlobalException;
import com.lpq.mail.result.CodeMessage;
import com.lpq.mail.service.ManagerService;
import com.lpq.mail.vo.LoginVO;
import com.lpq.mail.vo.SystemSettingsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/20
 * 注释：null
 **/
@Service
public class ManagerServiceImpl implements ManagerService {
    private UserInfoDao userInfoDao;
    private MailSendInfoDao mailSendInfoDao;


    @Autowired
    public ManagerServiceImpl(UserInfoDao userInfoDao, MailSendInfoDao mailSendInfoDao) {
        this.userInfoDao = userInfoDao;
        this.mailSendInfoDao = mailSendInfoDao;
    }

    @Override
    public String login(LoginVO loginVO) throws GlobalException {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andUsernameEqualTo(loginVO.getUsername());
        List<UserInfo> userInfos = userInfoDao.selectByExample(example);
        if (userInfos.size() > 1) {
            throw new GlobalException(CodeMessage.DATABSE_ERROR);
        } else if (userInfos.size() == 0) {
            throw new GlobalException(CodeMessage.ERROR_USERNAME);
        }
        ManagerInfo managerInfo = userInfoDao.selectManager(userInfos.get(0).getUsername());
        if (managerInfo == null || managerInfo.getRole_id() < 1) {
            throw new GlobalException(CodeMessage.NOT_MANAGER);
        }
        if (userInfos.get(0).getPassword().equals(loginVO.getPassword())) {
            //jwt生成token
            return getToken(managerInfo);
        } else {
            throw new GlobalException(CodeMessage.ERROR_PASSWORD);
        }
    }

    public String getToken(ManagerInfo manager) {
        String token = "";
        token = JWT.create().withAudience(String.valueOf(manager.getUser_id()))
                .sign(Algorithm.HMAC256(manager.getPassword()));
        return token;
    }

    @Override
    public List<UserInfo> loadAllUser() throws GlobalException {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andIdIsNotNull();
        List<UserInfo> users = userInfoDao.selectByExample(example);
        return users;
    }

    @Override
    public boolean changeState(Integer userId) throws GlobalException {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andIdEqualTo(userId);
        List<UserInfo> users = userInfoDao.selectByExample(example);
        UserInfo user = users.get(0);
        if (user.getState() == 1) {
            user.setState(0);
            userInfoDao.updateByPrimaryKey(user);
        } else {
            user.setState(1);
            userInfoDao.updateByPrimaryKey(user);
        }
        return true;
    }

    @Override
    public List<MailSendInfo> loadAllMail() {
        MailSendInfoExample example = new MailSendInfoExample();
        example.createCriteria().andIdIsNotNull();
        List<MailSendInfo> mails = mailSendInfoDao.selectByExample(example);
        return mails;
    }
}
