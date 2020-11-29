package com.seu.zqy.dao;

import com.seu.zqy.bean.OrderItem;

import java.util.List;

public interface OrderItemDao {
    /**
     * 保存订单商品
     * @param orderItem
     */
    public void saveOrderItem(OrderItem orderItem);

    /**
     * 根据订单号查询对应的商品项
     * @param orderId
     * @return
     */
    List<OrderItem> queryOrdersByOrderId(String orderId);
}
