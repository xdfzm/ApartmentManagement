package com.example.apartmentmanagement.controller;

import com.example.apartmentmanagement.dao.SysMapper;
import com.example.apartmentmanagement.utils.ResultVo;
import com.google.gson.Gson;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/system")
//@PreAuthorize("hasAuthority('admin')")
public class SystemController {

    @Autowired
    private SysMapper sysMapper;

    @Autowired
    private Gson gson;
    @GetMapping("/get")
    public String getFee(){
        ResultVo resultVo = new ResultVo<>();
        resultVo.setCode(200);
        resultVo.setMsg("查费成功");
        Integer i = sysMapper.selectFee();
        resultVo.setData(i);
        return gson.toJson(resultVo);
    }

    @PostMapping("/update")
    public String updateFee( Integer fee){
        ResultVo resultVo = new ResultVo<>();
        System.out.println(fee);

        Integer i = 0;
        i= sysMapper.updateFee(fee);
        if(i != 0 ){
            resultVo.setCode(200);
            resultVo.setMsg("修改成功");
        } else {
          resultVo.setCode(500);
          resultVo.setMsg("修改失败");
        }

        return gson.toJson(resultVo);
    }
}
