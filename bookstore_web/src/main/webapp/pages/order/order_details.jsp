<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单详情</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<!-- <img class="logo_img" alt="" src="static/img/logo.png" > -->
			<span class="wel_word">我的订单</span>
			<%@include file="/WEB-INF/include/user-menu.jsp" %>
	</div>
	
	<div id="main">

		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
			</tr>	
				<tr>
					<td>${order.id }</td>
					<td>${order.createTime }</td>
					<td>${order.totalMoney }</td>
					<td>
						<c:if test="${order.status ==0 }">
							未发货
						</c:if>
						<c:if test="${order.status ==1 }">
							已发货<br/>
						</c:if>
						<c:if test="${order.status ==2 }">
							交易完成
						</c:if>
					</td>
				</tr>		
		</table>	
	</div>
	
	<div id="bottom">
		<span>
			葛式集团.Copyright &copy;2015
		</span>
	</div>
</body>
</html>