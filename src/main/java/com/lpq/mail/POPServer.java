package com.lpq.mail;

import com.lpq.mail.server.POPServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/23
 * 注释：null
 **/
public class POPServer {
    public static void main(String[] args){
        int popPort = 1524;
        try {
            ServerSocket popServer = new ServerSocket(popPort);
            Socket popSocket = null;
            while (true){
                popSocket = popServer.accept();
                new Thread(new POPServerHandler(popSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
