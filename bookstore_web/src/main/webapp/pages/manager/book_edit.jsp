<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	 input {
		text-align: center;
	} 
</style>
</head>
<body>
		<div id="header">
			<!-- <img class="logo_img" alt="" src="static/img/logo.png" > -->
			<span class="wel_word">编辑图书</span>
			<%@include file="/WEB-INF/include/user-menu.jsp" %>
		</div>
		
		<div id="main">
			<form action="guest/saveOrUpdate.do" method="post">
				<!-- 设置一个隐藏域来保存更新时的图书的ID -->
				<input type="hidden" name="bookId" value="${bookById.id }"/>
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="title" type="text" value="${bookById.title }"/></td>
						<td><input name="price" type="text" value="${bookById.price }"/></td>
						<td><input name="author" type="text" value="${bookById.author }"/></td>
						<td><input name="sales" type="text" value="${bookById.sales }"/></td>
						<td><input name="stock" type="text" value="${bookById.stock }"/></td>
						<td><input name="imgPath" type="hidden" value="${sessionScope.url}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	       		<form action="guest/uploadImg.do" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<td>图书封面</td>
					</tr>
					<tr>
					    <td>
					      <input type="file" name="photo"/>
					    </td>
					</tr>
					<tr>
						<td>
						  <input type="submit" value="上传"/>
						</td>
					</tr>
				</table> 
            </form>
            
            <div>
            	<a href="/bookstoreWeb/pages/manager/template.jsp" style='text-align:center;text-decoration:none;'>批量图书添加</a>
            </div>
	    </div>
	    
		<div id="bottom">
			<span>
				葛式集团.Copyright &copy;2015
			</span>
		</div>
</body>
</html>