package com.rt.dao;

import com.rt.pojo.Book;
import com.rt.pojo.vo.BookVo;

import java.util.List;

public interface BookDao {

    List<Book> selectAllBook();

    List<Book> selectByCondition(BookVo bookVo);







    Book selectByIsbn(String isbn);

    int insert(Book book);

    Integer selectLeftNumberByIsbn(String isbn);

    int updateLentNumberMinusByIsbn(String isbn);

    int updateLentNumberPlusByIsbn(String isbn);

    int deleteByIsbn(String isbn);
}
