package com.seu.zqy.Controller;

import com.seu.zqy.bean.Book;
import com.seu.zqy.bean.Cart;
import com.seu.zqy.bean.CartItem;
import com.seu.zqy.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

public class CartServlet extends BaseServlet {
    private BookServiceImpl bookService = new BookServiceImpl();
    /**
     * 向购物车中添加商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("cart",cart);
        req.getSession().setAttribute("addnewname", book.getName());
        //重定向到发起添加购物车请求的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 删除购物车中某个商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.delete(id);
            req.getSession().setAttribute("cart",cart);
        }
        resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
            req.getSession().removeAttribute("addnewname");
            req.getSession().removeAttribute("cart");
        }
        resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");
    }

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        Integer count = Integer.valueOf(req.getParameter("count"));
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id, count);
            req.getSession().setAttribute("cart",cart);
        }
        resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");
    }
}
