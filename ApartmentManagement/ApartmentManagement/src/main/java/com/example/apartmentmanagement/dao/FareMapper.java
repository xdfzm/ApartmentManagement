package com.example.apartmentmanagement.dao;
import com.example.apartmentmanagement.entity.Fare;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FareMapper {
    Fare selectById(@Param("stuId") String stuId);
    List<Fare> selectAllFares();
    List<Fare> selectFare(String stuId);

}
