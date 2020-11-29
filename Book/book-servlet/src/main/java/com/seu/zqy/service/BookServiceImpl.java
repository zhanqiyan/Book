package com.seu.zqy.service;

import com.seu.zqy.bean.Book;
import com.seu.zqy.bean.Page;
import com.seu.zqy.dao.BookDao;

import java.util.List;
import com.seu.zqy.utils.jdbcUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.Cookie;

public class BookServiceImpl implements BookService {
    @Override
    public void addBook(Book book) {
        SqlSession session = null;
        try {
            session = jdbcUtils.getSession();
            BookDao bookDao = session.getMapper(BookDao.class);
            bookDao.addBook(book);
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
    public void deleteBookById(Integer id) {
        SqlSession session = null;
        try {
            session = jdbcUtils.getSession();
            BookDao bookDao = session.getMapper(BookDao.class);
            bookDao.deleteBookById(id);
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
    public void updateBook(Book book) {
        SqlSession session = null;

        try {
            session = jdbcUtils.getSession();
            BookDao bookDao = session.getMapper(BookDao.class);
            bookDao.updateBook(book);
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
    public Book queryBookById(Integer id) {
        SqlSession session = null;
        session = jdbcUtils.getSession();
        BookDao bookDao = session.getMapper(BookDao.class);
        Book book = bookDao.queryBookById(id);
        jdbcUtils.closeConnection(session);
        return book;
    }

    @Override
    public Book queryBookByBookName(String name) {
        SqlSession session = null;
        session = jdbcUtils.getSession();
        BookDao bookDao = session.getMapper(BookDao.class);
        Book book = bookDao.queryBookByname(name);
        jdbcUtils.closeConnection(session);
        return book;
    }

    @Override
    public List<Book> queryBooks() {
        SqlSession session = null;
        session = jdbcUtils.getSession();
        BookDao bookDao = session.getMapper(BookDao.class);
        List<Book> books = bookDao.queryBooks();
        jdbcUtils.closeConnection(session);
        return books;
    }

    @Override
    public Page<Book> page(Integer pageno, Integer pagesize) {
        Page<Book> page = new Page<>();
        page.setPageSize(pagesize);
        SqlSession session = null;
        session = jdbcUtils.getSession();
        BookDao bookDao = session.getMapper(BookDao.class);
        Integer pageTotalcount = bookDao.queryForPageTotal();
        page.setPageTotalcount(pageTotalcount);
        int pageTotalno = pageTotalcount / pagesize;
        if (pageTotalcount % pagesize != 0) {
            pageTotalno++;
        }
        page.setPageTotalno(pageTotalno);
//        System.out.println("纠正前"+pageno);
        page.setPageno(pageno);
//        System.out.println("纠正后"+page.getPageno());
        //page.getPageno可保证当输入的pageno为边界数据时可以取出page对象中的纠正值
//        System.out.println(page.getPageno());
        int begin = (page.getPageno() - 1) * pagesize;
        List<Book> items = bookDao.queryForItems(begin, pagesize);
        page.setItems(items);
        jdbcUtils.closeConnection(session);
        return page;
    }

    @Override
    public Page<Book> pageByprice(Integer pageno, Integer pagesize, Integer min, Integer max) {
        Page<Book> page = new Page<>();
        page.setPageSize(pagesize);
        SqlSession session = null;
        session = jdbcUtils.getSession();
        BookDao bookDao = session.getMapper(BookDao.class);
        Integer pageTotalcount = bookDao.queryForPageTotalByprice(min, max);
        page.setPageTotalcount(pageTotalcount);
        int pageTotalno = pageTotalcount / pagesize;
        if (pageTotalcount % pagesize != 0) {
            pageTotalno++;
        }
        page.setPageTotalno(pageTotalno);
        page.setPageno(pageno);
        int begin = (pageno - 1) * pagesize;
        List<Book> items = bookDao.queryForItemsByprice(begin, pagesize, min, max);
        page.setItems(items);
        jdbcUtils.closeConnection(session);
        return page;
    };
}
