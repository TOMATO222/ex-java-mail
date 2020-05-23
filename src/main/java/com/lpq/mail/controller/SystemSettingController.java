package com.lpq.mail.controller;

import com.lpq.mail.annotations.PassToken;
import com.lpq.mail.exception.GlobalException;
import com.lpq.mail.result.BaseResult;
import com.lpq.mail.result.CodeMessage;
import com.lpq.mail.service.ManagerService;
import com.lpq.mail.service.SystemService;
import com.lpq.mail.service.UserService;
import com.lpq.mail.vo.SystemSettingsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wei yuyaung
 * @date 2020.05.23 11:49
 */
@RestController
@RequestMapping("system")
public class SystemSettingController {
    private SystemService systemService ;
    @Autowired
    public SystemSettingController(SystemService systemService) {this.systemService = systemService; }

    @PassToken
    @PostMapping("change")
    public BaseResult<String> settings(SystemSettingsVO settingsVO){
        try{
            boolean success = systemService.changeSystem(settingsVO);
            if(success){
                return BaseResult.success("删除成功");
            }else{
                return BaseResult.fail(CodeMessage.DEL_USER_FAILE);
            }
        } catch (GlobalException e) {
            return BaseResult.fail(e.getCodeMessage());
        }
    }
}
