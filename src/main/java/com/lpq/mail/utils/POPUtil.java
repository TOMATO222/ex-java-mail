package com.lpq.mail.utils;

import com.lpq.mail.dao.MailInfoDao;
import com.lpq.mail.entity.MailAccountInfo;
import com.lpq.mail.entity.MailInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/19
 * 注释：null
 **/
public class POPUtil {
    public List<MailInfo> MyPopServer(MailAccountInfo mailAccountInfo) throws IOException, ParseException {
        List<MailInfo> mails = new ArrayList<MailInfo>() ;
        int port = Integer.valueOf(mailAccountInfo.getMailPopPort());
        String server = mailAccountInfo.getMailPopAddress();
        Socket socket = null ;
        try{
            socket = new Socket(server,port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            sendServer("pop " + mailAccountInfo.getMailAccount()+" "+mailAccountInfo.getMailPassword(), out);
            String line = null ;
            do{
                line = getReturn(in);
                if(!line.substring(0,3).equals("250")){
                    break ;
                }
                MailInfo mailInfo = new MailInfo();
                mailInfo.setUserId(mailAccountInfo.getUserId());
                line = getReturn(in);
                mailInfo.setFrom(line);
                line = getReturn(in);
                mailInfo.setTo(line);
                line = getReturn(in);
                mailInfo.setSubject(line);
                line = getReturn(in);
                mailInfo.setContent(line);
                line = getReturn(in);
                SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                Date date = sdf1.parse(line) ;
                mailInfo.setDate(date);
                if(!(mailInfo.getFrom() == null || mailInfo.getFrom().length()<1)){
                    mails.add(mailInfo);
                }
            }
            while(!"...".equals(line));
            socket.close();
            return mails;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (ParseException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<MailInfo> POPServer(MailAccountInfo mailAccountInfo) throws IOException, InterruptedException, ParseException {
        List<MailInfo> mails = new ArrayList<>();
        int port = Integer.valueOf(mailAccountInfo.getMailPopPort());
        String server = mailAccountInfo.getMailPopAddress();
        Socket socket = null ;
        //定义工具
        MailAnalyseUtil mailAnalyseUtil = new MailAnalyseUtil();
        MailDecodeUtil mailDecodeUtil = new MailDecodeUtil();
        try{
            socket = new Socket(server,port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            user(mailAccountInfo.getMailAccount(),in,out);
            pass(mailAccountInfo.getMailPassword(),in,out);
            int mailNum = stat(in,out);
            list(in,out);
            for(int i=1 ; i<=mailNum ;i++){
                String message = retr(i,in,out);
                MailInfo mailInfo = new MailInfo();
                mailInfo.setUserId(mailAccountInfo.getUserId());
                mailInfo.setFrom(mailAnalyseUtil.from(message));
                mailInfo.setTo(mailAnalyseUtil.to(message));
                mailInfo.setDate(mailAnalyseUtil.date(message));
                String charset = mailAnalyseUtil.subjectCharset(message);
                if(charset == null || charset.length()==0){
                    mailInfo.setSubject(mailAnalyseUtil.subjectText(message));
                }else{
                    mailInfo.setSubject(mailDecodeUtil.codeTransform(mailAnalyseUtil.subjectText(message),charset,"gbk"));
                }
                charset = mailAnalyseUtil.bodyCharset(message);
                if(charset == null || charset.length()==0){
                    mailInfo.setContent(mailAnalyseUtil.bodyText(message));
                }else{
                    mailInfo.setContent(mailDecodeUtil.codeTransform(mailAnalyseUtil.bodyText(message),charset,"gbk"));
                }
                if(!(mailInfo.getFrom() == null || mailInfo.getFrom().length()<1)){
                    mails.add(mailInfo);
                }
            }
            socket.close();
            return mails ;
        } catch (UnknownHostException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } catch (InterruptedException e) {
            throw e;
        } catch (ParseException e) {
            throw e;
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

    public String sendServer(String str,BufferedReader in,BufferedWriter out) throws IOException {
        out.write(str);
        out.newLine();
        out.flush();
        return getReturn(in);
    }
    public void sendServer(String str , BufferedWriter out) throws IOException {
        out.write(str);
        out.newLine();
        out.flush();
    }

    public void user(String user,BufferedReader in,BufferedWriter out) throws IOException {
        try {
            String result = getResult(getReturn(in));
            if (!"+OK".equals(result)) {
                throw new IOException("连接服务器失败!");
            }
            result=getResult(sendServer("user "+user,in,out));
            if(!"+OK".equals(result)){
                throw new IOException("用户名错误!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pass(String pwd,BufferedReader in,BufferedWriter out) {
        try {
            String result = null ;
            result = getResult(sendServer("pass " + pwd,in,out));
            if (!"+OK".equals(result)) {
                throw new IOException("密码错误!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int stat(BufferedReader in,BufferedWriter out) throws IOException {
        String result = null;
        String line = null;
        int mailNum = 0;
        line=sendServer("stat",in,out);
        StringTokenizer st=new StringTokenizer(line," ");
        result=st.nextToken();
        if(st.hasMoreTokens()) {
            mailNum=Integer.parseInt(st.nextToken());
        } else{
            mailNum=0;
        }
        if(!"+OK".equals(result)){
            throw new IOException("查看邮箱状态出错!");
        }
        return mailNum;
    }
    public void list(BufferedReader in,BufferedWriter out) throws IOException{
        String message = "";
        String line = null;
        line=sendServer("list",in,out);
        while(!".".equalsIgnoreCase(line)){
            message=message+line+"\n";
            line=in.readLine().toString();
        }
    }
    public void list_one(int mailNumber ,BufferedReader in,BufferedWriter out) throws IOException{
        String result = null;
        result = getResult(sendServer("list "+mailNumber,in,out));
        if(!"+OK".equals(result)){
            throw new IOException("list错误!");
        }
    }
    public String retr(int mailNum,BufferedReader in,BufferedWriter out) throws IOException, InterruptedException{
        String result = null;
        result=getResult(sendServer("retr "+mailNum,in,out));
        if(!"+OK".equals(result)){
            throw new IOException("接收邮件出错!");
        }
        String message = "";
        String line = null;
        try{
            line= in.readLine();
            while(!".".equalsIgnoreCase(line)){
                message=message+line+"\n";
                line= in.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return message;
    }
    public void quit(BufferedReader in,BufferedWriter out) throws IOException{
        String result;
        result=getResult(sendServer("QUIT",in,out));
        if(!"+OK".equals(result)){
            throw new IOException("未能正确退出");
        }
    }
}
