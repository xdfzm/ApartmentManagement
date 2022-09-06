package com.example.apartmentmanagement.controller;

import com.alibaba.fastjson.JSON;
import com.example.apartmentmanagement.entity.Dorm;
import com.example.apartmentmanagement.service.DormService;
import com.example.apartmentmanagement.utils.ResultVo;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/dorm")
public class DormController {

    @Autowired
    private Gson gson = new Gson();

    @Autowired
    private DormService dormService;

//查询全部宿舍
    @GetMapping("/getAll")
    public String getDorms(int currentPage, int pageSize){
        ResultVo resultVo = new ResultVo<>();
        System.out.println(currentPage);
        System.out.println(pageSize);
        PageInfo<Dorm> allDorms = dormService.findAllDorms(currentPage, pageSize);
        if(allDorms != null){
            resultVo.setCode(200);
            resultVo.setMsg("查询成功");
            resultVo.setData(allDorms);
        }else {
            resultVo.setCode(500);
            resultVo.setMsg("没有你想要的查询值");
        }

        return gson.toJson(resultVo);

    }
//查询单个宿舍
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
            resultVo.setMsg("没有你想要的查询结果");

        }
        return gson.toJson(resultVo);
    }

    @GetMapping("/gettest/{id}/{currentPage}/{pageSize}")
    public String getDormTest(@PathVariable("id") String id,
                                 @PathVariable("currentPage") int currentPage,
                                 @PathVariable("pageSize") int pageSize,
                                 @RequestBody String[] ids){
        System.out.println(id);
        System.out.println(currentPage);
        System.out.println(pageSize);
        for (String d : ids) {
            System.out.println(id);
        }
        return JSON.toJSONString(ids);
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
            resultVo.setMsg("添加失败");
        }
        return gson.toJson(resultVo);
    }
//删除宿舍
    @PostMapping("/delete")
    public String deleteDorm(@RequestBody Dorm dorm){
        ResultVo resultVo = new ResultVo<>();
        int liveNum = dormService.findLive(dorm.getDormId());
        if(liveNum != 0){
            resultVo.setCode(500);
            resultVo.setMsg("删除失败,宿舍有人居住");
            return gson.toJson(resultVo);
        }
        int isDelete = 0;
        isDelete = dormService.deleteDorm(dorm);
        if(isDelete != 0){
            resultVo.setCode(200);
            resultVo.setMsg("删除成功");
        }else {
            resultVo.setCode(500);
            resultVo.setMsg("删除失败");
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
            resultVo.setMsg("修改失败");
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
            resultVo.setMsg("没有你想要的查询值");
        }

        return gson.toJson(resultVo);

    }
}
