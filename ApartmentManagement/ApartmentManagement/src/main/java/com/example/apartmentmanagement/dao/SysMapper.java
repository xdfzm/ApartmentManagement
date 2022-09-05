package com.example.apartmentmanagement.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysMapper {
    int selectFee();
    int updateFee(int newFee);
}
