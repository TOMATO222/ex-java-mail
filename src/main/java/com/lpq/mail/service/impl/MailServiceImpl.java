package com.lpq.mail.service.impl;

import com.lpq.mail.dao.MailAccountInfoDao;
import com.lpq.mail.dao.MailInfoDao;
import com.lpq.mail.entity.MailAccountInfo;
import com.lpq.mail.entity.MailAccountInfoExample;
import com.lpq.mail.entity.MailInfo;
import com.lpq.mail.entity.MailInfoExample;
import com.lpq.mail.exception.GlobalException;
import com.lpq.mail.result.CodeMessage;
import com.lpq.mail.service.MailService;
import com.lpq.mail.utils.MailAnalyseUtil;
import com.lpq.mail.utils.MailDecodeUtil;
import com.lpq.mail.utils.POPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author Wei yuyaung
 * @date 2020.05.19 17:17
 */
@Service
public class MailServiceImpl implements MailService {

    private MailInfoDao mailInfoDao;
    private MailAccountInfoDao mailAccountInfoDao;

    @Autowired
    public MailServiceImpl(MailInfoDao mailInfoDao) {
        this.mailInfoDao = mailInfoDao;
    }

    @Override
    public boolean send() {
        return false;
    }

    @Override
    public boolean receiveMail(Integer userId) throws GlobalException {
        //先删后存，防止数据库里面邮件重复
        MailInfoExample example = new MailInfoExample();
        example.createCriteria().andUserIdEqualTo(userId);
        mailInfoDao.deleteByExample(example);
        example.clear();
        //获取用户所有邮箱账号
        MailAccountInfoExample accountInfoExample = new MailAccountInfoExample() ;
        accountInfoExample.createCriteria().andUserIdEqualTo(userId);
        List<MailAccountInfo> accountInfoList = mailAccountInfoDao.selectByExample(accountInfoExample);
        accountInfoExample.clear();
        //没有邮箱账号直接返回
        if(accountInfoList.isEmpty()){
            throw new GlobalException(CodeMessage.NO_MAILBOX);
        }
        //获取每个邮箱中 每一封邮件并写入数据库
        //定义工具
        MailAnalyseUtil mailAnalyseUtil = new MailAnalyseUtil();
        MailDecodeUtil mailDecodeUtil = new MailDecodeUtil();
        //遍历邮箱账号
        for (MailAccountInfo mailaccount: accountInfoList) {
            POPUtil popUtil = new POPUtil(mailaccount.getMailPopAddress(),Integer.valueOf(mailaccount.getMailPopPort()));
            try{
                popUtil.user(mailaccount.getMailAccount());
                popUtil.pass(mailaccount.getMailPassword());
                int mailnum = popUtil.stat();
                popUtil.list();
                //读取每一封邮件 并分析插入
                for(int i=0 ;i<mailnum;i++){
                    String message = popUtil.retr(i+1) ;
                    MailInfo mailInfo = new MailInfo();
                    mailInfo.setUserId(userId);
                    mailInfo.setFrom(mailAnalyseUtil.from(message));
                    mailInfo.setTo(mailAnalyseUtil.to(message));
                    mailInfo.setDate(mailAnalyseUtil.date(message));
                    String charset = mailAnalyseUtil.subjectCharset(message);
                    mailInfo.setSubject(mailDecodeUtil.codeTransform(mailAnalyseUtil.subjectText(message),charset,"gbk"));
                    charset = mailAnalyseUtil.bodyCharset(message);
                    mailInfo.setContent(mailDecodeUtil.codeTransform(mailAnalyseUtil.bodyText(message),charset,"gbk"));
                    mailInfoDao.insert(mailInfo);
                }
            } catch (IOException | InterruptedException | ParseException e) {
                e.printStackTrace();
                throw new GlobalException(CodeMessage.POP_ERROR) ;
            }
        }
        return true ;
    }


}
