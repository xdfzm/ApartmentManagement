package com.example.apartmentmanagement.controller;

import com.example.apartmentmanagement.entity.Student;
import com.example.apartmentmanagement.entity.User;
import com.example.apartmentmanagement.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"*", "null"})
@RequestMapping("/user")
public class UserController {
    @Autowired
    private Gson gson;
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public String getUser(@RequestBody User user){
        System.out.println(user);
        Map<String, Object> result = new HashMap<>();
        List<User> users = userService.selectUser(user);
        if(users.size() != 0){
            result.put("result", "success");
        }else {
            result.put("result", "fail");
        }
        result.put("student", users);
        return gson.toJson(result);
    }

    @GetMapping("/getAll")
    public String getUsers(){

        Map<String, Object> result = new HashMap<>();
        List<User> users = userService.selectAllUser();
        if(users.size() != 0){
            result.put("result", "success");
        }else {
            result.put("result", "fail");
        }
        result.put("student", users);
        return gson.toJson(result);
    }

//    @PostMapping("/add")
//    public String addStudent(@RequestBody Student student){
//        System.out.println(student);
//        int isInsert = studentService.insertStudent(student);
//        Map<String, Object> result = new HashMap<>();
//        if(isInsert == 0){
//            result.put("result", "fail");
//        }else {
//            result.put("result", "success");
//        }
//        return gson.toJson(result);
//    }

//    @PostMapping("/delete")
//    public String deleteStudent(@RequestBody String stuId){
//        Student s = gson.fromJson(stuId, Student.class);
//        String sId = s.getStuId();
//        int isDelete = studentService.deleteStudent(sId);
//        Map<String, Object> result = new HashMap<>();
//        if(isDelete == 0){
//            result.put("result", "fail");
//        }else {
//            result.put("result", "success");
//        }
//        return gson.toJson(result);
//    }
}
