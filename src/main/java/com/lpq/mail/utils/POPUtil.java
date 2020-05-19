package com.lpq.mail.utils;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/19
 * 注释：null
 **/
public class POPUtil {
    private Socket socket;
    private String server;
    private int port;

    public POPUtil(String server, int port) {
        this.server = server;
        this.port = port;
        try {
            this.socket = new Socket(server, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getReturn(BufferedReader in) {
        String line = null;
        try {
            line = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return line;
        }
    }

    public String getResult(String line) {
        StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
        return stringTokenizer.nextToken();
    }

    public String sendServer(String str, BufferedReader in, BufferedWriter out) throws IOException {
        out.write(str);
        out.newLine();
        out.flush();
        return getReturn(in);
    }

    public boolean user(String user) throws IOException {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String result = getResult(sendServer("user " + user, in, out));
            if (!"+OK".equals(result)) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean pass(String pwd) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String result = getResult(sendServer("pass " + pwd, in, out));
            if (!"+OK".equals(result)) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public int stat() {
        int mailNum = 0;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line = sendServer("stat", in, out);
            StringTokenizer st = new StringTokenizer(line, " ");
            String result = st.nextToken();
            if (st.hasMoreTokens()) {
                mailNum = Integer.parseInt(st.nextToken());
            } else {
                mailNum = 0;
            }
            if (!"+OK".equals(result)) {
                throw new IOException("查看邮箱状态出错!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return mailNum;
        }
    }
    public void list() {
        String message = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line = sendServer("list", in, out);
            while (!".".equalsIgnoreCase(line)) {
                message = message + line + "\n";
                line = in.readLine().toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void list(int mailNumber) throws IOException {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String result = getResult(sendServer("list " + mailNumber, in, out));
            if (!"+OK".equals(result)) {
                throw new IOException("list错误!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String retr(int mailNum) throws IOException, InterruptedException{
        String message = null ;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String result = getResult(sendServer("retr "+mailNum,in,out));
            if(!"+OK".equals(result)){
                throw new IOException("接收邮件出错!");
            }
            String line = null ;
            try{
                line = in.readLine().toString();
                while(!".".equalsIgnoreCase(line)){
                    message=message+line+"\n";
                    line=in.readLine().toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread.sleep(3000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return message;
    }
    public boolean quit(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String result = getResult(sendServer("QUIT",in,out));
            if(!"+OK".equals(result)){
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true ;
    }
}
