package com.rt.service.impl;

import com.rt.dao.BookTypeDao;
import com.rt.pojo.BookType;
import com.rt.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookTypeServiceImpl implements BookTypeService {

    @Autowired
    BookTypeDao bookTypeDao;
    @Override
    @Transactional
    public List<BookType> selectAllBookType() {
        return bookTypeDao.selectAllBookType();
    }
}
