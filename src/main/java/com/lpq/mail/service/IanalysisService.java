package com.lpq.mail.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/19
 * 注释：null
 **/
public interface IanalysisService {
    String from();
    String to();
    Timestamp date() throws ParseException;
    String subjectCharset() ;
    String subjectText();
    String bodyCharset();
    String bodyText();
}
