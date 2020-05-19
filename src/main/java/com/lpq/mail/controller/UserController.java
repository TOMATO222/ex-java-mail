package com.lpq.mail.controller;

import com.lpq.mail.dto.LoginDTO;
import com.lpq.mail.result.BaseResult;
import com.lpq.mail.vo.LoginVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wei yuyaung
 * @date 2020.05.19 12:16
 */
@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping("login")
    public BaseResult<LoginDTO> login(@RequestBody LoginVO loginVO){
        return BaseResult.success(new LoginDTO());
    }

}
