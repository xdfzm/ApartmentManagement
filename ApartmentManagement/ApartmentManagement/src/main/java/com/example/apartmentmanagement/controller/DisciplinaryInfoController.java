package com.example.apartmentmanagement.controller;

import java.util.List;
import java.util.Objects;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.apartmentmanagement.entity.DisciplinaryInfo;
import com.example.apartmentmanagement.entity.Dorm;
import com.example.apartmentmanagement.entity.Student;
import com.example.apartmentmanagement.service.DormService;
import com.example.apartmentmanagement.service.StudentService;
import com.example.apartmentmanagement.utils.ResultVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.apartmentmanagement.service.IDisciplinaryInfoService;

/**
 * 违纪信息Controller
 * 
 * @author suqi
 * @date 2022-09-05
 */
@RestController
@RequestMapping("/disc")
public class DisciplinaryInfoController
{
    @Autowired
    private IDisciplinaryInfoService disciplinaryInfoService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private DormService dormService;
    /**
     * 查询违纪信息列表
     */
    @GetMapping("/get")
    public String list(int currentPage, int pageSize, DisciplinaryInfo disciplinaryInfo)
    {
        ResultVo resultVo = new ResultVo<>();

        PageInfo<DisciplinaryInfo> list = disciplinaryInfoService.selectDisciplinaryInfoList(currentPage, pageSize, disciplinaryInfo);
        if(list.getList().size()!=0){
            resultVo.setCode(200);
            resultVo.setMsg("查询成功");
            resultVo.setData(list);
        }else{
            resultVo.setCode(500);
            resultVo.setMsg("查询失败");
        }
        return JSON.toJSONString(resultVo);
    }



    /**
     * 获取违纪信息详细信息
     */
    @GetMapping(value = "/{stuId}")
    public String getInfo(@PathVariable("stuId") String stuId)
    {
        return JSON.toJSONString(disciplinaryInfoService.selectDisciplinaryInfoByStuId(stuId));
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
     * 新增违纪信息
     */
    @PostMapping("/add")
    public String add(@RequestBody DisciplinaryInfo disciplinaryInfo)
    {
        ResultVo resultVo = new ResultVo<>();
        //查是否有该学生
        Student studentById = studentService.findStudentById(disciplinaryInfo.getStuId());
        System.out.println("studnet:"+studentById);
        System.out.println(Objects.isNull(studentById));
        if(Objects.isNull(studentById)){
            resultVo.setCode(500);
            resultVo.setMsg("不存在该学生");
            return JSON.toJSONString(resultVo);
        }
        Dorm dorm = new Dorm();
        dorm.setDormId(disciplinaryInfo.getDormitoryNo());
        //查是否有该宿舍
        PageInfo<Dorm> dorm1 = dormService.findDorm(1, 1, dorm);

        if(dorm1.getTotal()==0){
            resultVo.setCode(500);
            resultVo.setMsg("不存在该宿舍");
           return JSON.toJSONString(resultVo);
        }

        int ret = disciplinaryInfoService.insertDisciplinaryInfo(disciplinaryInfo);
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
     * 修改违纪信息
     */
    @PostMapping("/update")
    public String edit(@RequestBody DisciplinaryInfo disciplinaryInfo)
    {
        ResultVo resultVo = new ResultVo<>();
        int i = disciplinaryInfoService.updateDisciplinaryInfo(disciplinaryInfo);
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
     * 删除违纪信息
     */
	@PostMapping("/delete")
    public String remove(@RequestBody String[] stuIds)
    {
        int i = disciplinaryInfoService.deleteDisciplinaryInfoByStuIds(stuIds);
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
