<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>书城首页</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>

	<script>
		$(function (){
			//给加入购物车按钮绑定单击事件
			$("button.addToCart").click(function (){

				var bookId = $(this).attr("bookId")
				/*未使用Ajax请求
				location.href = "http://localhost:8080/10_book/cartServlet?action=addItem&id=" + bookId*/

				//发送Ajax请求,添加商品到购物车
				$.getJSON("http://localhost:8080/10_book/cartServlet","action=ajaxAddItem&id="+bookId,function (data){
					$("#cartTotalCount").text("您的购物车中有" + data.totalCount + "商品");
					$("#cartLastName").text(data.lastName);
				})

			});
		});
	</script>

</head>
<body>

<div id="header">
	<img class="logo_img" alt="" src="static/img/logo.gif" >
	<span class="wel_word">网上书城</span>
	<div>
		<%--如果用户还未登录，显示登录、注册菜单--%>
		<c:if test="${ empty sessionScope.user }"><%--为空为未登录--%>
			<a href="pages/user/login.jsp">登录</a> |
			<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
		</c:if>
		<c:if test="${ not empty sessionScope.user }"><%--为空为登录--%>
			<span>欢迎<span class="um_span">${ sessionScope.user.username }</span>光临尚硅谷书城</span>
			<a href="orderServlet?action=showMyOrder">我的订单</a>
			<a href="http://localhost:8080/10_book/userServlet?action=loginOut">注销</a>&nbsp;&nbsp;
		</c:if>
			<a href="pages/cart/cart.jsp">购物车</a>
		<a href="pages/manager/manager.jsp">后台管理</a>
	</div>
</div>

<div id="main">
	<div id="book">
		<div class="book_cond">
			<form action="client/bookServlet" method="get">
				<input type="hidden" name="action" value="pageByPrice">
				价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
				<input id="max" type="text" name="max" value="${param.max}"> 元
				<input type="submit" value="查询" />
			</form>
		</div>

		<c:if test="${empty sessionScope.cart.items}"><%--购物车为空时--%>
			<div style="text-align: center">
				<span style="color: red">当前购物车为空!!!</span>
			</div>
		</c:if>
		<c:if test="${not empty sessionScope.cart.items}"><%--购物车非空时--%>
			<div style="text-align: center">
				<span id="cartTotalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
				<div>
					您刚刚将<span style="color: #ff0000" id="cartLastName">${sessionScope.lastName}</span>加入到了购物车中
				</div>
			</div>
		</c:if>

			<c:forEach items="${requestScope.page.items}" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${book.img_path}" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.name}</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">￥${book.price}</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author}</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales}</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${book.stock}</span>
						</div>
						<div class="book_add">
							<button bookId="${book.id}" class="addToCart">加入购物车</button>
						</div>
					</div>
				</div>
			</c:forEach>
	</div>

	<%--用户端和后台的分页条部分抽取(我没有抽取)p252、p256查询分页未解决--%>
	<%------------------------分页部分-------------------------------------%>
	<div id="page_nav">
		<%--如果是第一页,不显示上一页和首页--%>
		<c:if test="${requestScope.page.pageNo > 1}">
			<a href="client/bookServlet?action=page&pageNo=1">首页</a>
			<a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
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
						<a href="client/bookServlet?action=page&pageNo=${i}">${i}</a>
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
								<a href="client/bookServlet?action=page&pageNo=${i}">${i}</a>
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
								<a href="client/bookServlet?action=page&pageNo=${i}">${i}</a>
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
								<a href="client/bookServlet?action=page&pageNo=${i}">${i}</a>
							</c:if>
						</c:forEach>
					</c:otherwise>

				</c:choose>
			</c:when>
		</c:choose>
		<%------------分页条页码部分1 2 3 4 5--------------页码输出的结束--------%>


		<%--如果是最后一页,不显示下一页和最后一页--%>
		<c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
			<a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
			<a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
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
						location.href="${pageScope.basePath}client/bookServlet?action=page&pageNo=" + pageNo;
					}else{
						alert("请输入正确页码");
					}

				});
			});
		</script>
	</div>


</div>


</body>
</html>