<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>邮箱验证页面</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript" src="/bookstoreWeb/js/verifyEmail.js"></script>
</head>
<body>
		<div id="login_header">
			<!-- <img class="logo_img" alt="" src="static/img/logo.png" > -->
		</div>
		
			<div class="login_banner">
			
			
				
				<div id="content">
					<form>
						<p>邮箱号&nbsp;:<input type="email" placeholder="请输入注册时邮箱" id="email"></p>
						<p>验证码&nbsp;:<input type="text" placeholder="请输入6位验证码" id="code"><input type="button" value="获取邮箱验证码" id="get"></p>
						<p>
							<a href="javascript:void(0)"><input type="button" value="提交" id="sub"></a>
						</p>
					</form>
				</div>
			</div>
		<div id="bottom">
			<span>
				葛式集团.Copyright &copy;2015
			</span>
		</div>
</body>
</html>