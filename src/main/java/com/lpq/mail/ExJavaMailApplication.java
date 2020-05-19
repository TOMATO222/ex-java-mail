package com.lpq.mail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Wei yuyaung
 * @date 2020.05.19 9:43
 */
@SpringBootApplication
@MapperScan("com.lpq.mail.dao")
public class ExJavaMailApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExJavaMailApplication.class,args);
    }
}
