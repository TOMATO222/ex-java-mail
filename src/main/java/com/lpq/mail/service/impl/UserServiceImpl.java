package com.lpq.mail.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lpq.mail.dao.UserInfoDao;
import com.lpq.mail.entity.UserInfo;
import com.lpq.mail.entity.UserInfoExample;
import com.lpq.mail.exception.GlobalException;
import com.lpq.mail.result.CodeMessage;
import com.lpq.mail.service.UserService;
import com.lpq.mail.vo.LoginVO;
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

    @Autowired
    public UserServiceImpl(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
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
    public boolean changePassword() {
        return false;
    }

    @Override
    public boolean changeState() {
        return false;
    }

    @Override
    public boolean changeNickName() {
        return false;
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
}
