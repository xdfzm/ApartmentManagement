package com.example.apartmentmanagement.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.example.apartmentmanagement.entity.MaintenanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.apartmentmanagement.service.IMaintenanceInfoService;

/**
 * 保修信息Controller
 * 
 * @author suqi
 * @date 2022-09-05
 */
@RestController
@RequestMapping("/system/main")
public class MaintenanceInfoController
{
    @Autowired
    private IMaintenanceInfoService maintenanceInfoService;

    /**
     * 查询保修信息列表
     */
    @PostMapping("/list")
    public String list(MaintenanceInfo maintenanceInfo)
    {
        List<MaintenanceInfo> list = maintenanceInfoService.selectMaintenanceInfoList(maintenanceInfo);
        return JSON.toJSONString(list);
    }


    /**
     * 获取保修信息详细信息
     */
    @GetMapping(value = "/{id}")
    public String getInfo(@PathVariable("id") Long id)
    {
        return JSON.toJSONString(maintenanceInfoService.selectMaintenanceInfoById(id));
    }

    /**
     * 新增保修信息
     */
    @PostMapping
    public String add(@RequestBody MaintenanceInfo maintenanceInfo)
    {
        return JSON.toJSONString(maintenanceInfoService.insertMaintenanceInfo(maintenanceInfo));
    }

    /**
     * 修改保修信息
     */
    @PutMapping
    public String edit(@RequestBody MaintenanceInfo maintenanceInfo)
    {
        return JSON.toJSONString(maintenanceInfoService.updateMaintenanceInfo(maintenanceInfo));
    }

    /**
     * 删除保修信息
     */
	@DeleteMapping("/{ids}")
    public String remove(@PathVariable Long[] ids)
    {
        return JSON.toJSONString(maintenanceInfoService.deleteMaintenanceInfoByIds(ids));
    }
}
