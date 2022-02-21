package com.rt.service.impl;

import com.rt.dao.DetailDao;
import com.rt.pojo.Detail;
import com.rt.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class DetailServiceImpl implements DetailService {
    @Autowired
    DetailDao detailDao;

    @Override
    public int insert(Detail detail) {
        return detailDao.insert(detail);
    }

    @Override
    public Detail selectDetailByReaderIdAndIsbn(int readerId, String isbn) {
        Detail detail = null;
        detail = detailDao.selectDetailByReaderIdAndIsbn(readerId,isbn);
        return detail;
    }

    @Override
    public int updateReturnDateByReaderIdAndIsbn(int readerId, String isbn, Date returnDate) {
        return detailDao.updateReturnDateByReaderIdAndIsbn(readerId,isbn,returnDate);
    }

    @Override
    public List<Detail> selectDetailByIsbn(String isbn) {
        List<Detail> details = null;
        details = detailDao.selectDetailByIsbn(isbn);
        return details;
    }
}
