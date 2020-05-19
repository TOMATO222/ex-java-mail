package com.lpq.mail.service;

import java.io.UnsupportedEncodingException;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/19
 * 注释：null
 **/
public interface IdecodeService {
    String codeTransform (String text , String charset , String charout ) throws UnsupportedEncodingException;
}
