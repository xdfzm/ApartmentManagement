package com.example.apartmentmanagement.dao;

import com.example.apartmentmanagement.entity.Live;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface LiveMapper {

    public List<Live> selectLiveByDorm(String dormId);

    public int insertLive(Live live);

    public List<Live> selectLive(Live live);

    public int deleteLive(String stuId);

    public int updateLive(Live live);
}
