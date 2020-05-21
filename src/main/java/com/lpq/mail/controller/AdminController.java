package com.lpq.mail.controller;

import com.auth0.jwt.JWT;
import com.lpq.mail.annotations.PassToken;
import com.lpq.mail.annotations.UserLoginToken;
import com.lpq.mail.dto.LoginDTO;
import com.lpq.mail.entity.UserInfo;
import com.lpq.mail.exception.GlobalException;
import com.lpq.mail.result.BaseResult;
import com.lpq.mail.result.CodeMessage;
import com.lpq.mail.service.ManagerService;
import com.lpq.mail.vo.ChangeStateVO;
import com.lpq.mail.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Wei yuyaung
 * @date 2020.05.19 12:17
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    private ManagerService managerService ;
    @Autowired
    public AdminController(ManagerService managerService) {this.managerService = managerService; }

    @PassToken
    @PostMapping("login")
    public BaseResult<LoginDTO> login(@RequestBody LoginVO loginVO){
        try {
            String login = managerService.login(loginVO);
            return BaseResult.success(new LoginDTO(login));
        } catch (GlobalException e) {
            return BaseResult.fail(e.getCodeMessage());
        }
    }

    @UserLoginToken
    @GetMapping("userManage/list")
    public BaseResult<List<UserInfo>> viewUserList(){
        try{
            List<UserInfo> users = managerService.loadAllUser();
            return BaseResult.success(users);
        } catch (GlobalException e) {
            return BaseResult.fail(e.getCodeMessage());
        }
    }

    @UserLoginToken
    @PostMapping("userManage/changeState")
    public BaseResult<Void> changeUserState(@RequestBody ChangeStateVO changeStateVO, HttpServletRequest httpServletRequest){
        try{
            boolean success = managerService.changeState(changeStateVO.getUserId(),changeStateVO.getState());
            if(success){
                return BaseResult.success(null);
            }else{
                return BaseResult.fail(CodeMessage.CHANGE_STATE_ERROR);
            }
        } catch (GlobalException e) {
            return BaseResult.fail(e.getCodeMessage());
        }
    }
}
