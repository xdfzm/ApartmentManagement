package com.example.apartmentmanagement.dao;


import com.example.apartmentmanagement.entity.Price;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PriceMapper {

    Price findPrice();

    List<Price> selectPrice();

    int updatePrice(Price price);
}
