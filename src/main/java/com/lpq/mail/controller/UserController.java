package com.lpq.mail.controller;

import com.auth0.jwt.JWT;
import com.lpq.mail.annotations.PassToken;
import com.lpq.mail.annotations.UserLoginToken;
import com.lpq.mail.dto.LoginDTO;
import com.lpq.mail.exception.GlobalException;
import com.lpq.mail.result.BaseResult;
import com.lpq.mail.result.CodeMessage;
import com.lpq.mail.service.UserService;
import com.lpq.mail.vo.ChangePasswordVO;
import com.lpq.mail.vo.LoginVO;
import com.lpq.mail.vo.ModifyUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Wei yuyaung
 * @date 2020.05.19 12:16
 */
@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * description: 用户登录接口 <br>
     * version: 1.0 <br>
     * date: 2020.05.20 11:08 <br>
     * author: Dominikyang <br>
     * 
     * @param loginVO
     * @return com.lpq.mail.result.BaseResult<com.lpq.mail.dto.LoginDTO>
     */ 
    @PassToken
    @PostMapping("login")
    public BaseResult<LoginDTO> login(LoginVO loginVO){
        try {
            String login = userService.login(loginVO);
            return BaseResult.success(new LoginDTO(login));
        } catch (GlobalException e) {
            return BaseResult.fail(e.getCodeMessage());
        }
    }

    /**
     * description: 修改用户昵称 <br>
     * version: 1.0 <br>
     * date: 2020.05.20 11:20 <br>
     * author: Dominikyang <br>
     *
     * @param modifyUserInfoVO
     * @return com.lpq.mail.result.BaseResult<java.lang.Void>
     */
    @UserLoginToken
    @PostMapping("change/nickname")
    public BaseResult<Void> changeNickName(@RequestBody ModifyUserInfoVO modifyUserInfoVO, HttpServletRequest httpServletRequest){
        try {
            boolean success = userService.changeNickName(Integer.valueOf(JWT.decode(httpServletRequest.getHeader("token")).getId()), modifyUserInfoVO);
            if(success){
                return BaseResult.success(null);
            }else {
                return BaseResult.fail(CodeMessage.CHANGE_NICKNAME_FAIL);
            }
        } catch (GlobalException e) {
            return BaseResult.fail(e.getCodeMessage());
        }
    }


}
