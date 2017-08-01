	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@ include file="/WEB-INF/include/base.jsp" %>

</head>
<body>
	<div id="header">
			
			<!-- <img class="logo_img" alt="" src="static/img/logo.gif" > -->
			<span class="wel_word">网上书城</span>
			
			<%@include file="/WEB-INF/include/user-menu.jsp" %>
	</div>
	
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="guest/getPageBookByPrice.do" method="post">
					价格：<input type="text" name="min" value="${param.min }"> 元 - 
						<input type="text" name="max" value="${param.max }"> 元
						<input type="submit" value="查询">
				</form>
			</div>
			<div style="text-align: center">
				<span>您的购物车中有
					<c:out value="${cart.totalCount }" default="0"></c:out>
				件商品</span>
				<div>
					<c:if test="${!empty lastAdd }">
						您刚刚将<span style="color: red">
						${lastAdd.title }
						</span>加入到了购物车中
					</c:if>
					<c:if test="${empty lastAdd }">
						<span>&nbsp;&nbsp;</span>
					</c:if>
				</div>
			</div>
			<c:if test="${empty page.list }">
				没有任何图书！
			</c:if>
			<c:if test="${!empty page.list }">
			<c:forEach items="${page.list }" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${pageContext.request.contextPath }/${book.imgPath }" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.title }</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author }</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">￥${book.price }</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales }</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${book.stock }</span>
						</div>
						<div class="book_add">
							<a href="guest/addCartItem.do?id=${book.id }">加入购物车</a>
						</div>
					</div>
				</div>
			</c:forEach>
			</c:if>
		</div>
		<!-- 分页信息 -->
		<%@ include file="/WEB-INF/include/page.jsp" %>
	</div>
	
	<div id="bottom">
		<span>
			葛式集团.Copyright &copy;2015
		</span>
	</div>
</body>
</html>