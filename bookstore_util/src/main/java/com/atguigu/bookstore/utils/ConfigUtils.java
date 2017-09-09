package com.atguigu.bookstore.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
      
	 private static Properties prop=loadPropertie ();
     //随着类的加载来加载properties配置文件
     public static Properties loadPropertie(){
           try {
              Properties properties= new Properties();
              InputStream in=ConfigUtils.class.getClassLoader().getResourceAsStream("location.properties" );
              properties.load(in);
              in.close();
               return properties;
        } catch (Exception e) {
              // TODO: handle exception
        }
           return null ;

     }

     //根据properties获取数据
     public static String getString(String key){
     
           return prop.getProperty(key);
     }
     //测试
     public static void main(String[] args) {
         
          System.out.println(getString( "adPicUrl"));
          System.out.println(getString( "adVideo"));
          System.out.println(getString( "adBannerPicUrl"));
         
     }
}