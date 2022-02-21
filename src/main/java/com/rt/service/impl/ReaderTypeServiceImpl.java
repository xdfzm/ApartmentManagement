package com.rt.service.impl;

import com.rt.dao.ReaderTypeDao;
import com.rt.pojo.ReaderType;
import com.rt.service.ReaderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReaderTypeServiceImpl implements ReaderTypeService {

    @Autowired
    ReaderTypeDao readerTypeDao;
    @Override
    @Transactional
    public List<ReaderType> selectAllReaderType() {
        return readerTypeDao.selectAllReaderType();
    }
}
