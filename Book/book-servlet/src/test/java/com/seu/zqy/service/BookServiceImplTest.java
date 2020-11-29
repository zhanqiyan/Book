package com.seu.zqy.service;

import com.seu.zqy.bean.Book;
import com.seu.zqy.bean.Page;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceImplTest {
    BookServiceImpl bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"天下我有！ ",new BigDecimal(1000000),"zqy",
                1000, 0, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(23,"天下我有！ ",new BigDecimal(1000000),"zqy",
                1000, 0, null));
    }

    @Test
    public void queryBookById() {
    }

    @Test
    public void queryBookByBookName() {
        Book book = bookService.queryBookByBookName("西游记");
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
    }
    @Test
    public void page(){
        Page<Book> page = bookService.page(1, 4);
        System.out.println(page);
    }
    @Test
    public void pagebyprice(){
        Page<Book> page = bookService.pageByprice(1, 4,10,100);
        System.out.println(page);
    }

}