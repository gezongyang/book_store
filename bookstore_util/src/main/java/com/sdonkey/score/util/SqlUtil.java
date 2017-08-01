package com.sdonkey.score.util;

/**
 * sql语句相关工具类
 * @author ZhaoShihao
 * @version 1.0
 */
public class SqlUtil {

    /**
     * 防止sql注入：替换危险字符
     *
     * @param sql
     * @return
     */
    public static String replace(String sql) {
        if (sql != null) {
            sql = sql.replaceAll(";", "");
            sql = sql.replaceAll("&", "");
            sql = sql.replaceAll("<", "");
            sql = sql.replaceAll(">", "");
            sql = sql.replaceAll("'", "");
            sql = sql.replaceAll("--", "");
            sql = sql.replaceAll("/", "");
            sql = sql.replaceAll("%", "");
        }
        return sql;
    }
}
