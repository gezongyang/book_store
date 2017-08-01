<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>iphone7 内裤价甩卖</title>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.7.2.js"></script>
<script  type="text/javascript">
$(function(){
    $("#miaosha_btn").click(function(){ 
	var userid="user_"+Math.floor(Math.random()*100);
	$("#userid").val(userid);
	var url=$("#msform").attr("action");
           $.post(url,$("#msform").serialize(),function(data){
    	   if(data=="false"){
       	      alert("抢光了" );
                 $("#miaosha_btn").attr("disabled",true);
              }
           });    
     })
})
</script>

</head>
<body>
      <form id="msform" action="${pageContext.request.contextPath}/quickBuy.do">
<input type="hidden" id="userid" name="userid" value="">
<input type="hidden" id="prodid" name="prodid" value="pd1">
<input type="button"  id="miaosha_btn" name="miaosha_btn" value="秒杀点我"/>
</form>
      
</body>
</html>