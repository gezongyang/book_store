package com.atguigu.bookstore.shiro;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.atguigu.bookstore.utils.Encrypt;


public class CustomCredentialsMatcher extends  SimpleCredentialsMatcher {

	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		//1.如何得到用户输入的密码（登录界面上输入的密码）
		String clintInputPwd = Encrypt.md5(new String(upToken.getPassword()), upToken.getUsername());//将用户输入的明文转化为密文
		//2.得到数据库中的密码
		String dbPwd = (String) info.getCredentials();//代表用户的认证密码（数据库中的密码）
		
		//这是父类中的方法，用于比较密码是否一致，当不一致时就会抛出异常
		return this.equals(clintInputPwd, dbPwd);
	}
	
	public static void main(String[] args) {
		String password=Encrypt.md5("123456","zhangxin");
		System.out.println(password);
		
	}

}
