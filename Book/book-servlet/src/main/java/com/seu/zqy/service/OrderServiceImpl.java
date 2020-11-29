package com.seu.zqy.service;

import com.seu.zqy.bean.*;
import com.seu.zqy.dao.BookDao;
import com.seu.zqy.dao.OrderDao;
import com.seu.zqy.dao.OrderItemDao;
import com.seu.zqy.utils.jdbcUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    @Override
    public String createOrder(Cart cart, Integer userId) {
        SqlSession session = null;
        // 订单号===唯一性
        String orderId = System.currentTimeMillis()+""+userId;
        try {
            session = jdbcUtils.getSession();
            OrderDao orderDao = session.getMapper(OrderDao.class);
            OrderItemDao orderItemDao = session.getMapper(OrderItemDao.class);
            BookDao bookDao  = session.getMapper(BookDao.class);
            // 创建一个订单对象
            Order order = new Order(orderId,new Date(),cart.getTotalPrice(), 0,userId);
            // 保存订单
            orderDao.saveOrder(order);
            // 遍历购物车中每一个商品项转换成为订单项保存到数据库
            for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            // 获取每一个购物车中的商品项
                CartItem cartItem = entry.getValue();
            // 转换为每一个订单项
                OrderItem orderItem = new OrderItem(null,cartItem.getName(),
                        cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(), orderId);
            // 保存订单项到数据库
                orderItemDao.saveOrderItem(orderItem);
                Book book = bookDao.queryBookById(cartItem.getId());
                book.setSales(book.getSales() + cartItem.getCount());
                book.setStock(book.getStock() - cartItem.getCount());
                bookDao.updateBook(book);
            }
            //清空购物车
            cart.clear();
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            jdbcUtils.closeConnection(session);
        }
        return orderId;
    }

    @Override
    public List<Order> queryOrders() {
        SqlSession session = null;
        List<Order> orders = null;
        session = jdbcUtils.getSession();
        OrderDao orderDao = session.getMapper(OrderDao.class);
        orders = orderDao.queryOrders();
        return orders;
    }

    @Override
    public void sendOrder(String OrderId,Integer status) {
        SqlSession session = null;
        try {
            session = jdbcUtils.getSession();
            OrderDao orderDao = session.getMapper(OrderDao.class);
            orderDao.changeOrderStatus(OrderId, status);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            jdbcUtils.closeConnection(session);
        }
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        SqlSession session = null;
        List<OrderItem> orderItems = null;
        session = jdbcUtils.getSession();
        OrderItemDao orderItemDao = session.getMapper(OrderItemDao.class);
        orderItems = orderItemDao.queryOrdersByOrderId(orderId);
        jdbcUtils.closeConnection(session);
        return orderItems;
    }

    @Override
    public List<Order> showMyOrders(Integer userid) {
        SqlSession session = null;
        List<Order> myorders = null;
        session = jdbcUtils.getSession();
        OrderDao orderDao = session.getMapper(OrderDao.class);
        myorders = orderDao.queryOrdersByUserId(userid);
        jdbcUtils.closeConnection(session);
        return myorders;
    }
}
