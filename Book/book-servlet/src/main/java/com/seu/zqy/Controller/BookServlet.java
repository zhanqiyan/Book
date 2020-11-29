package com.seu.zqy.Controller;

import com.seu.zqy.bean.Book;
import com.seu.zqy.bean.Page;
import com.seu.zqy.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class BookServlet extends BaseServlet {

    private final BookServiceImpl bookService = new BookServiceImpl();

    /**
     * 实现图书的添加功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        String author = req.getParameter("author");
        Integer sales =Integer.valueOf(req.getParameter("sales"));
        Integer stock =Integer.valueOf(req.getParameter("stock"));
        Book book = new Book(null, name, price, author, sales, stock);
        bookService.addBook(book);
//        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page");
    }

    /**
     * 实现图书的删除功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        bookService.deleteBookById(id);
        //重定向是将地址发送回浏览器。有浏览器对地址进行解析拼串
//        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page");

    }

    /**
     * 实现图书的修改
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        String author = req.getParameter("author");
        Integer sales = Integer.valueOf(req.getParameter("sales"));
        Integer stock = Integer.valueOf(req.getParameter("stock"));
        Book book = new Book(id, name, price, author, sales, stock);
        bookService.updateBook(book);
        //重定向是将地址发送回浏览器。有浏览器对地址进行解析拼串
//        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page");
    }

    /**
     * 获取要修改的图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        Book book = bookService.queryBookById(id);
        req.setAttribute("book", book);
        //转发，是在服务器内部进行的
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    /**
     * 查出所有的数据到book_manager页面进行展示
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

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
        //此在jsp页面中用在href上，由浏览器解析。故要写为相对设定好的相对basepath的相对路径
        page.setUrl("manager/bookServlet?action=page");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
