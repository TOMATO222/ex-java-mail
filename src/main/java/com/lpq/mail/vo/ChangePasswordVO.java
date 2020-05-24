package com.lpq.mail.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wei yuyaung
 * @date 2020.05.20 11:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordVO {
    private String oldPassword;
    private String newPassword;
}
