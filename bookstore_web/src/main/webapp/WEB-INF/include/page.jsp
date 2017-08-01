<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div id="page_nav">
				<c:if test="${page.pageNo != 1 }">
					<a href="${page.path }">首页</a>
					<a href="${page.path }?pageNo=${page.prev }">上一页</a>
				</c:if>
				<!-- 设置两个变量 begin 和 end -->
				<!-- 1.总页数小于 5 时 -->
				<!-- 2.当前页小于等于 3 时 -->
				<!-- 3.当前页大于 3 时 -->
				<!-- 注意：当end大于总页数时这种情况 -->
				<c:choose>
					<c:when test="${page.totalPageNo < 5 }">
						<c:set var="begin" value="1"></c:set>
						<c:set var="end" value="${page.totalPageNo }"></c:set>
					</c:when>
					<c:when test="${page.pageNo <=3  }">
						<c:set var="begin" value="1"></c:set>
						<c:set var="end" value="5"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="begin" value="${page.pageNo - 2 }"></c:set>
						<c:set var="end" value="${page.pageNo + 2 }"></c:set>
						<c:if test="${end > page.totalPageNo }">
							<c:set var="begin" value="${page.totalPageNo - 4 }"></c:set>
							<c:set var="end" value="${page.totalPageNo}"></c:set>
						</c:if>
					</c:otherwise>
				</c:choose>
				<c:forEach begin="${begin }" end="${end }" var="index">
					<c:if test="${page.pageNo == index }">
						[<a href="${page.path }?pageNo=${index }">${index }</a>]
					</c:if>
					<c:if test="${page.pageNo != index }">
						<a href="${page.path }?pageNo=${index }">${index }</a>
					</c:if>
				</c:forEach>
				<c:if test="${page.pageNo != page.totalPageNo }">
					<a href="${page.path }?pageNo=${page.next }">下一页</a>
					<a href="${page.path }?pageNo=${page.totalPageNo }">末页</a>
				</c:if>
				共${page.totalPageNo }页 共${page.totalRecord }条记录 到第<input value="${page.pageNo }" name="pn" id="pn_input"/>页
				<input id="submit" type="button" value="确定">
				<script type="text/javascript">
					$("#submit").click(function(){
						//获取文本框输入的值
						var pageNo = $("#pn_input").val();
						//提交请求
						window.location = "${page.path }?pageNo="+pageNo;
					});
				</script>
	</div>