package com.sdonkey.score.util;

import com.sdonkey.score.util.images.ValidateCodeDraw;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

/**
 * 验证码工具类
 *
 * @author ZhaoShihao
 * @version: 1.0
 */
public class ValidateCodeUtil {
    private static char[] level_1 = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9'};
    private static char[] level_2 = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'P', 'A', 'S',
            'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N',
            'M'};
    private static char[] level_3 = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'P', 'A', 'S',
            'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N',
            'M', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'p', 'a', 's', 'd',
            'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'};
    private static ValidateCodeDraw draw;

    /**
     * 生成图片验证码并写入Session
     *
     * @param request
     * @param response
     * @param sessionKey session中需要的key
     */
    public static void writeCodeToWeb(HttpServletRequest request,
                                      HttpServletResponse response, String sessionKey) {
        try {
            HttpSession session = request.getSession();
            // 生成验证码
            String code = createCode(4, 2);
            // 将验证码放入Session
            session.setAttribute(sessionKey, code);
            session.setMaxInactiveInterval(60 * 5);
            // 设置响应的类型格式为图片格式
            response.setContentType("image/jpeg");
            // 禁止图像缓存。
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            // 渲染验证码
            draw = new ValidateCodeDraw(code);
            draw.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成验证码图片并保存图片至物理磁盘中
     *
     * @param path 输出文件目录
     * @param name 输出文件名称 为空则使用默认名称
     */
    public static void writeCodeToFile(String path, String name) {
        // 生成验证码
        String code = createCode(4, 2);
        draw = new ValidateCodeDraw(code);
        try {
            if (name == null || "".equals(name.trim())) {
                name = new Date().getTime() + "";
            }
            String e = path + "/" + name + ".png";
            System.out.println(code + " >" + e);
            draw.write(e);
        } catch (IOException var3) {
            var3.printStackTrace();
        }
    }

    /**
     * 生成验证码并发送至用户邮箱
     *
     * @param userEmail 用户邮箱
     * @param name      用户名
     * @return
     */
    public static int writeCodeToEmail(String userEmail, String name, HttpSession session) {
        Long codeTime = (Long) session.getAttribute("codeTime");
        Long nowTime = new Date().getTime();
        if (codeTime != null) {
            long l = (nowTime - codeTime) / 1000;
            if (l < 30) {
                return -3;//非法请求
            }
        }
        if (FormValidateUtil.isEmail(userEmail)) {
            if (name == null) {
                name = userEmail;
            }
            // 生成验证码
            String code = createCode(6, 3);
            StringBuilder sb = new StringBuilder();
            sb.append("<p>尊敬的用户【").append(name).append("】您好！<br /><br />此邮件旨在验证邮箱。").append("此次您的验证码为：<h1>").append(code).append("</h1><br/>").append("请注意：该验证码将在10分钟后过期，请尽快验证！。<br />").append("此为系统邮件，请勿回复。如有任何疑问或遇到问题，请发送电子邮件至 767608439@qq.com获取我们的帮助。<br />").append("感谢您对图书商城的支持。<br /><br />").append("图书商城支持中心</p>");
            try {
                SendMailNew.sendMailAsText1("图书商城  邮箱验证", ConfigUtil.getString("sendMail"), userEmail, sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
                return -2;//邮件发送失败
            }
            session.setAttribute("codeTime", nowTime);
            session.setAttribute(userEmail, code);
            return 0;//发送成功
        }
        return -1;//邮箱地址格式错误
    }

    /**
     * 生成验证码并发送至用户手机
     *
     * @param mobile  用户手机号
     * @param session
     * @return
     */
//    public static int writeCodeToMobile(String mobile, HttpSession session) {
//        Long codeTime = (Long) session.getAttribute("codeTime");
//        Long nowTime = new Date().getTime();
//        if (codeTime != null) {
//            long l = (nowTime - codeTime) / 1000;
//            if (l < 30) {
//                return -3;//非法请求
//            }
//        }
//        if (FormValidateUtil.isMobile(mobile)) {
//            // 生成验证码
//            String code = createCode(6, 1);
//            boolean b = SendMessageUtil.sendMessage(mobile, code);
//            if (b) {
//                session.setAttribute(mobile, code);
//                session.setAttribute("codeTime", nowTime);
//                return 0;//发送成功
//            }
//            return -2;//短信发送失败
//        }
//        return -1;//手机号格式错误
//    }


    /**
     * 生成一组验证码
     *
     * @param codeCount 生成的验证码位数
     * @param level     1.纯数字 2.数字加全大写字母 3.数字加大小写字母
     * @return
     */
    public static String createCode(int codeCount, int level) {
        // 生成随机数
        Random random = new Random();
        char[] codeSequence;
        switch (level) {
            case 1:
                codeSequence = level_1;
                break;
            case 2:
                codeSequence = level_2;
                break;
            case 3:
                codeSequence = level_3;
                break;
            default:
                codeSequence = level_2;
                break;
        }
        // randomCode记录随机产生的验证码
        StringBuilder randomCode = new StringBuilder();
        // 随机产生codeCount个字符的验证码。
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random
                    .nextInt(codeSequence.length)]);
            // 将产生的随机数组合在一起。
            randomCode.append(strRand);
        }
        // 将生成的验证码返回
        return randomCode.toString();
    }

}
