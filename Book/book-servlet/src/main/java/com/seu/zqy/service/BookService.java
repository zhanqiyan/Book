package com.seu.zqy.service;

import com.seu.zqy.bean.Book;
import com.seu.zqy.bean.Page;

import java.util.List;

public interface BookService {
    /**
     * 像数据库中保存图书信息
     * @param book
     */
    public void addBook(Book book);

    /**
     * 根据图书的id号删除图书
     * @param id
     */
    public void deleteBookById(Integer id);

    /**
     * 更新图书信息
     * @param book
     */
    public void updateBook(Book book);

    /**
     * 根据图书的id号查询图书
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 根据图书名查询图书信息
     * @param name
     * @return
     */
    public Book queryBookByBookName(String name);

    /**
     * 查出所有的图书
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 查出分页所需要的Page模型
     * @param pageno
     * @param pagesize
     * @return
     */
    public Page<Book> page(Integer pageno, Integer pagesize);

    /**
     * 按价格区间查出分页所需要的Page模型
     * @param pageno
     * @param pagesize
     * @param min
     * @param max
     * @return
     */
    public Page<Book> pageByprice(Integer pageno, Integer pagesize, Integer min, Integer max);
}
