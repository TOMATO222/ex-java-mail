package com.lpq.mail.utils;

import com.lpq.mail.entity.MailAccountInfo;
import com.lpq.mail.entity.MailInfo;
import com.lpq.mail.entity.MailSendInfo;
import com.lpq.mail.entity.UserInfoExample;
import com.lpq.mail.exception.GlobalException;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/20
 * 注释：null
 **/
public class SMTPUtil {
    public String MySmtpServer(MailSendInfo mailSendInfo , MailAccountInfo mailAccountInfo ){
        int port = Integer.valueOf(mailAccountInfo.getMailSmtpPort());
        String server = mailAccountInfo.getMailSmtpAddress();
        Socket client = null;
        String re = null ;
        try{
            //建立连接与输入输出流
            client = new Socket(server,port);
            InputStream input = client.getInputStream();
            BufferedReader socketin = new BufferedReader(new InputStreamReader(input));
            OutputStream output = client.getOutputStream();
            PrintWriter socketout = new PrintWriter(output , true);
            //发送正文
            socketout.println(mailAccountInfo.getUserId());
            socketout.println(mailSendInfo.getFrom());
            socketout.println(mailSendInfo.getTo());
            socketout.println(mailSendInfo.getSubject());
            socketout.println(mailSendInfo.getContent());
            re = socketin.readLine().substring(0,3);
            client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return re;
    }
    public String SMTPserver(MailSendInfo mailInfo, MailAccountInfo mailAccountInfo) {
        try{
            Properties props = new Properties();
            props.setProperty("mail.smtp.auth" , "true");
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.host", mailAccountInfo.getMailSmtpAddress());
            Session session = Session.getInstance(props);
            session.setDebug(true);
            //获取邮件实例
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mailInfo.getFrom()));
            msg.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(mailInfo.getTo()));
            msg.setSubject(mailInfo.getSubject());
            msg.setContent(mailInfo.getContent(),"text/html;charset=UTF-8");
            msg.setSentDate(new Date());
            //传输
            Message message = msg ;
            Transport transport = session.getTransport();
            transport.connect(mailAccountInfo.getMailAccount(),mailAccountInfo.getMailPassword());
            transport.sendMessage(msg,msg.getAllRecipients());
            transport.close();
            return "250 发送成功" ;
        } catch (AddressException e) {
            e.printStackTrace();
            return "554 发送失败" ;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return "554 发送失败" ;
        } catch (MessagingException e) {
            e.printStackTrace();
            return "554 发送失败" ;
        }
    }

}
