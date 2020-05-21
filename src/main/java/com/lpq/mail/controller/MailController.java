package com.lpq.mail.controller;

import com.auth0.jwt.JWT;
import com.lpq.mail.annotations.UserLoginToken;
import com.lpq.mail.entity.MailAccountInfo;
import com.lpq.mail.entity.MailInfo;
import com.lpq.mail.exception.GlobalException;
import com.lpq.mail.result.BaseResult;
import com.lpq.mail.result.CodeMessage;
import com.lpq.mail.service.MailService;
import com.lpq.mail.service.ManagerService;
import com.lpq.mail.vo.MailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Wei yuyaung
 * @date 2020.05.19 12:17
 */
@RestController
@RequestMapping("mail")
public class MailController {
    private MailService mailService ;

    @Autowired
    public MailController(MailService mailService){
        this.mailService = mailService ;
    }

    @UserLoginToken
    @PostMapping("/send")
    public BaseResult<Void> sendMail(@RequestBody MailVO mailVO , HttpServletRequest httpServletRequest){
        MailInfo mailInfo = new MailInfo() ;
        int userId = Integer.parseInt(JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0));
        mailInfo.setUserId(userId);
        mailInfo.setFrom(mailVO.getFrom());
        mailInfo.setContent(mailVO.getContent());
        mailInfo.setTo(mailVO.getTo());
        mailInfo.setSubject(mailVO.getSubject());

        try{
            boolean success = mailService.send(mailInfo);
            if(success){
                return BaseResult.success(null) ;
            }else{
                return BaseResult.fail(CodeMessage.SEND_MAIL_ERROR);
            }
        } catch (GlobalException e) {
            return BaseResult.fail(e.getCodeMessage());
        }
    }

    @UserLoginToken
    @GetMapping("/list")
    public BaseResult<List<MailInfo>> receiveMail(HttpServletRequest httpServletRequest){
        try{
            int userId = Integer.parseInt(JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0));
            boolean success = mailService.receiveMail(userId);
            if(success){
                List<MailInfo> mails = mailService.takeMail(userId);
                if(mails.size()<1){
                    return BaseResult.success(null);
                }
                return BaseResult.success(mails);
            }else{
                return BaseResult.fail(CodeMessage.GET_MAIL_ERROR);
            }
        } catch (GlobalException e) {
            return BaseResult.fail(e.getCodeMessage());
        }

    }
}
