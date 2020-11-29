<%--
  Created by IntelliJ IDEA.
  User: zqy
  Date: 2020/11/26
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageno>1}">
        <%--            大于首页才显示上一页、首页--%>
        <a href="${requestScope.page.url}">首页</a>
        <a href="${requestScope.page.url}&pageno=${requestScope.page.pageno-1}&pagesize=${requestScope.page.pageSize}">>上一页</a>
    </c:if>

    <c:forEach begin="1" end="${page.pageno}" var="i">
        <c:if test="${i>page.pageno-3&&i<page.pageno}">
            <a href="${requestScope.page.url}&pageno=${i}&pagesize=${requestScope.page.pageSize}">${i}</a>
        </c:if>
    </c:forEach>
    <%--        当前页--%>
    【${requestScope.page.pageno}】

    <c:forEach begin="${page.pageno+1}" end="${page.pageno+2}" var="i">
        <c:if test="${i<requestScope.page.pageTotalno+1}">
            <a href="${requestScope.page.url}&pageno=${i}&pagesize=${requestScope.page.pageSize}">${i}</a>
        </c:if>
    </c:forEach>
    <c:if test="${requestScope.page.pageno<requestScope.page.pageTotalno}">
        <%--            <a href="${requestScope.page.url}&pageno=${requestScope.page.pageno+1}&pagesize=${requestScope.page.pageSize}">${requestScope.page.pageno+1}</a>--%>
        <a href="${requestScope.page.url}&pageno=${requestScope.page.pageno+1}&pagesize=${requestScope.page.pageSize}">下一页</a>
        <a href="${requestScope.page.url}&pageno=${requestScope.page.pageTotalno}&pagesize=${requestScope.page.pageSize}">末页</a>
    </c:if>
    共${requestScope.page.pageTotalno}页，${requestScope.page.pageTotalcount}条记录
    <%--        <form action="" method="get">--%>
    到第<input value="${page.pageno}" name="pn" id="pn_input"/>页
    <input id="pagebuton" type="button" value="确定">
    <%--        </form>--%>
    <script type="text/javascript">
        $(function (){
            // 跳到指定的页数去
            $("#pagebuton").click(function (){
                var pageno = $("#pn_input").val();
                location.href="${requestScope.page.url}&pageno="+pageno+"&pagesize=${requestScope.page.pageSize}"
            });
        });
    </script>
</div>
