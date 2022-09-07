package com.example.apartmentmanagement.service;

import com.example.apartmentmanagement.dao.PriceMapper;
import com.example.apartmentmanagement.entity.Price;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {
    @Autowired
    private PriceMapper priceMapper;

    public List<Price> findPrice(){

        List<Price> price = priceMapper.selectPrice();

        return price;
    }


    public int updatePrice(Price price) {
        Price price1 = priceMapper.findPrice();
        int result = 0;
        result = priceMapper.updatePrice(price);
        return result;
    }
}
