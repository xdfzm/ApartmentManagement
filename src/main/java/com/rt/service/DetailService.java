package com.rt.service;

import com.rt.pojo.Detail;

import java.util.List;

public interface DetailService {
    int insert(Detail detail);

    Detail selectDetailByReaderIdAndIsbn(int readerId,String isbn);

    int updateReturnDateByReaderIdAndIsbn(int readerId,String isbn,java.sql.Date returnDate);

    List<Detail> selectDetailByIsbn(String isbn);
}
