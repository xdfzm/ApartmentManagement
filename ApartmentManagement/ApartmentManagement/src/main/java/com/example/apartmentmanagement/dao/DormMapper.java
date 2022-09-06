package com.example.apartmentmanagement.dao;
import com.example.apartmentmanagement.entity.Dorm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DormMapper {
    Dorm selectById(@Param("dormId") String dormId);

    List<Dorm> selectAllDorms();

    List<Dorm> selectAccessDorms();

    List<Dorm> selectDorm(Dorm dorm);

    int insertDorm(Dorm dorm);

    int updateDorm(Dorm dorm);

    int deleteDorm(String dormId);

    int findLive(String dormId);
}

