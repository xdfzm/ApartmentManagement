package com.example.apartmentmanagement.controller;

import com.alibaba.fastjson.JSON;
import com.example.apartmentmanagement.dao.StudentMapper;
import com.example.apartmentmanagement.entity.Student;
import com.example.apartmentmanagement.service.StudentService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"*", "null"})
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private Gson gson = new Gson();

    @Autowired
    private StudentService studentService;
    @GetMapping("/getAll")
    public String getStudents(){
        Map<String, Object> result = new HashMap<>();

        List<Student> students = studentService.findAllStudents();

        if(students != null){
            result.put("result", "success");
        }else {
            result.put("result", "fail");
        }
        result.put("studentList", students);
        return gson.toJson(result);

    }
//
//    @GetMapping("/get")
//    public String getStudent(@RequestBody String stuId, @RequestBody String dormId){
//        Map<String, Object> result = new HashMap<>();
//        Student s = gson.fromJson(stuId, Student.class);
//        String sId = s.getStuId();
//        Student student = studentService.findStudentById(sId);
//        if(student != null){
//            result.put("result", "success");
//        }else {
//            result.put("result", "fail");
//        }
//        result.put("student", student);
//        return gson.toJson(result);
//    }

    @GetMapping("/get")
    public String getStudent(@RequestBody String student){
        Map<String, Object> result = new HashMap<>();
        Student s = gson.fromJson(student, Student.class);
        List<Student> retStu = studentService.findStudent(s);
        if(retStu.size() != 0){
            result.put("result", "success");
        }else {
            result.put("result", "fail");
        }
        result.put("student", retStu);
        return gson.toJson(result);
    }
    @GetMapping("/gettest/{id}/{currentPage}/{pageSize}")
    public String getStudentTest(@PathVariable("id") String id,
                                 @PathVariable("currentPage") int currentPage,
                                 @PathVariable("pageSize") int pageSize,
                                 @RequestBody String[] ids){
        System.out.println(id);
        System.out.println(currentPage);
        System.out.println(pageSize);
        for (String s : ids) {
            System.out.println(id);
        }
        return JSON.toJSONString(ids);
    }
    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        System.out.println(student);
        int isInsert = studentService.insertStudent(student);
        Map<String, Object> result = new HashMap<>();
        if(isInsert == 0){
            result.put("result", "fail");
        }else {
            result.put("result", "success");
        }
        return gson.toJson(result);
    }

    @PostMapping("/delete")
    public String deleteStudent(@RequestBody String stuId){
        Student s = gson.fromJson(stuId, Student.class);
        String sId = s.getStuId();
        int isDelete = studentService.deleteStudent(sId);
        Map<String, Object> result = new HashMap<>();
        if(isDelete == 0){
            result.put("result", "fail");
        }else {
            result.put("result", "success");
        }
        return gson.toJson(result);
    }

    @PostMapping("/update")
    public String updateStudent(@RequestBody Student student){
        System.out.println(student);
        int isUpdate = studentService.updateStudent(student);
        Map<String, Object> result = new HashMap<>();
        if(isUpdate == 0){
            result.put("result", "fail");
        }else {
            result.put("result", "success");
        }
        return gson.toJson(result);
    }

}
