package com.atguigu.bookstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.atguigu.bookstore.beans.Resource;
import com.atguigu.bookstore.service.ResourceService;



/**
 * 资源拦截器
 * @author gezongyang
 *
 */
public class ResIntercepter extends HandlerInterceptorAdapter {

	
	@Autowired
	private ResourceService resService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//1.让静态资源直接放行
		if(handler instanceof DefaultServletHttpRequestHandler) {
			return true;
		}
		
		//2.从request对象中获取servletPath
		String servletPath = request.getServletPath();
		
		//3.将servletPath中后面附加参数数据的部分去掉
		//servletPath = DataprocessUtils.cutUrl(servletPath);
		
		//4.检查这个servletPath是否是被保存过的
		boolean exists = resService.checkResExists(servletPath);
		
		//5.如果已经保存过，则直接放行
		if(exists) {
			return true;
		}
		
		//6.计算当前资源权限码和权限位的值
		//①声明两个变量，初始值是权限码和权限位的默认值
		Integer resCode = 1;
		Integer resPos = 0;
		
		int systemCode = 1 << 30;
		
		//②查询当前系统中最大的权限位
		Integer maxPos = resService.getMaxPos();
		
		//③查询当前系统中最大权限位范围内的最大权限码
		Integer maxCode = (maxPos == null) ? null : resService.getMaxResCodeOfResPos(resPos);
		
		//④在maxCode不为空的情况下，检查maxCode是否已经达到最大值
		if(maxCode != null) {
			if(maxCode == systemCode) {
				//如果已经达到最大，那么权限位+1，权限码设置为1
				resPos = maxPos + 1;
				resCode = 1;
			} else if(maxCode < systemCode){
				//⑤如果maxCode没有达到最大值
				//resPos设置为maxPos
				resPos = maxPos;
				
				//maxCode左移1位，然后赋值给resCode
				resCode = maxCode << 1;
			}
		}
		
		Resource res = new Resource(null, servletPath, false, resCode, resPos);
		resService.saveSource(res);
		
		return true;
	}
}
