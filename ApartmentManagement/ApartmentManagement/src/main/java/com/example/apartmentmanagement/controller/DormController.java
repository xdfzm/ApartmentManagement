package com.example.apartmentmanagement.controller;

import com.alibaba.fastjson.JSON;
import com.example.apartmentmanagement.entity.Dorm;
import com.example.apartmentmanagement.service.DormService;
import com.example.apartmentmanagement.utils.ResultVo;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/dorm")
public class DormController {

    @Autowired
    private Gson gson = new Gson();

    @Autowired
    private DormService dormService;


    @GetMapping("/get")
    public String getDorm(int currentPage, int pageSize, Dorm dorm){
        ResultVo resultVo = new ResultVo<>();
        PageInfo<Dorm> dorm1 = dormService.findDorm(currentPage, pageSize, dorm);

        if(dorm1.getSize() != 0){
            resultVo.setCode(200);
            resultVo.setMsg("查询成功");
            resultVo.setData(dorm1);
        }
        else {
            resultVo.setCode(500);
            resultVo.setMsg("查询失败，宿舍不存在");

        }
        return gson.toJson(resultVo);
    }

//添加宿舍
    @PostMapping("/add")
    public String addDorm(@RequestBody Dorm dorm){
        ResultVo resultVo = new ResultVo<>();
        int isInsert = dormService.insertDorm(dorm);
        if(isInsert != 0){
            resultVo.setCode(200);
            resultVo.setMsg("添加成功");
        }else {
            resultVo.setCode(500);
            resultVo.setMsg("添加失败，宿舍已存在");
        }
        return gson.toJson(resultVo);
    }
//删除宿舍
    @PostMapping("/delete")
    public String deleteDorm(@RequestBody String[] dormId){
        ResultVo resultVo = new ResultVo<>();
        int isDelete = 0;
        int length = 0 ;
        for (String  d : dormId){
            int liveNum1 = dormService.findLive(d);
            int findDorm1 = dormService.findDormById(d);
            if(liveNum1 == 1 || findDorm1 == 0){
                length = length +1;
            }
        }
        String[] arr = new String[length];
        int a = 0;
        for (String  d : dormId){
            int liveNum = dormService.findLive(d);
            int findDorm = dormService.findDormById(d);
            if(liveNum == 0 && findDorm == 1){
                isDelete += dormService.deleteDorm(d);
            }
            else{
                arr[a]=d;
                a=a+1;
            }
        }
        if(isDelete == dormId.length) {
            resultVo.setCode(200);
            resultVo.setMsg("删除成功");
        }
        else{
            resultVo.setCode(500);
            resultVo.setMsg("未能全部删除");
            resultVo.setData(arr);
        }
        return gson.toJson(resultVo);
    }
//更新宿舍信息
    @PostMapping("/update")
    public String updateDorm(@RequestBody Dorm dorm){
        ResultVo resultVo = new ResultVo<>();
        int liveNum = dormService.findLive(dorm.getDormId());
        if(liveNum != 0){
            resultVo.setCode(500);
            resultVo.setMsg("修改失败,宿舍有人居住");
            return gson.toJson(resultVo);
        }
        int isUpdate = dormService.updateDorm(dorm);

        if(isUpdate != 0){
            resultVo.setCode(200);
            resultVo.setMsg("修改成功");
        }else {
            resultVo.setCode(500);
            resultVo.setMsg("修改失败，宿舍不存在");
        }
        return gson.toJson(resultVo);
    }
//查询可入住宿舍
    @GetMapping("/getAccess")
    public String getAccessDorms(int currentPage, int pageSize){
        ResultVo resultVo = new ResultVo<>();
        System.out.println(currentPage);
        System.out.println(pageSize);
        PageInfo<Dorm> accessDorms = dormService.findAccessDorms(currentPage, pageSize);
        if(accessDorms != null){
            resultVo.setCode(200);
            resultVo.setMsg("查询成功");
            resultVo.setData(accessDorms);
        }else {
            resultVo.setCode(500);
            resultVo.setMsg("没有可入住宿舍");
        }

        return gson.toJson(resultVo);

    }
}
