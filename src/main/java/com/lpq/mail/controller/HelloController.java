package com.lpq.mail.controller;

import com.lpq.mail.result.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wei yuyaung
 * @date 2020.05.19 12:10
 */
@RestController
public class HelloController {
    @GetMapping("hello/{user}")
    public BaseResult<String> hello(@PathVariable String user){
        return BaseResult.success("傻逼，忘了活几把该");
    }
}
