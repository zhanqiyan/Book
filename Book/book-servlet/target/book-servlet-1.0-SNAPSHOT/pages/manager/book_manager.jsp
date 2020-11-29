<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%--<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >--%>
    <%@include file="/pages/common/head.jsp" %>

    <script type="text/javascript">
        $(function () {// 给删除的 a 标签绑定单击事件， 用于删除的确认提示操作
            $("a.deleteClass").click(function () {
// 在事件的 function 函数中， 有一个 this 对象。 这个 this 对象， 是当前正在响应事件的 dom 对象。
                /**
                 * confirm 是确认提示框函数
                 * 参数是它的提示内容
                 * 它有两个按钮， 一个确认， 一个是取消。
                 * 返回 true 表示点击了， 确认， 返回 false 表示点击取消。
                 */
                return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】 ?");
// return false// 阻止元素的默认行为===不提交请求
            });
        });
    </script>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
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
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${requestScope.page.items}" var="book">
<%--        <c:forEach items="${requestScope.books}" var="book">--%>
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="manager/bookServlet?action=getBook&id=${book.id}">修改</a></td>
                <td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}">删除</a></td></tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_add.jsp">添加图书</a></td>
        </tr>
    </table>

<%--    静态包含分页--%>
    <%@include file="/pages/common/page_nav.jsp" %>
    <%--<div id="page_nav">
        <c:if test="${requestScope.page.pageno>1}">
&lt;%&ndash;            大于首页才显示上一页、首页&ndash;%&gt;
            <a href="${requestScope.page.url}">首页</a>
            <a href="${requestScope.page.url}&pageno=${requestScope.page.pageno-1}&pagesize=${requestScope.page.pageSize}">>上一页</a>
        </c:if>

        <c:forEach begin="1" end="${page.pageno}" var="i">
            <c:if test="${i>page.pageno-3&&i<page.pageno}">
            <a href="${requestScope.page.url}&pageno=${i}&pagesize=${requestScope.page.pageSize}">${i}</a>
            </c:if>
        </c:forEach>
        &lt;%&ndash;        当前页&ndash;%&gt;
        【${requestScope.page.pageno}】

        <c:forEach begin="${page.pageno+1}" end="${page.pageno+2}" var="i">
            <c:if test="${i<requestScope.page.pageTotalno+1}">
                <a href="${requestScope.page.url}&pageno=${i}&pagesize=${requestScope.page.pageSize}">${i}</a>
            </c:if>
        </c:forEach>
        <c:if test="${requestScope.page.pageno<requestScope.page.pageTotalno}">
&lt;%&ndash;            <a href="${requestScope.page.url}&pageno=${requestScope.page.pageno+1}&pagesize=${requestScope.page.pageSize}">${requestScope.page.pageno+1}</a>&ndash;%&gt;
            <a href="${requestScope.page.url}&pageno=${requestScope.page.pageno+1}&pagesize=${requestScope.page.pageSize}">下一页</a>
            <a href="${requestScope.page.url}&pageno=${requestScope.page.pageTotalno}&pagesize=${requestScope.page.pageSize}">末页</a>
        </c:if>
        共${requestScope.page.pageTotalno}页，${requestScope.page.pageTotalcount}条记录
&lt;%&ndash;        <form action="" method="get">&ndash;%&gt;
            到第<input value="${page.pageno}" name="pn" id="pn_input"/>页
            <input id="pagebuton" type="button" value="确定">
&lt;%&ndash;        </form>&ndash;%&gt;
        <script type="text/javascript">
            $(function (){
                // 跳到指定的页数去
               $("#pagebuton").click(function (){
                 var pageno = $("#pn_input").val();
                 &lt;%&ndash;if (pageno<1||pageno>${page.pageTotalno}){&ndash;%&gt;
                 &lt;%&ndash;    return false;&ndash;%&gt;
                 &lt;%&ndash;}&ndash;%&gt;
                 location.href="${requestScope.page.url}&pageno="+pageno+"&pagesize=${requestScope.page.pageSize}"
               });
            });
        </script>
    </div>--%>

</div>

<%@include file="/pages/common/footer.jsp" %>

</body>
</html>