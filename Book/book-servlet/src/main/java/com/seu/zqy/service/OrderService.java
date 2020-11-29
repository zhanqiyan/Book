package com.seu.zqy.service;

import com.seu.zqy.bean.Cart;
import com.seu.zqy.bean.Order;
import com.seu.zqy.bean.OrderItem;

import java.util.List;

public interface OrderService {

    /**
     * 根据购物车数据和用户编号来创建订单
     * @param cart
     * @param userId
     * @return
     */
    public String createOrder(Cart cart, Integer userId);

    /**
     * 查询所有订单
     * @return
     */
    public List<Order> queryOrders();

    /**
     * 改变订单状态：
     * 0：未发货  1：已发货  2：已签收
     *
     * @param OrderId
     */
    public void sendOrder(String OrderId, Integer status);

    /**
     * 展现订单号对应的商品详情
     * @param orderId
     * @return
     */
    List<OrderItem> showOrderDetail(String orderId);

    /**
     * 根据用户id号查询对应的订单
     * @param userid
     * @return
     */
    List<Order> showMyOrders(Integer userid);
}
