package serviceImpl;

import com.lpq.mail.service.IdecodeService;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/19
 * 注释：null
 **/
public class DecodeServiceImpl implements IdecodeService {
    @Override
    public String codeTransform(String text, String charset, String charout) throws UnsupportedEncodingException {
        //文本转二进制
        String Str = new String( text.getBytes(charset));
        //二进制base64解码  再转输出文本
        String result = new String(Base64.getDecoder().decode(Str),charout);
        return result;
    }
}
