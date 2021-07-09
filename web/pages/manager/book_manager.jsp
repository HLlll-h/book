<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%--	静态包含含有base、css、js文件--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		//给删除按钮单击事件 用于确认是否删除
		$(function (){

			$("a.deleteClass").click(function (){

				return confirm("确定要删除[" + $(this).parent().parent().find("td:first").text() + "]？")

				return false;
			});

		});


	</script>
</head>

<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%--静态包含manager管理模块菜单--%>
		<%@include file="/pages/common/manage_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>		

			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&method=update&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?method=add&pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>	
		</table>

	</div>

	<%--用户端和后台的分页条部分抽取(我没有抽取)p252、p256查询分页未解决--%>
<%------------------------分页部分-------------------------------------%>
	<div id="page_nav">
        <%--如果是第一页,不显示上一页和首页--%>
        <c:if test="${requestScope.page.pageNo > 1}">
            <a href="manager/bookServlet?action=page&pageNo=1">首页</a>
            <a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
        </c:if>
<%------------分页条页码部分1 2 3 4 5--------------页码输出的开始--------%>
			<c:choose>
				<%--情况一：如果总页码小等于5情况,页码的范围是：1-总页码--%>
				<c:when test="${requestScope.page.pageTotal <= 5}">
					<c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
						<c:if test="${i == requestScope.page.pageNo}">
							【${i}】
						</c:if>
						<c:if test="${i != requestScope.page.pageNo}">
							<a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
						</c:if>
					</c:forEach>
				</c:when>
				<%--情况二：如果总页码大于5情况,假设为10页--%>
				<c:when test="${requestScope.page.pageTotal > 5}">
					<c:choose>
						<%--小情况1：当前页码为前面3个:1 2 3的情况,页码范围是1-5--%>
						<c:when test="${requestScope.page.pageNo <= 3}">
							<c:forEach begin="1" end="5" var="i">
								<c:if test="${i == requestScope.page.pageNo}">
									【${i}】
								</c:if>
								<c:if test="${i != requestScope.page.pageNo}">
									<a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
								</c:if>
							</c:forEach>
						</c:when>
						<%--小情况2：当前页码为最后三个3个:8 9 10的情况,页码范围是 总页码-4~总页码--%>
						<c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal - 3}">
							<c:forEach begin="${requestScope.page.pageTotal - 4}" end="${requestScope.page.pageTotal}" var="i">
								<c:if test="${i == requestScope.page.pageNo}">
									【${i}】
								</c:if>
								<c:if test="${i != requestScope.page.pageNo}">
									<a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
								</c:if>
							</c:forEach>
						</c:when>
						<%--小情况3：当前页码为中间4 5 6 7的情况,页码范围是 当前页码-2~当前页码+2--%>
						<c:otherwise>
							<c:forEach begin="${requestScope.page.pageNo - 2}" end="${requestScope.page.pageNo + 2}" var="i">
								<c:if test="${i == requestScope.page.pageNo}">
									【${i}】
								</c:if>
								<c:if test="${i != requestScope.page.pageNo}">
									<a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
								</c:if>
							</c:forEach>
						</c:otherwise>

					</c:choose>
				</c:when>
			</c:choose>
<%------------分页条页码部分1 2 3 4 5--------------页码输出的结束--------%>


        <%--如果是最后一页,不显示下一页和最后一页--%>
        <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
            <a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
            <a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
        </c:if>

		共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
		到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
		<input id="searchPageBtn" type="button" value="确定">

        <script>
            $(function (){
                //跳转到指定页面
                $("#searchPageBtn").click(function (){

                        var pageNo = $("#pn_input").val();
                    if(pageNo >= 1 && pageNo <= ${requestScope.page.pageTotal}){
                        location.href="${pageScope.basePath}manager/bookServlet?action=page&pageNo=" + pageNo;
                    }else{
                        alert("请输入正确页码");
                    }

                });
            });
        </script>
	</div>


		<%--静态包含页脚部分--%>
<%--	<%@include file="/pages/common/footer.jsp"%>--%>
</body>
</html>