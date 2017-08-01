package com.sdonkey.score.util;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMailNew {

    private JavaMailSenderImpl javaMailSender;
    private MimeMessageHelper mimeMessageHelper;

    //邮件服务器信息
    private String host = ConfigUtil.getString("mailHost");
    private String protocol = "smtp";
    private String port = "25";
    //邮件服务器验证信息
    private String userName = ConfigUtil.getString("sendMail");
    private String password = ConfigUtil.getString("mailPas");
    private String ismailAuth = "true";

    /**
     * 使用默认参数发送邮件
     */
    public SendMailNew() throws Exception {
        this(true);
    }

    /**
     * 使用发送邮件是否带附件
     */
    private SendMailNew(boolean isMultipart) throws Exception {
        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setProtocol(protocol);
        javaMailSender.setPort(Integer.parseInt(port));
        if (ismailAuth.equals("true")) {//如果需要服务器需要验证
            javaMailSender.setUsername(userName);
            javaMailSender.setPassword(password);
            Properties p = new Properties();
            p.put("mail.smtp.auth",ismailAuth);
            javaMailSender.setJavaMailProperties(p);
        }
        mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(),isMultipart,"GB2312");
    }

    /**
     * 发送邮件
     * @param fromTitle 发件人昵称/邮件主题
     * @param mailFrom 发件人地址
     * @param mailTo 收件人地址
     * @param strText 邮件内容
     * @throws Exception
     */
    public static void sendMailAsText1(String fromTitle, String mailFrom,
                                       String mailTo ,  final String strText) throws Exception {
        String [] mail = new String[]{mailTo};
        String [] mailCCs = new String[]{mailTo};
        SendMailNew send1 = new SendMailNew();
        //send1.sendMail(mailFrom, mailFrom, mail, mail, mailCCs, fromTitle, strText,false, null,true);
        send1.sendMailAsHtml(fromTitle, mailFrom, mailCCs, mail, null, fromTitle, strText);
    }
    /**
     * 发送邮件
     * @param fromTitle 发件人昵称
     * @param mailFrom 发件人地址
     * @param toTitles 收件人昵称
     * @param mailTos 收件人地址
     * @param mails 抄送或暗送地址
     * @param subject 主题
     * @param strText 邮件内容
     * @param html 邮件格式
     * @param fileTable 附件表key为文件名value为File对象
     */
    private void sendMail(String fromTitle, String mailFrom,String[] toTitles, String[] mailTos, String[] mails, String subject, String strText, boolean html, Hashtable fileTable,boolean isCC)
            throws Exception {
        if (null != mailTos) {
            if (null == toTitles) {
                mimeMessageHelper.setTo(mailTos);
            } else {
                for (int i=0; i<mailTos.length; i++) {
                    if (i > toTitles.length || toTitles.length == 0) {
                        mimeMessageHelper.addTo(mailTos[i]);
                    } else {
                        mimeMessageHelper.addTo(mailTos[i],toTitles[i]);
                    }
                }
            }
        }
        if (null != mailFrom) {
            if (null != fromTitle) {
                mimeMessageHelper.setFrom(mailFrom,fromTitle);
            } else {
                mimeMessageHelper.setFrom(mailFrom);
            }
        }
        if (null != mails) {
            if(isCC){
                mimeMessageHelper.setCc(mails);
            }else{
                mimeMessageHelper.setBcc(mails);
            }
        }
        if (null != subject) {
            mimeMessageHelper.setSubject(subject);
        }
        if (null != strText) {
            if (html) {
                mimeMessageHelper.setText(strText, true);
            } else {
                mimeMessageHelper.setText(strText, false);
            }
        }
        if (null != fileTable) {
            Enumeration e = fileTable.keys();
            while (e.hasMoreElements()) {
                String key = (String)e.nextElement();
                File value = (File)fileTable.get(key);
                mimeMessageHelper.addAttachment(key, value);
            }
        }
        javaMailSender.send(mimeMessageHelper.getMimeMessage());
    }

    /**
     * 发送html格式邮件,不带附件
     * @param fromTitle 发件人昵称
     * @param mailFrom 发件人地址
     * @param toTitles 收件人昵称
     * @param mailTos 收件人地址
     * @param mailCCs 抄送地址
     * @param subject 主题
     * @param strText 邮件内容
     */
    public void sendMailAsHtml(String fromTitle, String mailFrom,String[] toTitles, String[] mailTos, String[] mailCCs, String subject, String strText)
            throws Exception{
        sendMail(fromTitle, mailFrom, toTitles, mailTos, mailCCs, subject, strText, true, null,true);
    }

}
