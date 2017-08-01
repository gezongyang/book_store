<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会员登录页面</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){	
		//点击切换验证码
		$("#yzCode").click(function(){
			this.src="code.jpg?a="+Math.random();
		});
		
		//获取登录按钮并给它绑定单击响应函数
		$("#sub_btn").click(function(){
			//获取用户输入的用户名和密码
			var userName = $("#username").val();
			var password = $("#password").val();
			if(userName == ""){
				alert("用户名不能为空！");
				//取消默认行为
				return false;
			}
			if(password == ""){
				alert("密码不能为空！");
				return false;
			}
		});
	});
</script>
</head>
<body>
		<div id="login_header">
			<!-- <img class="logo_img" alt="" src="static/img/logo.png" > -->
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>会员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<!-- <span class="errorMsg">请输入用户名和密码</span> -->
								<%-- <span class="errorMsg"><%=request.getAttribute("message")==null?"请输入用户名和密码":request.getAttribute("message") %></span> --%>
								<span class="errorMsg">${empty message?"请输入用户名和密码":message }</span>
							</div>
							<div class="form">
								<form action="login.do" method="post">
									<label>用户名称：</label>
									<input value="${cookie.userName.value }" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input value="${cookie.password.value}"class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>验证码</label>
									<input class="itxt" name="code" type="text" style="width: 130px;height: 10px;" id="code" />
									<!--这里的src="code.jpg" 指的是验证码组件的serverlet的路径  -->
									<img id="yzCode" alt="" src="code.jpg" style="float: right; margin-right: 40px;width: 80px;height: 30px;">									
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn"/>
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				葛式集团.Copyright &copy;2015
			</span>
		</div>
</body>
</html>