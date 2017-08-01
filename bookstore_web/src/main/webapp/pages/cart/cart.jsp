<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@ include file="/WEB-INF/include/base.jsp"%>
<script type="text/javascript">
	$(function() {
		$(".delBtn").click(function() {
			//alert("ok");
			var eleTxt = $(this).parents("tr").find("td:first").text();
			var flag = confirm("确认删除【" + eleTxt + "】吗？");
			if (!flag) {
				//用户点击了取消
				return false;
			}
		});

		//当输入框内容变化后再发送请求
		$(".updateInput").change(function() {
							//alert("ok");
							//1、带上数据1）、图书的id   2）、修改后的数量
							var count = $(this).val();
							var bookId = $(this).attr("id");
							//alert("修改的数量："+count+"-->修改的图书id："+bookId);
							//通过对location.href赋值，可以使浏览器重新发送一个请求
							//base标签有些浏览器会让其影响location.href。
							location.href = "${pageContext.request.contextPath}/guest/updateCountOfCartItem.do?id="
									+ bookId + "&count=" + count;
			});
	});
</script>
</head>
<body>

	<div id="header">
		<!-- <img class="logo_img" alt="" src="static/img/logo.png"> --> <span
			class="wel_word">购物车</span>
		<%@include file="/WEB-INF/include/user-menu.jsp"%>
	</div>

	<div id="main">
		<c:if test="${ empty cart.allItems}">
			<center>
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				<h1>
					当前购物车没有图书 ，赶紧去<a href="index.jsp">购买吧</a>
				</h1>
			</center>

		</c:if>

		<c:if test="${!empty cart.allItems }">
			<table>
				<tr>
					<td>商品名称</td>
					<td>数量</td>
					<td>单价</td>
					<td>金额</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${cart.allItems }" var="item">
					<tr>
						<td>${item.book.title }</td>
						<td><input id="${item.book.id }" class="updateInput"
							type="text" name="count" value="${item.count }"
							style="width: 40px;" /></td>
						<td>${item.book.price }</td>
						<td>${item.totalPrice }</td>
						<td><a class="delBtn"
							href="guest/deleteCartItem.do?id=${item.book.id }">删除</a></td>
					</tr>
				</c:forEach>

			</table>

			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${cart.totalCount }</span>件商品
				</span> <span class="cart_span">总金额<span class="b_price">${cart.totalMoney }</span>元
				</span> <span class="cart_span"><a href="guest/clearCart.do">清空购物车</a></span>
				<span class="cart_span"><a href="guest/checkout.do">去结账</a></span>
			</div>
		</c:if>
	</div>

	<div id="bottom">
		<span>葛式集团.Copyright &copy;2015</span>
	</div>
</body>
</html>