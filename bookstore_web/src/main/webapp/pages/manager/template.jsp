<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书模板下载</title>
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
			<span class="wel_word">图书文件模板</span>
		</div>
		
		<div id="main">
			<h1>图书模板下载</h1>
		    <a href="guest/templateDownLoad.do">图书的模板下载</a>
		    
		    <h1>音乐下载</h1>
		    <a href="guest/fileCommonDownLoad.do?path=/template/news3.wav">音乐下载</a>
		    
		    <h1>图书批量导入</h1>
		    <form enctype="multipart/form-data" method="post" action="guest/bookInfoImport.do">

            	<input type="file" name="file"/>
            	<input type="submit" value="上传"/>

            </form>
            
            <h1>fdfs上传的测试</h1>
		    <form enctype="multipart/form-data" method="post" action="uploadFile.do">

            	<input type="file" name="file"/>
            	<input type="submit" value="上传"/>

            </form>
            <h1>fdfs下载测试</h1>
            <a href="downloadFile.do?filePath=group1/M00/00/00/rBAsgFl_5lWAf9lrAAAFMQXQ8Kk959.txt">图书的模板下载</a>
            
            
	    </div>
	    
		<div id="bottom">
			<span>
				葛式集团.Copyright &copy;2015
			</span>
		</div>
</body>
</html>