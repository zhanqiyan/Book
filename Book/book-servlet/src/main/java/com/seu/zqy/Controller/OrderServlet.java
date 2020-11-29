package com.seu.zqy.Controller;

import com.seu.zqy.bean.Cart;
import com.seu.zqy.bean.Order;
import com.seu.zqy.bean.OrderItem;
import com.seu.zqy.bean.User;
import com.seu.zqy.service.OrderServiceImpl;
import jdk.jshell.Snippet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {

    private OrderServiceImpl orderService = new OrderServiceImpl();

    /**
     * 用户结账，为其创建订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void creatOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        用户登录后，会将用户的简要信息保存在session会话中，用以实现有状态连接
        //同时购物车功能也是通过session来实现的
        Cart cart = (Cart) req.getSession().getAttribute("cart");
//        System.out.println(cart);
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }else {
            if (cart == null) {  //阻止订单重复提交
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }else {
                String orderid = orderService.createOrder(cart, user.getId());
                //  req.setAttribute("orderid", orderid);
//            req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
                req.getSession().setAttribute("orderid", orderid);
                req.getSession().removeAttribute("cart");  //一旦订单提交保存后立即销毁session中的购物车数据。确保不会重复提交
                resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
            }
        }
    }

    /**
     * 展现用户订单详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            Integer userid = user.getId();
            List<Order> myOrders = orderService.showMyOrders(userid);
            req.setAttribute("myOrders", myOrders);
            req.getRequestDispatcher("/pages/order/my_order_manager.jsp").forward(req, resp);
        }
    }

    /**
     * 用户签收
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        Integer status = Integer.valueOf(req.getParameter("status"));
        orderService.sendOrder(orderId, status);
        resp.sendRedirect(req.getContextPath()+"/orderServlet?action=showMyOrders");
    }

    /**
     * 管理者和用户查看订单详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        String referer = req.getHeader("Referer");
        req.setAttribute("orderItems", orderItems);
        req.setAttribute("referer", referer);
        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req, resp);
    }
}
