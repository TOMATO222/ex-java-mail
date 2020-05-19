package com.lpq.mail.controller;

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
    public String hello(@PathVariable String user){
        return "hello " + user;
    }
}
