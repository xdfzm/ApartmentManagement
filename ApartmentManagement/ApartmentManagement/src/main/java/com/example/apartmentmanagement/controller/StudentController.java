package com.example.apartmentmanagement.controller;

import com.alibaba.fastjson.JSON;
import com.example.apartmentmanagement.dao.StudentMapper;
import com.example.apartmentmanagement.entity.Student;
import com.example.apartmentmanagement.service.LiveService;
import com.example.apartmentmanagement.service.StudentService;
import com.example.apartmentmanagement.utils.ResultVo;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
//@PreAuthorize("hasAnyAuthority('admin','dorm')")
public class StudentController {

    @Autowired
    private Gson gson = new Gson();

    @Autowired
    private StudentService studentService;

    @Autowired
    private LiveService liveService;


//    @GetMapping("/get")
//    public String getStudent(int currentPage, int pageSize, Student student){
//        ResultVo resultVo = new ResultVo<>();
//        PageInfo<Student> student1 = studentService.findStudent(currentPage, pageSize, student);
//        if(student1.getSize() != 0){
//            resultVo.setCode(200);
//            resultVo.setMsg("查询成功");
//            resultVo.setData(student1);
//        }else {
//            resultVo.setCode(500);
//            resultVo.setMsg("没有你想要的查询结果");
//
//        }
//        return gson.toJson(resultVo);
//    }

    @GetMapping("/get")
    public String getStudent(int currentPage, int pageSize, Student student){
        ResultVo resultVo = new ResultVo<>();
        PageInfo<Student> student1 = studentService.findStudent(currentPage, pageSize, student);

        if(student1.getSize() != 0){
            resultVo.setCode(200);
            resultVo.setMsg("查询成功");
            List<Student> list = student1.getList();
            for (Student student2 : list) {

            }

            resultVo.setData(student1);
        }else {
            resultVo.setCode(500);
            resultVo.setMsg("没有你想要的查询结果");

        }
        return gson.toJson(resultVo);
    }
    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        System.out.println("===========");
        System.out.println(student);
        ResultVo resultVo = new ResultVo<>();
        int isInsert = studentService.insertStudent(student);
        if(isInsert != 0){
            resultVo.setCode(200);
            resultVo.setMsg("添加成功");
        }else {
            resultVo.setCode(500);
            resultVo.setMsg("添加失败");
        }
        return gson.toJson(resultVo);
    }

    @PostMapping("/delete")
    public String deleteStudent(@RequestBody String[] stuId){
        int isDelete = 0;
        for (String s : stuId) {
            isDelete = studentService.deleteStudent(s);
        }

        ResultVo resultVo = new ResultVo<>();

        if(isDelete != 0){
            resultVo.setCode(200);
            resultVo.setMsg("删除成功");
        }else {
            resultVo.setCode(500);
            resultVo.setMsg("删除失败");
        }
        return gson.toJson(resultVo);
    }

    @PostMapping("/update")
    public String updateStudent(@RequestBody Student student){
        ResultVo resultVo = new ResultVo<>();
        int isUpdate = studentService.updateStudent(student);

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
