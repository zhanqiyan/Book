<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>
    <%--<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >--%>
    <%@include file="/pages/common/head.jsp" %>

    <script type="text/javascript">
        $(function () {// 给删除的 a 标签绑定单击事件， 用于删除的确认提示操作
            $("a.changestatus").click(function () {
// 在事件的 function 函数中， 有一个 this 对象。 这个 this 对象， 是当前正在响应事件的 dom 对象。
                /**
                 * confirm 是确认提示框函数
                 * 参数是它的提示内容
                 * 它有两个按钮， 一个确认， 一个是取消。
                 * 返回 true 表示点击了， 确认， 返回 false 表示点击取消。
                 */
                return confirm("确定发货吗?");
// return false// 阻止元素的默认行为===不提交请求
            });
        });
    </script>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">订单管理系统</span>
    <%--			<div>
                    <a href="book_manager.jsp">图书管理</a>
                    <a href="order_manager.jsp">订单管理</a>
                    <a href="../../index.jsp">返回商城</a>
                </div>--%>
    <%@include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>详情</td>
            <td>发货</td>

        </tr>
        <c:if test="${not empty requestScope.orders}">
            <c:forEach items="${requestScope.orders}" var="order">
                <tr>
                    <td>${order.createTime}</td>
                    <td>${order.price}</td>
                    <td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
                    <c:if test="${order.status==0}">
                        <td><a class="changestatus" href="manager/orderServlet?action=sendOrder&orderId=${order.orderId}&status=1">点击发货</a></td>
                    </c:if>
                    <c:if test="${order.status==1}">
                        <td>已发货，待买家签收</td>
                    </c:if>
                    <c:if test="${order.status==2}">
                        <td>客户已签收</td>
                    </c:if>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>

<%@include file="/pages/common/footer.jsp" %>

</body>
</html>