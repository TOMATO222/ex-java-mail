package com.lpq.mail.controller;

import com.lpq.mail.annotations.PassToken;
import com.lpq.mail.result.BaseResult;
import com.lpq.mail.vo.SystemSettingsVO;
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


    @PassToken
    @PostMapping("change")
    public BaseResult<String> settings(SystemSettingsVO settingsVO){
        //todo 系统设置
        return BaseResult.success(null);
    }
}
