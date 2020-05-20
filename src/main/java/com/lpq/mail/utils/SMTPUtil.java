package com.lpq.mail.utils;

import com.lpq.mail.entity.MailAccountInfo;
import com.lpq.mail.entity.MailInfo;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/20
 * 注释：null
 **/
public class SMTPUtil {
    public boolean SMTPserver(MailInfo mailInfo, MailAccountInfo mailAccountInfo){
        int port = Integer.valueOf(mailAccountInfo.getMailSmtpPort());
        String server = mailAccountInfo.getMailSmtpAddress();
        Socket client = null ;
        try{
            //建立连接与输入输出流
            client = new Socket(server,port);
            InputStream input = client.getInputStream();
            BufferedReader socketin = new BufferedReader(new InputStreamReader(input));
            OutputStream output = client.getOutputStream();
            PrintWriter socketout = new PrintWriter(output , true);
            //握手过程
            socketout.println("hello");
            socketin.readLine();
            socketout.println("auth login");
            socketin.readLine();
            socketout.println(mailAccountInfo.getMailAccount());
            socketin.readLine();
            socketout.println(mailAccountInfo.getMailPassword());
            socketin.readLine();
            socketout.println("mail from: " + "<" + mailInfo.getFrom() + ">");
            socketin.readLine();
            socketout.println("rcpt to: " + "<" + mailInfo.getTo() + ">");
            socketin.readLine();
            socketout.println("data");
            socketin.readLine();
            //发送正文
            socketout.println("subject:"+mailInfo.getSubject());
            socketout.println(mailInfo.getContent());
            socketout.println(".");
            socketout.println("rset");
            socketout.println("quit");
            client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true ;
    }

}
