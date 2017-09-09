//点击切换验证码
$("#yzCode").click(function(){
	this.src="code.jpg?a="+Math.random();
});
//登录函数		
function userLogin(object){
	var userName = $("#username").val();
	var password = $("#password").val();
	var code = $("#code").val();
	if(userName == ""){
		alert("用户名不能为空！");
		//取消默认行为
		return false;
	}
	if(password == ""){
		alert("密码不能为空！");
		return false;
	}
	$.ajax({
		url:"login.do",
	    type:"post",
	    dataType:"json",
	    async:false,
	    contentType:'application/json; charset=UTF-8',
	    data:JSON.stringify({"username":userName,"password":password,"code":code}), 
	    success: function (data) {
         //拿到登录用户信息
	     console.log(data)
	     window.location.href="pages/user/login_success.jsp";
        },
        error: function () {
             alert("登录失败")
        } 
     
    });
	
	/**
	 * 发送邮箱验证码
	 * @param email用户邮箱
	 */
	function ajaxGetCodeToEmail(email,that) {
	    if (isEmail(email)) {
	        var url = "/Lvscore_User_M/ajaxGetCodeToEmail";
	        $.post(
	            url,
	            {'email': email},
	            function (data) {
	                if (data == 1) {
	                    alertBox("发送成功,请注意查收");
	                } else {
	                    alertBox("您输入的邮箱未注册！","");
	                }
	                //倒计时
	                settime(that);
	            })
	    }
	}
}
