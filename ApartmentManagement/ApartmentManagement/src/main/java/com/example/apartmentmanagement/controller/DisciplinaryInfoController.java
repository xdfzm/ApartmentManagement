package com.example.apartmentmanagement.controller;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.apartmentmanagement.entity.DisciplinaryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.apartmentmanagement.service.IDisciplinaryInfoService;

/**
 * 违纪信息Controller
 * 
 * @author suqi
 * @date 2022-09-05
 */
@RestController
@RequestMapping("/system/disc")
public class DisciplinaryInfoController
{
    @Autowired
    private IDisciplinaryInfoService disciplinaryInfoService;

    /**
     * 查询违纪信息列表
     */
    @PostMapping("/list")
    public String list(DisciplinaryInfo disciplinaryInfo)
    {
        List<DisciplinaryInfo> list = disciplinaryInfoService.selectDisciplinaryInfoList(disciplinaryInfo);
        return JSON.toJSONString(list);
    }



    /**
     * 获取违纪信息详细信息
     */
    @GetMapping(value = "/{stuId}")
    public String getInfo(@PathVariable("stuId") String stuId)
    {
        return JSON.toJSONString(disciplinaryInfoService.selectDisciplinaryInfoByStuId(stuId));
    }

    /**
     * 新增违纪信息
     */
    @PostMapping
    public String add(@RequestBody DisciplinaryInfo disciplinaryInfo)
    {
        return JSON.toJSONString(disciplinaryInfoService.insertDisciplinaryInfo(disciplinaryInfo));
    }

    /**
     * 修改违纪信息
     */
    @PutMapping
    public String edit(@RequestBody DisciplinaryInfo disciplinaryInfo)
    {
        return JSON.toJSONString(disciplinaryInfoService.updateDisciplinaryInfo(disciplinaryInfo));
    }

    /**
     * 删除违纪信息
     */
	@DeleteMapping("/{stuIds}")
    public String remove(@PathVariable String[] stuIds)
    {
        return JSON.toJSONString(disciplinaryInfoService.deleteDisciplinaryInfoByStuIds(stuIds));
    }
}
