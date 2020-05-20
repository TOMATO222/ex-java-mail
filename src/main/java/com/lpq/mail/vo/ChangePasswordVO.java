package com.lpq.mail.vo;

import lombok.Data;

/**
 * @author Wei yuyaung
 * @date 2020.05.20 11:18
 */
@Data
public class ChangePasswordVO {
    String oldPassword;
    String newPassword;
}
