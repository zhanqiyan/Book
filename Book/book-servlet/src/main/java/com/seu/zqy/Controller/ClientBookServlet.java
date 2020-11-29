package com.seu.zqy.Controller;

import com.seu.zqy.bean.Book;
import com.seu.zqy.bean.Page;
import com.seu.zqy.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {
    private final BookServiceImpl bookService = new BookServiceImpl();

    /**
     * 实现图书的分页展示功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pageno = req.getParameter("pageno") == null ? 1 : Integer.valueOf(req.getParameter("pageno"));
        Integer pagesize = req.getParameter("pagesize") == null ? Page.PAGE_SIZE : Integer.valueOf(req.getParameter("pagesize"));
        Page<Book> page = bookService.page(pageno, pagesize);
        page.setUrl("client/bookServlet?action=page");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    /**
     * 实现图书按价格区间查询并分页展示功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pagebyprice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pageno = req.getParameter("pageno") == null ? 1 : Integer.valueOf(req.getParameter("pageno"));
        Integer pagesize = req.getParameter("pagesize") == null ? Page.PAGE_SIZE : Integer.valueOf(req.getParameter("pagesize"));
        Integer min = req.getParameter("min") == null ? 0 : Integer.valueOf(req.getParameter("min"));
        Integer max = req.getParameter("max") == null ? Integer.MAX_VALUE : Integer.valueOf(req.getParameter("max"));
        Page<Book> page = bookService.pageByprice(pageno, pagesize,min,max);
        StringBuilder stringBuilder = new StringBuilder("client/bookServlet?action=pagebyprice");
//        如果有最小价格的参数，将其追加到分页条的参数中
        if (req.getParameter("min") != null) {
            stringBuilder.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null) {
            stringBuilder.append("&max=").append(req.getParameter("max"));
        }

        //分页条的url地址前缀由后端发送过去。完整地址在这个基础之上进行拼接
        page.setUrl(stringBuilder.toString());
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
