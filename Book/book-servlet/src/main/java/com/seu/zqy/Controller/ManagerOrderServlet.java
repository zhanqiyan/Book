package com.seu.zqy.Controller;

import com.seu.zqy.bean.Order;
import com.seu.zqy.bean.OrderItem;
import com.seu.zqy.service.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ManagerOrderServlet extends BaseServlet {
    private OrderServiceImpl orderService = new OrderServiceImpl();
    /**
     * 查询所有的订单并展示
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void queryorders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.queryOrders();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    /**
     * 管理员改变发货状态
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        Integer status = Integer.valueOf(req.getParameter("status"));
        orderService.sendOrder(orderId, status);
        resp.sendRedirect(req.getContextPath()+"/manager/orderServlet?action=queryorders");
    }


}
