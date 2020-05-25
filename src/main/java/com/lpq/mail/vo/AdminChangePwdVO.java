package com.lpq.mail.vo;

import lombok.Data;

/**
 * @author Wei yuyaung
 * @date 2020.05.25 1:47
 */
@Data
public class AdminChangePwdVO {
    private String oldPassword;
    private String newPassword;
}
