package com.lpq.mail;

import com.lpq.mail.server.POPServerHandler;
import com.lpq.mail.server.SMTPServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/23
 * 注释：null
 **/
public class SMTPServer {
    public static void main(String[] args){
        int smtpPort = 4251;
        try {
            ServerSocket smtpServer = new ServerSocket(smtpPort);
            Socket smtpSocket = null;
            while (true){
                smtpSocket = smtpServer.accept();
                new Thread(new SMTPServerHandler(smtpSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
