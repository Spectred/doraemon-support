package com.doraemon;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author ： qjc
 * @date : 2017年3月6日 下午12:25:09
 * @file : JavaMail.java
 */
public class JavaMailUtil {

    private MimeMessage message;

    private Session session;

    private Transport transport;

    private String mailHost="";
    private String sender_username="";
    private String sender_password="";


    private Properties properties = new Properties();
    /*
     * 初始化方法
     */
    public JavaMailUtil(boolean debug) {
        InputStream in = JavaMailUtil.class.getResourceAsStream("/MailServer.properties");
        try {
            properties.load(in);
            this.mailHost = properties.getProperty("mail.smtp.host");
            this.sender_username = properties.getProperty("mail.sender.username");
            this.sender_password = properties.getProperty("mail.sender.password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        session = Session.getInstance(properties);
        session.setDebug(debug);//开启后有调试信息
        message = new MimeMessage(session);
    }

    /**
     * 发送邮件
     *
     * @param subject
     *            邮件主题
     * @param sendHtml
     *            邮件内容
     * @param receiveUser
     *            收件人地址
     */
    private  void doSendHtmlEmail(String subject, String sendHtml,
                                  String receiveUser) {
        try {
            // 发件人
            //InternetAddress from = new InternetAddress(sender_username);
            // 下面这个是设置发送人的Nick name
            InternetAddress from = new InternetAddress(" <"+sender_username+">");
            message.setFrom(from);

            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);//还可以有CC、BCC

            // 邮件主题
            message.setSubject(subject);

            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本"text/plain"
            message.setContent(content, "text/html;charset=UTF-8");

            // 保存邮件
            message.saveChanges();

            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(mailHost, sender_username, sender_password);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(transport!=null){
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void sendMails(String subject, String sendHtml, String receiveUser) {
        JavaMailUtil javaMailUtil = new JavaMailUtil(true);
        if (receiveUser.contains(";")) { //如果含有分号则说明需要给多个人发送
            String[] receiveUsers = receiveUser.split(";");
            for (String recipient : receiveUsers) {
                javaMailUtil.doSendHtmlEmail(subject, sendHtml, recipient);
            }
        }else {	//如果不含有分号说明只给一个人发送
            javaMailUtil.doSendHtmlEmail(subject, sendHtml, receiveUser);
        }
    }

    public static void main(String[] args) {
        JavaMailUtil javaMailUtil = new JavaMailUtil(true);
        javaMailUtil.doSendHtmlEmail("XX","HelloWord","1505073336@qq.com");
    }
}

