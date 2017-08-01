<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
  String path = request.getContextPath();
%>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<!-- <img class="logo_img" alt="" src="static/img/logo.gif" > -->
				<%@include file="/WEB-INF/include/user-menu.jsp" %>
		</div>
		
		<div id="main">
		
			<h1>欢迎回来 <a href="index.jsp">转到主页</a></h1>
			<h1>要评论吗？<a href="<%=path  %>/pages/userFeedBack/feedback.jsp">来这里哦！</a></h1>
	
		</div>
		
		<div id="bottom">
			<span>
				葛式集团.Copyright &copy;2015
			</span>
		</div>
</body>
</html>