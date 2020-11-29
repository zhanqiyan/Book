<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%--<base href="http://localhost:8080/book/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css" >--%>
    <%@include file="/pages/common/head.jsp" %>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <div>
        <span>欢迎<span class="um_span"></span>光临尚硅谷书城</span>
        <a href="user?action=logout">注销</a>&nbsp;
        <a href="${requestScope.referer}">返回</a>
    </div>

</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
<%--            <td>操作</td>--%>
        </tr>
        <c:if test="${not empty requestScope.orderItems}">
            <c:forEach items="${requestScope.orderItems}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.count}</td>
                    <td>${item.price}</td>
                    <td>${item.totalPrice}</td>
                </tr>
            </c:forEach>
        </c:if>

    </table>


</div>

<%--	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>