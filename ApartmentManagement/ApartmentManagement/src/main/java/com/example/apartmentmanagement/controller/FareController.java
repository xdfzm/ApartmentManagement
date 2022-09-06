package com.example.apartmentmanagement.controller;


import com.example.apartmentmanagement.entity.Fare;
import com.example.apartmentmanagement.service.FareService;
import com.example.apartmentmanagement.utils.ResultVo;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fare")
public class FareController {

    @Autowired
    private  Gson gson = new Gson();

    @Autowired
    private FareService fareService;

    @GetMapping("/get")
    public String getFare(int currentPage, int pageSize, String stuId){
        ResultVo resultVo = new ResultVo<>();
        PageInfo<Fare> fare1 = fareService.findFare(currentPage, pageSize, stuId);
        if(fare1.getSize() != 0){
            resultVo.setCode(200);
            resultVo.setMsg("查询成功");
            resultVo.setData(fare1);
        }else {
            resultVo.setCode(500);
            resultVo.setMsg("没有你想要的查询结果");

        }
        return gson.toJson(resultVo);
    }


}
