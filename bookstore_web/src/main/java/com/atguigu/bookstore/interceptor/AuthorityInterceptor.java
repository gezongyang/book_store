package com.atguigu.bookstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.atguigu.bookstore.beans.Resource;
import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.common.ExceptionEnum;
import com.atguigu.bookstore.exception.HasNoAuthorityException;
import com.atguigu.bookstore.exception.UserOperationForbiddenException;
import com.atguigu.bookstore.service.ResourceService;
import com.atguigu.bookstore.utils.DataprocessUtils;
import com.sdonkey.score.util.GlobleNames;


public class AuthorityInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private ResourceService resService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
        //1.将静态资源放过
		if(handler instanceof DefaultServletHttpRequestHandler) {
			return true;
		}
		
        //2.获取Session对象
		HttpSession session = request.getSession();
		
        //3.获取ServletPath
		String servletPath = request.getServletPath();
		
        //4.将ServletPath中的多余部分剪掉
		servletPath = DataprocessUtils.cutUrl(servletPath);
		
        //5.根据ServletPath获取对应的Res对象
		Resource res = resService.getResourceByServletPath(servletPath);
		
        //6.检查是否是公共资源
		if (res.isPublicRes()) {
	    //7.如果是公共资源则放行
			return true;
		}
		
        //8.如果当前请求的目标地址是User部分的
		if(servletPath.startsWith("/guest")) {
	//		9.检查User是否登录
			User user = (User) session.getAttribute("loginUser");
			
	//		10.如果没有登录则抛出自定义异常：UserLoginNeededException
			if(user == null) {
				throw new UserOperationForbiddenException(ExceptionEnum.UserOperationForbiddenException.getMsg());
			}
			
	//		11.已登录则检查权限
			String codeArrStr = user.getCodeArray();
			boolean checkAuthority = DataprocessUtils.checkAuthority(codeArrStr, res);
			
			if(checkAuthority) {
		//		12.有权限则放行
				return true;
			}else{
		//		13.没有权限则抛出自定义异常：HasNoRightException
				throw new HasNoAuthorityException(ExceptionEnum.HasNoAuthorityException.getMsg());
			}
			
		}
		
//		14.如果当前请求的目标地址是Admin部分的
		if(servletPath.startsWith("/manager")) {
	//		15.检查Admin是否登录
			User user =  (User) session.getAttribute(GlobleNames.LOGIN_USER);
			if(user == null) {
		//		16.如果没有登录则抛出自定义异常：AdminLoginNeededException
				throw new UserOperationForbiddenException(ExceptionEnum.USER_OPERATION_FORBIDDEN.getMsg());
			}
			
	//		17.如果已登录则检查是管理员
			String role = user.getRole();
			if ("2".equals(role)) {
				return true;
			}
			
	//		18.如果不是管理员，则检查是否具备访问目标资源的权限
			String codeArrStr = user.getCodeArray();
			boolean hasAuthority = DataprocessUtils.checkAuthority(codeArrStr, res);
			
	//		19.有权限则放行
			if (hasAuthority) {
				return true;
			} else {
		//		20.没有权限则抛出自定义异常：HasNoRightException
				throw new HasNoAuthorityException(ExceptionEnum.HasNoAuthorityException.getMsg());
			}
			
		}
		return true;
	}

}
