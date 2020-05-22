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
import com.lpq.mail.utils.SMTPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
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
    public MailServiceImpl(MailInfoDao mailInfoDao , MailAccountInfoDao mailAccountInfoDao) {
        this.mailInfoDao = mailInfoDao;
        this.mailAccountInfoDao = mailAccountInfoDao ;
    }

    @Override
    public String send(MailInfo mailInfo)throws GlobalException {
        SMTPUtil smtp = new SMTPUtil();
        MailAccountInfoExample example = new MailAccountInfoExample();
        example.createCriteria().andMailAccountEqualTo(mailInfo.getFrom()).andUserIdEqualTo(mailInfo.getUserId());
        List<MailAccountInfo> mailAccountInfo = mailAccountInfoDao.selectByExample(example);
        example.clear();
        if(mailAccountInfo.size()!=1){
            throw new GlobalException(CodeMessage.NO_MAILBOX);
        }
        String success = smtp.SMTPserver(mailInfo,mailAccountInfo.get(0));
        return success ;
    }

    @Override
    @Transactional
    public boolean receiveMail(Integer userId) throws GlobalException, InterruptedException, ParseException, IOException {
        //先删后存，防止数据库里面邮件重复
        MailInfoExample example = new MailInfoExample();
        example.createCriteria().andUserIdEqualTo(userId);
        mailInfoDao.deleteByExample(example);
        example.clear();
        //获取用户所有邮箱账号
        MailAccountInfoExample accountInfoExample = new MailAccountInfoExample() ;
        accountInfoExample.createCriteria().andUserIdEqualTo(userId);
        List<MailAccountInfo> accountInfoList = null ;
        accountInfoList = mailAccountInfoDao.selectByExample(accountInfoExample);
        accountInfoExample.clear();
        //没有邮箱账号直接返回
        if(accountInfoList.isEmpty()){
            throw new GlobalException(CodeMessage.NO_MAILBOX);
        }
        //遍历邮箱账号
        for (MailAccountInfo mailaccount: accountInfoList) {
            POPUtil popUtil = new POPUtil();
            try{
                List<MailInfo> mails = popUtil.POPServer(mailaccount);
                for (MailInfo m : mails) {
                    mailInfoDao.insert(m);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw e;
            } catch (ParseException e) {
                throw e;
            }
        }
        return true ;
    }

    @Override
    public List<MailInfo> takeMail(Integer userId) throws GlobalException {
        try{
            MailInfoExample example = new MailInfoExample();
            example.createCriteria().andUserIdEqualTo(userId);
            List<MailInfo> mails = mailInfoDao.selectByExample(example);
            return mails ;
        } catch (Exception e) {
            throw e;
        }
    }
}
