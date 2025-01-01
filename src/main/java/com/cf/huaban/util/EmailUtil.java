package com.cf.huaban.util;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @title: EmailUtil
 * @Author: NightRain
 * @Date: 2022/5/31 16:41
 * @Version 1.0
 * @Description:
 */
public class EmailUtil {
    // 发送人邮箱服务器
    public static String smtp_host = "smtp.163.com";
    public static String username = "wangjiajin0920@163.com";
    public static String password = "STTFWKSLJHEIMBPC";
    public static String from = "wangjiajin0920@163.com";

    public static void sendMail(String to, String subject, String text) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", smtp_host);
        props.put("mail.smtp.auth", true);
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        InternetAddress fromAddress = new InternetAddress(from);
        message.setFrom(fromAddress);
        InternetAddress toAddress = new InternetAddress(to);
        message.setRecipient(Message.RecipientType.TO, toAddress);
        message.setSubject(subject);
        message.setText(text);
        message.setContent(text, "text/html;charset=utf-8");
        Transport transport = session.getTransport("smtp");
        transport.connect(username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}

