
package com.sdonkey.score.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @author fangguanhong
 */
public class StringUtil extends StringUtils {
    /**
     * 把一个字符串中连续的几个空格替换成一个@，字符串前后各添一个@
     *
     * @param str
     * @return
     */
    public static String getString(String str) {
        String newBlankList = "";
        try {
            String[] black = str.split(" ");
            for (int i = 0; i < black.length; i++) {
                String subString = black[i];
                if (subString.equals(" ") || subString.equals("")) {
                    continue;
                }
                newBlankList += "@" + subString.trim();
            }
            newBlankList += "@";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newBlankList;
    }

    public static String encode(String s) {
        String str = null;
        if (!StringUtil.isEmpty(s)) {
            try {
                str = URLEncoder.encode(s, "utf-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

        }
        return str;
    }

    public static String decode(String s) {
        String str = null;
        if (!StringUtil.isEmpty(s)) {
            try {
                str = URLDecoder.decode(s, "utf-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

        }
        return str;
    }

    public static String decodeURL(String s) {
        String str = null;
        if (!StringUtil.isEmpty(s)) {
            try {
                str = new String(s.getBytes("iso8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        return str;
    }


    public static String converToString(String[] ig) {
        StringBuilder str = new StringBuilder();
        String s = "";
        if (ig != null && ig.length > 0) {
            for (String anIg : ig) {
                str.append(anIg).append(",");
            }
            s = str.toString().substring(0, str.length() - 1);
        }
        return s;
    }


    public static String HtmlText(String inputString) {
        String htmlStr = inputString; //含html标签的字符串
        String textStr = "";
        Pattern p_script;
        java.util.regex.Matcher m_script;
        Pattern p_style;
        java.util.regex.Matcher m_style;
        Pattern p_html;
        java.util.regex.Matcher m_html;
        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
            String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); //过滤script标签
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); //过滤style标签
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); //过滤html标签
            /* 空格 ——   */
            // p_html = Pattern.compile("\\ ", Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = htmlStr.replaceAll(" ", " ");
            textStr = htmlStr;
        } catch (Exception e) {
        }
        return textStr;
    }

    /**
     * 将数据库中取出的字符串进行格式化
     *
     * @param Cg
     * @return
     */
    public static String formatCg(String Cg) {
        String[] split = Cg.split("\\|");
        StringBuilder sb = new StringBuilder();
        if (split.length > 0) {
            for (int i = 0; i < split.length; i++) {
                if (!split[i].equals("null") && i != 0) {
                    sb.append("|" + split[i]);
                } else if (i == 0) {
                    sb.append(split[i]);
                }
            }
            return sb.toString();
        }
        return Cg;
    }

    /**
     * 从url中获取文件名
     * @param url
     * @return
     */
    public static String getFileName(String url){
		
		return url.substring(url.lastIndexOf("/"));
		
	}
}
