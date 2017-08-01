package com.atguigu.bookstore.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.service.UserService;

public class MyAuthRealm extends AuthorizingRealm {
    
	@Autowired
	private UserService userService;
	
	//授权验证
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}
    
	//登录验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//1.转化token
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		//2.调用业务方法通过用户名查询用户
		User user=userService.getUserByName(upToken.getUsername());
		//当前 Realm 的 name 属性值. 可以直接调用父类的 getName() 方法来获取
		return new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());	
	}

}
