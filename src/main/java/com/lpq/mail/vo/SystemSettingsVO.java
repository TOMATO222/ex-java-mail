package com.lpq.mail.vo;

import lombok.Data;

/**
 * @author Wei yuyaung
 * @date 2020.05.23 11:55
 */
@Data
public class SystemSettingsVO {
    private String pop3Port;
    private String pop3Status;
    private String smtpPort;
    private String smtpStatus;
}
