package com.example.apartmentmanagement.controller;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.example.apartmentmanagement.entity.Dorm;
import com.example.apartmentmanagement.entity.MaintenanceInfo;
import com.example.apartmentmanagement.entity.Student;
import com.example.apartmentmanagement.service.DormService;
import com.example.apartmentmanagement.utils.ResultVo;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/main")
public class MaintenanceInfoController
{
    @Autowired
    private IMaintenanceInfoService maintenanceInfoService;

    @Autowired
    private DormService dormService;

    /**
     * 查询保修信息列表
     */
    @GetMapping("/get")
    public String list(int currentPage, int pageSize, MaintenanceInfo maintenanceInfo)
    {
        ResultVo resultVo = new ResultVo<>();
        PageInfo<MaintenanceInfo> maintenanceInfoPageInfo = maintenanceInfoService.selectMaintenanceInfoList(currentPage, pageSize, maintenanceInfo);
        if(maintenanceInfoPageInfo.getTotal()!=0){
            resultVo.setCode(200);
            resultVo.setMsg("查询成功");
            resultVo.setData(maintenanceInfoPageInfo);
        }else {
            resultVo.setCode(500);
            resultVo.setMsg("没有你想要的结果");
        }
        return JSON.toJSONString(resultVo);
    }

/*
ResultVo resultVo = new ResultVo<>();
        if(list.size()!=0){
    resultVo.setCode(200);
    resultVo.setMsg("查询成功");
    resultVo.setData(list);
}else{
    resultVo.setCode(500);
    resultVo.setMsg("查询失败");
}
        return JSON.toJSONString(resultVo);


 */
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
    @PostMapping("/add")
    public String add(@RequestBody MaintenanceInfo maintenanceInfo)
    {        ResultVo resultVo = new ResultVo<>();


        //查是否有宿舍
        Dorm dorm = new Dorm();
        dorm.setDormId(maintenanceInfo.getDormitoryNo());
        //查是否有该宿舍
        PageInfo<Dorm> dorm1 = dormService.findDorm(1, 1, dorm);

        if(dorm1.getTotal()==0){
            resultVo.setCode(500);
            resultVo.setMsg("不存在该宿舍");
            return JSON.toJSONString(resultVo);
        }
        maintenanceInfo.setApplicationTime(new Date());
        int ret = maintenanceInfoService.insertMaintenanceInfo(maintenanceInfo);
        if(ret!=0){
            resultVo.setCode(200);
            resultVo.setMsg("插入成功");
        }else{
            resultVo.setCode(500);
            resultVo.setMsg("插入失败");
        }
        return JSON.toJSONString(resultVo);

    }

    /**
     * 修改保修信息
     */
    @PostMapping("/update")
    public String edit(@RequestBody MaintenanceInfo maintenanceInfo)
    {
        ResultVo resultVo = new ResultVo<>();
        //查是否有宿舍
        Dorm dorm = new Dorm();
        dorm.setDormId(maintenanceInfo.getDormitoryNo());
        //查是否有该宿舍
        PageInfo<Dorm> dorm1 = dormService.findDorm(1, 1, dorm);

        if(dorm1.getTotal()==0){
            resultVo.setCode(500);
            resultVo.setMsg("不存在该宿舍");
            return JSON.toJSONString(resultVo);
        }
        int i = maintenanceInfoService.updateMaintenanceInfo(maintenanceInfo);

        if(i!=0){
            resultVo.setCode(200);
            resultVo.setMsg("修改成功");
        }else{
            resultVo.setCode(500);
            resultVo.setMsg("修改失败");
        }
        return JSON.toJSONString(resultVo);
    }

    /**
     * 删除保修信息
     */
	@PostMapping("/delete")
    public String remove(@RequestBody Long[] ids)
    {
        int i = maintenanceInfoService.deleteMaintenanceInfoByIds(ids);
        ResultVo resultVo = new ResultVo<>();
        if(i!=0){
            resultVo.setCode(200);
            resultVo.setMsg("删除成功");
        }else{
            resultVo.setCode(500);
            resultVo.setMsg("删除失败");
        }
        return JSON.toJSONString(resultVo);
    }
}
