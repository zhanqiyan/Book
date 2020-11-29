package com.seu.zqy.dao;

import com.seu.zqy.bean.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookDao {
    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public Book queryBookByname(String name);

    public List<Book> queryBooks();

    public Integer queryForPageTotal();

    public List<Book> queryForItems(@Param("begin") Integer begin, @Param("pagesize") Integer pagesize);

    public Integer queryForPageTotalByprice(@Param("min") Integer min, @Param("max") Integer max);

    public List<Book> queryForItemsByprice(@Param("begin") Integer begin, @Param("pagesize") Integer pagesize, @Param("min") Integer min, @Param("max") Integer max);
}
