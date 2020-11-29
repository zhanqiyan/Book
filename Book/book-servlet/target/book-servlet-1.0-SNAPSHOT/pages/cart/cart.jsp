<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%--<base href="http://localhost:8080/book/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css" >--%>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            //确认是否删除商品项
            $("a.deleteitem").click(function () {
                return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() +"】 吗?")
            });
            // 确认是否清空购物车
            $("a.clearall").click(function () {
                return confirm("确定清空购物车吗？")
            });

            // 给输入框绑定 onchange 内容发生改变事件
            $(".updateCount").change(function () {
                // 获取商品名称
                var name = $(this).parent().parent().find("td:first").text();
                var id = $(this).attr('bookId');
                // 获取商品数量
                var count = this.value;
                if (confirm("你确定要将【 " + name + "】 商品修改数量为： " + count + " 吗?")) {
                //发起请求。 给服务器保存修改
                    location.href =
                        "client/cartServlet?action=updateCount&count=" + count + "&id=" + id;
                } else {
                // defaultValue 属性是表单项 Dom 对象的属性。 它表示默认的 value 属性值。
                    this.value = this.defaultValue;
                }
            });

        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%--			<div>
                    <span>欢迎<span class="um_span">韩总</span>光临尚硅谷书城</span>
                    <a href="pages/order/order.jsp">我的订单</a>
                    <a href="index.jsp">注销</a>&nbsp;&nbsp;
                    <a href="index.jsp">返回</a>
                </div>--%>
    <%@include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <c:if test="${not empty sessionScope.cart.items}">
            <c:forEach items="${sessionScope.cart.items}" var="item">
                <tr>
                    <td>${item.value.name}</td>
                    <td>
                        <input class="updatecount" bookId="${item.value.id}" style="width: 80px" type="text" value="${item.value.count}">
                    </td>
                    <td>${item.value.price}</td>
                    <td>${item.value.totalPrice}</td>
                    <td><a class="deletitem" href="client/cartServlet?action=deleteItem&id=${item.value.id}">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty sessionScope.cart.items}">
                <tr>
                    <td colspan="5"><a href="index.jsp">当前购物车为空，火速去添加商品</a></td>
                </tr>
        </c:if>

    </table>
    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a href="client/cartServlet?action=clear" class="clearall">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=creatOrder">去结账</a></span>
        </div>
    </c:if>

</div>

<%--	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>