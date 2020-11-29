<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/5
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <%-- 查询所有的数据，然后到book_manager页面进行显示
    <a href="manager/bookServlet?action=list">图书管理</a>
    --%>
    <%--    现在要进行分页展示，首先进入page资源，得到分页模型Page,然后传输到book_manager页面。
    从外部点击此链接，默认是进入第一页。且页面有默认大小，进入页面后可以修改页面大小--%>
    <a href="manager/bookServlet?action=page">图书管理</a>
    <a href="manager/orderServlet?action=queryorders">订单管理</a>
    <a href="index.jsp">返回商城</a>
</div>