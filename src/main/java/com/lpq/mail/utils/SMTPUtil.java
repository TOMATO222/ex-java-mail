package com.lpq.mail.utils;

import com.lpq.mail.entity.MailAccountInfo;
import com.lpq.mail.entity.MailInfo;
import com.lpq.mail.exception.GlobalException;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/20
 * 注释：null
 **/
public class SMTPUtil {
    public String SMTPserver(MailInfo mailInfo, MailAccountInfo mailAccountInfo){
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
            //握手过程
            String[] account = mailAccountInfo.getMailAccount().split("@");
            socketout.println("helo "+account[1]);
            re = socketin.readLine().substring(0,2);
            System.out.println(re);
            socketout.println("auth login");
            re = socketin.readLine().substring(0,2);
            System.out.println(re);
            Base64.Encoder encoder = Base64.getEncoder();
            account[0] = encoder.encodeToString(account[0].getBytes(StandardCharsets.UTF_8));
            socketout.println(account[0]);
            re = socketin.readLine().substring(0,2);
            System.out.println(re);
            String psw = mailAccountInfo.getMailPassword();
            psw = encoder.encodeToString(psw.getBytes(StandardCharsets.UTF_8));
            socketout.println(psw);
            re = socketin.readLine().substring(0,2);
            System.out.println(re);
            socketout.println("mail from: " + "<" + mailInfo.getFrom() + ">");
            re = socketin.readLine().substring(0,2);
            System.out.println(re);
            socketout.println("rcpt to: " + "<" + mailInfo.getTo() + ">");
            re = socketin.readLine().substring(0,2);
            System.out.println(re);
            socketout.println("data\r");
            System.out.println(socketin.readLine());
            re = socketin.readLine().substring(0,2);
            System.out.println(re);
            //发送正文
            socketout.println("subject:"+mailInfo.getSubject());
            socketout.println(mailInfo.getContent());
            socketout.println(".");
            re = socketin.readLine().substring(0,2);
            System.out.println(re);
            socketout.println("rset");
            socketout.println("quit");
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

}
