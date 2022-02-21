package com.rt.service;


import com.github.pagehelper.PageInfo;
import com.rt.pojo.Book;
import com.rt.pojo.vo.BookVo;

import java.util.List;

public interface BookService {

    List<Book> selectALlBooks();

    PageInfo<Book> splitPage(int pageNum, int pageSize);

    PageInfo<Book> splitPageVo(BookVo bookVo,int pageSize);

//    int selectBookByIsbn(String isbn);
    Book selectBookByIsbn(String isbn);

    int insert(Book book);

    Integer selectLeftNumberByIsbn(String isbn);

    int updateLentNumberMinusByIsbn(String isbn);

    int updateLentNumberPlusByIsbn(String isbn);

    int deleteByIsbn(String isbn);
}
