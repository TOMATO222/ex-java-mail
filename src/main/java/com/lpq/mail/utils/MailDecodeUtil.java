package com.lpq.mail.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author Wei yuyaung
 * @date 2020.05.19 17:59
 */
public class MailDecodeUtil {

    public String codeTransform(String text, String charset, String charget) throws UnsupportedEncodingException {
        //文本转二进制
        String str = null ;
        str = new String(text.getBytes(charset));
        //二进制base64解码  再转输出文本
        String result = new String(Base64.getDecoder().decode(str),charget);
        return result;
    }

}
