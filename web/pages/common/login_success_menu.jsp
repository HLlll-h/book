<%--
  Created by IntelliJ IDEA.
  User: 84695
  Date: 2021/5/8
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

        <div>
            <span>欢迎<span class="um_span">${ sessionScope.user.username }</span>光临尚硅谷书城</span>
            <a href="orderServlet?action=showMyOrder">我的订单</a>
            <a href="http://localhost:8080/10_book/userServlet?action=loginOut">注销</a>&nbsp;&nbsp;
            <a href="index.jsp">返回</a>
        </div>

</body>
</html>
