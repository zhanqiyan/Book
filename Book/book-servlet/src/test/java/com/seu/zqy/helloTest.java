package com.seu.zqy;

import com.seu.zqy.bean.Book;
import com.seu.zqy.bean.Order;
import com.seu.zqy.bean.OrderItem;
import com.seu.zqy.bean.User;
import com.seu.zqy.dao.BookDao;
import com.seu.zqy.dao.OrderDao;
import com.seu.zqy.dao.OrderItemDao;
import com.seu.zqy.service.BookServiceImpl;
import com.seu.zqy.service.UserserviceImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class helloTest {
    @Test
    public void test01() {
        UserserviceImpl userservice = new UserserviceImpl();

        boolean flag = userservice.existsUsername("zqy123");
        System.out.println(flag);


    }
    @Test
    public void test04() {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession session = factory.openSession();
            OrderDao orderDao = session.getMapper(OrderDao.class);
//            orderDao.saveOrder(new Order("1234567891",new Date(),new BigDecimal(100),0, 1));
//            session.commit();
            List<Order> orders = orderDao.queryOrders();
            for (Order order : orders) {
                System.out.println(order);
            }
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test05() {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession session = factory.openSession();
            OrderItemDao orderItemDao = session.getMapper(OrderItemDao.class);
            orderItemDao.saveOrderItem(new OrderItem(null,"java 从入门到精通", 1,new BigDecimal(100),new
                    BigDecimal(100),"1234567891"));
            session.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Test
    public void test02() {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession session = factory.openSession();
            BookDao bookDao = session.getMapper(BookDao.class);
//            Book book = bookDao.queryBookById(1);
//            System.out.println(book);
//            Book book1 = bookDao.queryBookByname("西游记");
//            System.out.println(book1);
////            int i = bookDao.addBook(new Book(null, "ha1ha", new BigDecimal(999), "dajiba", 100, 100));
////            session.commit();
//            int i = bookDao.updateBook(new Book(21, "haha", new BigDecimal(999), "dajiba", 100, 100));
//            session.commit();
//            List<Book> books = bookDao.queryBooks();
//            for (Book book2 : books) {
//                System.out.println(book2);
//            }
//            session.close();
//            bookDao.deleteBookById(21);
//            session.commit();
//            Integer total = bookDao.queryForPageTotal();
//            System.out.println(total);
//            Integer integer = bookDao.queryForPageTotalByprice(10, 100000);
//            System.out.println(integer);
            List<Book> books = bookDao.queryForItemsByprice(0, 4, 10, 100);
            for (Book book : books) {
                System.out.println(book);
            }
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test03() {
        BookServiceImpl bookService = new BookServiceImpl();
        Book book = bookService.queryBookById(20);
        System.out.println(book);

    }
}
