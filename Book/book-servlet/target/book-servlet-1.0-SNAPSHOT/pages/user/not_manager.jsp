<%--
  Created by IntelliJ IDEA.
  User: zqy
  Date: 2020/11/29
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>无管理员权限</title>
    <%--<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >--%>
    <%@include file="/pages/common/head.jsp" %>

    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    			<div>
                    <a href="index.jsp">返回商城</a>
                </div>
</div>

<div id="main">
    <h1>
        <a href="index.jsp">无管理员权限。请返回商城浏览商品哦！</a>
    </h1>
</div>

<%@include file="/pages/common/footer.jsp" %>

</body>
</html>