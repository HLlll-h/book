<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <%--	静态包含含有base、css、js文件--%>
    <%@include file="/pages/common/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif" >
    <span class="wel_word">订单详情</span>
    <%--				使用静态包含 登录成功的部分--%>
    <%@include file="/pages/common/login_success_menu.jsp"%>
</div>

<div id="main">
    <table>
        <tr>
            <td>商品名</td>
            <td>单价</td>
            <td>数量</td>
            <td>总金额</td>
        </tr>

        <c:forEach items="${requestScope.orderItems}" var="orderItems">
            <tr>
                <td>${orderItems.name}</td>
                <td>${orderItems.price}</td>
                <td>${orderItems.count}</td>
                <td>${orderItems.totalPrice}</td>
            </tr>
        </c:forEach>

    </table>


</div>

<%--	静态包含页脚部分--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>