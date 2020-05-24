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
import org.springframework.web.bind.annotation.*;

/**
 * @author Wei yuyaung
 * @date 2020.05.23 11:49
 */
@CrossOrigin
@RestController
@RequestMapping("system")
public class SystemSettingController {
    private SystemService systemService ;
    @Autowired
    public SystemSettingController(SystemService systemService) {this.systemService = systemService; }

    @PostMapping("change")
    public BaseResult<String> settings(@RequestBody SystemSettingsVO settingsVO){
        try{
            boolean success = systemService.changeSystem(settingsVO);
            if(success){
                return BaseResult.success("修改成功");
            }else{
                return BaseResult.fail(CodeMessage.DEL_USER_FAILE);
            }
        } catch (GlobalException e) {
            return BaseResult.fail(e.getCodeMessage());
        }
    }

    @GetMapping("settings")
    public BaseResult<SystemSettingsVO> settings(){
        return BaseResult.success(systemService.getSettings());
    }
}
