<%@page import="com.atguigu.bookstore.beans.BBSDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
   String path = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	div{
		width: 300px;
		border: 1px solid;
	}

</style>
</head>
<body>
<h1>欢迎来到葛宗阳论坛</h1>
<!--  -->
<form action="<%=path%>/publishAdvice.do" method="post">
	用户名：<input name="username" value="${loginUser.username}"/><br/>
	<textarea rows="10" cols="20" name="content"></textarea>
	<input type="submit" value="发帖"/>
</form>
<div>
	<!--遍历内容
		遍历map，每次遍历到的对象使用  对象.key取出map的key    对象.value取出value值
	  -->
	<c:forEach items="${feedback}" var="item">
		${item.content}：${item.callbackTime }<br/><!-- guest=bbbs    用户名：内容-->
	</c:forEach>
</div>
</body>
</html>