package com.seu.zqy.dao;

import com.seu.zqy.bean.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {

    /**
     * 保存订单信息
     * @param order
     */
    public void saveOrder(Order order);

    /**
     * 查询所有的订单信息
     * @return
     */
    public List<Order> queryOrders();

    /**
     * 改变订单状态
     *
     * @param OrderId
     * @param status
     */
    public void changeOrderStatus(@Param("OrderId") String OrderId, @Param("status") Integer status);

    /**
     * 根据用户id查询所有对应的订单
     * @param userid
     * @return
     */
    List<Order> queryOrdersByUserId(Integer userid);
}
