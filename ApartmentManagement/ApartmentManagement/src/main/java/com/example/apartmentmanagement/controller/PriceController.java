package com.example.apartmentmanagement.controller;

import com.example.apartmentmanagement.entity.Price;
import com.example.apartmentmanagement.service.PriceService;
import com.example.apartmentmanagement.utils.ResultVo;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/price")
public class PriceController {
    @Autowired
    private Gson gson = new Gson();

    @Autowired
    private PriceService priceService;
//查找水电标准
    @GetMapping("/get")
    public String getPrice(int currentPage, int pageSize){
        ResultVo resultVo = new ResultVo<>();
        System.out.println(currentPage);
        System.out.println(pageSize);
        PageInfo<Price> price1 = priceService.findPrice(currentPage, pageSize);

        if(price1 != null){
            resultVo.setCode(200);
            resultVo.setMsg("查询成功");
            resultVo.setData(price1);
        }else {
            resultVo.setCode(500);
            resultVo.setMsg("没有你想要的查询值");
        }
        return gson.toJson(resultVo);

    }
//修改水电标准
    @PostMapping("/update")
    public String updatePrice(@RequestBody Price price){
        ResultVo resultVo = new ResultVo<>();
        int isUpdate = priceService.updatePrice(price);
        if(isUpdate != 0){
            resultVo.setCode(200);
            resultVo.setMsg("修改成功");
        }else {
            resultVo.setCode(500);
            resultVo.setMsg("修改失败");
        }
        return gson.toJson(resultVo);
        }
}
