<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%--
    <link type="text/css" rel="stylesheet" href="/book/static/css/style.css" >
    --%>
    <%@include file="/pages/common/head.jsp" %>

    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="/book/static/img/logo.gif">
    <span class="wel_word"></span>
    <%--				<div>
                        <span>欢迎<span class="um_span">韩总</span>光临尚硅谷书城</span>
                        <a href="/book/pages/order/order.jsp">我的订单</a>
                        <a href="/book/index.jsp">注销</a>&nbsp;&nbsp;
                        <a href="/book/index.jsp">返回</a>
                    </div>--%>
    <%@include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <h1>注册成功! <a href="/book/index.jsp">转到主页</a></h1>

</div>

<%@include file="/pages/common/footer.jsp" %>

</body>
</html>