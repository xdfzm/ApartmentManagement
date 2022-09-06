package com.example.apartmentmanagement.service;

import com.example.apartmentmanagement.dao.FareMapper;
import com.example.apartmentmanagement.entity.Fare;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FareService {

    @Autowired
    private FareMapper fareMapper;

    public PageInfo<Fare> findFare(int currentPage, int pageSize, String stuId){
        PageHelper.startPage(currentPage, pageSize);

        List<Fare> fares = fareMapper.selectFare(stuId);

        PageInfo<Fare> pageInfo = new PageInfo<>(fares);

        return pageInfo;
    }

}
