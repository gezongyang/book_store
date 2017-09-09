<%@page import="com.atguigu.bookstore.beans.LoginUserMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--这是提取的菜单  -->
<!--用户没登录状态显示  -->
<c:if test="${empty sessionScope.loginUser.role }">
<div>
		<a href="pages/user/login.jsp">登录</a> | 
		<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
</div>
</c:if>

<c:if test="${not empty sessionScope.loginUser.role }">
<div>
		<span>欢迎<span class="um_span">${sessionScope.loginUser.username }</span>光临书城</span> 	
		<!--创建导航条-->
		<ul class="nav">
			<li>
				<a href="pages/cart/cart.jsp">我的购物车</a>
			</li>
			<li>
				<a href="guest/orders.do">我的订单</a>
			</li>
			<li>
				<a href="guest/getPageBook.do">图书管理</a>
			</li>
			<li>
				<a href="logout.do">注销</a>
			</li>
			<li>
				<a href="index.jsp">返回</a>
			</li>
		</ul>		
</div>

</c:if>

<c:if test="${sessionScope.loginUser.role == 'manager'}">
<div>
		<span>欢迎<span class="um_span">${sessionScope.loginUser.username }</span>光临书城</span>
		<a href="manager/getOrderList.do">订单管理</a>
		<a href="guest/getPageBook.do">图书管理</a>
		<a href="logout.do">注销</a>&nbsp;&nbsp;
		<a href="index.jsp">返回</a>
</div>
</c:if>
<!--这是提取的菜单  -->
