<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>葛氏集团会员注册页面</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
<script type="text/javascript">
	
	
	
	$(function(){
		
		
		/**
		 * 发送手机验证码
		 * @param mobile 手机号
		 */
		function ajaxGetCodeToMobile(mobile, that) {
		    var url = "/BookStore02/ajaxGetCodeToMobile.do";
		    $.post(url, {
		        mobile: mobile
		    }, function (data) {
		    	alert(data);
		    	/*请求完成，更改发送验证码的按钮为可用*/
		    	$("#get").removeAttr("disabled");
		    	$("#get").val("获取验证码");
		    	if (data == '1') {
		    		alert("发送成功,请注意查收");
		    	}
		    	 else if (data == '-1'){
		             alert('该手机号码已被注册', '');
		         }else{
		             alert('服务器异常，请稍后重试', '');
		         }
		    	/*未知*/ 
		    	settime(that);
		      
		    });
		}
		//获取验证码
		$(function () {
		    $("#get").click(function () {
		        /*获取手机号  */
		    	var tel = $("#telphone").val();
		        /*点击后，让这个按钮转为不可用状态*/        
		        $("#get").attr("disabled","true");
		        var bb = $("#get").attr("disabled");
		        alert(bb);
		        /*让这个按钮显示为 发送中...*/        
		        $("#get").val("发送中...");
		        /*发送ajax请求来发送手机验证码*/
		        ajaxGetCodeToMobile(tel, this);

		    });
		});

		//点击切换验证码
		/* $("#yzCode").click(function(){
			//alert("ok");
			//部分浏览器发现新的url地址如果和之前的一样，就不会再去发请求。
			//只需要给url后面追加一串可以的参数
			this.src = "code.jpg?a="+Math.random();
		}); */
		
		
		
		//获取注册按钮并给它绑定单击响应函数
		$("#sub_btn").click(function(){
			//获取用户名、密码、确认密码、邮箱、验证码
			var userName = $("#username").val();
			var password = $("#password").val();
			var repwd = $("#repwd").val();
			var email = $("#email").val();
			var code = $("#code").val();
			//使用正则表达式对用户名、密码、邮箱进行验证
			var userReg = /^[a-zA-Z0-9_-]{3,16}$/;
			if(!userReg.test(userName)){
				alert("请输入3-16位的数字、字母、下划线或减号的用户名！");
				return false;
			};
			var pwdReg = /^[a-zA-Z0-9_-]{6,18}$/;
			if(!pwdReg.test(password)){
				alert("请输入6-18位的数字、字母、下划线或减号的密码！");
				return false;
			};
			if(repwd != password){
				alert("两次输入的密码不一致！");
				return false;
			}
			var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!emailReg.test(email)){
				alert("邮箱格式不正确！");
				return false;
			}
			if(code == ""){
				alert("验证码不能为空！");
				return false;
			}
		});
	});

	
	/* function submit(object) {   
		//获取参数的值
		var userName = $("#username").val();
		var password = $("#password").val();
		var repwd = $("#repwd").val();
		var email = $("#email").val();
		var option=$("#role option:selected").val();  //获取选中的项
		alert(option)
		var url = "/BookStore02/regist.do";   
		
		  $.post(url, {username:userName,password:password,
			  repwd:repwd,
			  email:email,
			  role:option 
		    }, function (data) {
		    
		      
		    }); */
		
		/*  $.ajax({
		        url:"/BookStore02/regist.do",
			    type:"POST",
			    data:{username:userName,password:password,repwd:repwd, email:email,role:option}, 
			    success: function (data) {

			    },
			    error:function(data){
			    	alert("出现异常");
			    },	
			})  */
	
	
</script>
</head>
<body>
		<div id="login_header">
			<!-- <img class="logo_img" alt="" src="static/img/logo.png" > -->
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册会员</h1>
								<%-- <span class="errorMsg"><%=request.getAttribute("message")==null?"":request.getAttribute("message") %></span> --%>
								<span class="errorMsg">${message }</span>
							</div>
							<div class="form">
								<form action="regist.do" method="post">
									<label>用户名称：</label>
									<input value="${param.username }" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input value="${param.email }" class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<select name="role" id="role" class="t_aera">
										<option value="user" selected="selected">用户账户</option>
										<option value="manager">企业账号</option>
						            </select>
						            <br><br><br>
									
									<!-- <label>验证码：</label>
									<input class="itxt" name="code" type="text" style="width: 150px;" id="code"/> -->
									<!--这里的src="code.jpg" 指的是验证码组件的serverlet的路径  -->
									<!-- <img id="yzCode" alt="" src="code.jpg" style="float: right; margin-right: 40px;width: 80px;height: 40px;"> -->									
									<!-- <p class="clearfix">
			     					<label class="one" for="telphone">手机号&nbsp;:</label>
			     					<input id="telphone" name="telphone" maxlength="11" class="required" value placeholder="请输入手机号" />
		     				       </p>
		     				       <br/>
									<p>
			     					<label class="one">验证码&nbsp;:</label>
			     					<input type="text" maxlength="6" placeholder="请输入手机6位校验码" id="number" name="code">
			     					<input type="button" value="获取验证码" id="get">
		     				      </p> -->
									<br />
								   <!--  <input type="button" class="deal" onclick="submit(this)" value="注册" /> -->
									<input type="submit" value="注册" id="sub_btn" />
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