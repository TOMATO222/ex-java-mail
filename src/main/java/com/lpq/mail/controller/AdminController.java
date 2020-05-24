package com.lpq.mail.controller;

import com.lpq.mail.annotations.PassToken;
import com.lpq.mail.dto.LoginDTO;
import com.lpq.mail.entity.IpInfo;
import com.lpq.mail.entity.MailSendInfo;
import com.lpq.mail.entity.UserInfo;
import com.lpq.mail.exception.GlobalException;
import com.lpq.mail.result.BaseResult;
import com.lpq.mail.result.CodeMessage;
import com.lpq.mail.service.IpService;
import com.lpq.mail.service.ManagerService;
import com.lpq.mail.service.UserService;
import com.lpq.mail.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Wei yuyaung
 * @date 2020.05.19 12:17
 */
@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {
    private ManagerService managerService ;
    private UserService userService;
    private IpService ipService;


    @Autowired
    public AdminController(ManagerService managerService, UserService userService, IpService ipService) {
        this.managerService = managerService;
        this.userService = userService;
        this.ipService = ipService;
    }

    /**
     * description: 管理员登录 <br>
     * version: 1.0 <br>
     * date: 2020.05.24 13:41 <br>
     * author: Dominikyang <br>
     * 
     * @param loginVO
     * @return com.lpq.mail.result.BaseResult<com.lpq.mail.dto.LoginDTO>
     */ 
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

    /**
     * description: 用户列表 <br>
     * version: 1.0 <br>
     * date: 2020.05.24 13:34 <br>
     * author: Dominikyang <br>
     * 
     * @param 
     * @return com.lpq.mail.result.BaseResult<java.util.List<com.lpq.mail.entity.UserInfo>>
     */ 
    @GetMapping("userManage/list")
    public BaseResult<List<UserInfo>> viewUserList(){
        try{
            List<UserInfo> users = managerService.loadAllUser();
            return BaseResult.success(users);
        } catch (GlobalException e) {
            return BaseResult.fail(e.getCodeMessage());
        }
    }

    /**
     * description: 用户账户加锁解锁 <br>
     * version: 1.0 <br>
     * date: 2020.05.24 13:33 <br>
     * author: Dominikyang <br>
     * 
     * @param changeStateVO
     * @param httpServletRequest
     * @return com.lpq.mail.result.BaseResult<java.lang.Void>
     */ 
    @PostMapping("userManage/changeState")
    public BaseResult<String> changeUserState(@RequestBody ChangeStateVO changeStateVO, HttpServletRequest httpServletRequest){
        try{
            boolean success = managerService.changeState(changeStateVO.getUserId());
            if(success){
                return BaseResult.success("修改成功");
            }else{
                return BaseResult.fail(CodeMessage.CHANGE_STATE_ERROR);
            }
        } catch (GlobalException e) {
            return BaseResult.fail(e.getCodeMessage());
        }
    }

    /**
     * description: 获取发件箱列表 <br>
     * version: 1.0 <br>
     * date: 2020.05.24 13:33 <br>
     * author: Dominikyang <br>
     * 
     * @param 
     * @return com.lpq.mail.result.BaseResult<java.util.List<com.lpq.mail.entity.MailSendInfo>>
     */ 
    @GetMapping("mailManage/list")
    public BaseResult<List<MailSendInfo>> viewMailList(){
        List<MailSendInfo> mails = managerService.loadAllMail() ;
        return BaseResult.success(mails);
    }

    /**
     * description: 删除用户 <br>
     * version: 1.0 <br>
     * date: 2020.05.24 13:33 <br>
     * author: Dominikyang <br>
     * 
     * @param deleteUserVO
     * @return com.lpq.mail.result.BaseResult<java.lang.String>
     */ 
    @PostMapping("userManage/del")
    public BaseResult<String> deleteUser(@RequestBody DeleteUserVO deleteUserVO){
        boolean b = userService.deleteUser(deleteUserVO.getUserId());
        if(b){
            return BaseResult.success("删除成功");
        }else{
            return BaseResult.fail(CodeMessage.DEL_USER_FAILE);
        }
    }

    /**
     * description: 管理员注册 <br>
     * version: 1.0 <br>
     * date: 2020.05.25 1:35 <br>
     * author: Dominikyang <br>
     *
     * @param registerVO
     * @return com.lpq.mail.result.BaseResult<java.lang.String>
     */
    @PostMapping("register")
    public BaseResult<String> register(@RequestBody RegisterVO registerVO){
        boolean b = false;
        try {
            b = userService.addUser(registerVO);
            if(b){
                UserInfo info = userService.info(registerVO.getUsername());
                boolean role = userService.changeRole(info.getId());
                if(role){
                    return BaseResult.success("创建用户成功");
                }else {
                    return BaseResult.fail(new CodeMessage(500,"新增用户失败"));
                }
            }else {
                return BaseResult.fail(new CodeMessage(500,"新增用户失败"));
            }
        } catch (GlobalException e) {
            e.printStackTrace();
            return BaseResult.fail(e.getCodeMessage());
        }
    }


    @PostMapping("userManage/password")
    public BaseResult<String> changePassword(@RequestBody AdminChangePwdVO adminChangePwdVO){
        try {
            boolean b = userService.changePassword(adminChangePwdVO.getUserId(), new ChangePasswordVO(adminChangePwdVO.getOldPassword(), adminChangePwdVO.getNewPassword()));
            if(b){
                return BaseResult.success("修改成功");
            }else {
                return BaseResult.fail(new CodeMessage(500,"修改失败"));
            }
        } catch (GlobalException e) {
            e.printStackTrace();
            return BaseResult.fail(e.getCodeMessage());
        }
    }
    
    /**
     * description: 修改用户信息 <br>
     * version: 1.0 <br>
     * date: 2020.05.25 2:04 <br>
     * author: Dominikyang <br>
     * 
     * @param userInfo
     * @return com.lpq.mail.result.BaseResult<java.lang.String>
     */ 
    @PostMapping("userManage/info")
    public BaseResult<String> modifyInfos(@RequestBody UserInfo userInfo){
        boolean b = userService.modifyUserMessage(userInfo);
        if(b){
            return BaseResult.success("修改成功");
        }else {
            return BaseResult.fail(new CodeMessage(500,"修改失败"));
        }
    }

    /**
     * description: 获取ip列表 <br>
     * version: 1.0 <br>
     * date: 2020.05.25 2:22 <br>
     * author: Dominikyang <br>
     *
     * @param
     * @return com.lpq.mail.result.BaseResult<java.util.List<com.lpq.mail.entity.IpInfo>>
     */
    @GetMapping("ip/list")
    public BaseResult<List<IpInfo>> ipInfoList(){
        List<IpInfo> select = ipService.select();
        return BaseResult.success(select);
    }

    @PostMapping("ip/add")
    public BaseResult<String> ipAdd(@RequestBody IpInfo ipInfo){
        try {
            boolean add = ipService.add(ipInfo);
            if(add){
                return BaseResult.success("添加规则成功");
            }else {
                return BaseResult.fail(new CodeMessage(500,"添加规则失败"));
            }
        } catch (GlobalException e) {
            e.printStackTrace();
            return BaseResult.fail(e.getCodeMessage());
        }
    }

    @PostMapping("ip/del")
    public BaseResult<String> ipDel(@RequestBody IpInfo ipInfo){
        boolean delete = ipService.delete(ipInfo);
        if(add){
            return BaseResult.success("删除规则成功");
        }else {
            return BaseResult.fail(new CodeMessage(500,"删除规则失败"));
        }
    }
}
