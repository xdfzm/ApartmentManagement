package com.example.apartmentmanagement.service;

import com.example.apartmentmanagement.dao.DormMapper;
import com.example.apartmentmanagement.entity.Dorm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormService {

    @Autowired
    private DormMapper dormMapper;

    public int insertDorm(Dorm dorm){
        Dorm dorm1 = dormMapper.selectById(dorm.getDormId());
        int result = 0;//0为失败，1为插入成功
        System.out.println(dorm1);
        if(dorm1 == null){
            result = dormMapper.insertDorm(dorm);
        }
        return result;
    }


    public PageInfo<Dorm> findDorm(int currentPage, int pageSize, Dorm dorm){
        PageHelper.startPage(currentPage, pageSize);

        List<Dorm> dorms = dormMapper.selectDorm(dorm);

        PageInfo<Dorm> pageInfo = new PageInfo<>(dorms);

        return pageInfo;
    }


    public PageInfo<Dorm> findAllDorms(int currentPage, int pageSize){
        PageHelper.startPage(currentPage, pageSize);

        List<Dorm> dorms = dormMapper.selectAllDorms();

        PageInfo<Dorm> pageInfo = new PageInfo<>(dorms);

        return pageInfo;
    }

    public PageInfo<Dorm> findAccessDorms(int currentPage, int pageSize){
        PageHelper.startPage(currentPage, pageSize);

        List<Dorm> dorms = dormMapper.selectAccessDorms();

        PageInfo<Dorm> pageInfo = new PageInfo<>(dorms);

        return pageInfo;
    }

    public int findDormById(String dormId){
        int num=0;
        Dorm dorm1 = dormMapper.selectById(dormId);
        if(dorm1 != null) num=1;
        return num;
    }

    public int updateDorm(Dorm dorm){
        Dorm dorm1 = dormMapper.selectById(dorm.getDormId());
        int result = 0;
        if(dorm1 != null){
            result = dormMapper.updateDorm(dorm);
        }
        return result;
    }

    public int deleteDorm(String dormId){
        Dorm dorm1 = dormMapper.selectById(dormId);
        int result = 0;
        if(dorm1 != null){
            result = 1;
            dormMapper.deleteDorm(dormId);
        }
        return result;
    }


    public int findLive(String dormId) {
        int LiveNumber = dormMapper.findLive(dormId);
        return LiveNumber;
    }

    public int addRemainder(String dormId){
        return dormMapper.addRemainder(dormId);
    }

    public int reduceRemainder(String dormId){
        return dormMapper.reduceRemainder(dormId);
    }

    public int findTotal(String dormId){
        return dormMapper.findTotal(dormId);
    }

    public String findDormType(String dormId){
        return dormMapper.getDormType(dormId);
    }
}
