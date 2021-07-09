<%--
  Created by IntelliJ IDEA.
  User: 84695
  Date: 2021/5/8
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <div>
        <a href="manager/bookServlet?action=page">图书管理</a>
        <%--a标签是get请求,action表示调用BookServlet服务器里的list方法--%>
        <a href="orderServlet?action=showAllOrder">订单管理</a>
        <a href="../../10_book/index.jsp">返回商城</a>
    </div>

</body>
</html>
