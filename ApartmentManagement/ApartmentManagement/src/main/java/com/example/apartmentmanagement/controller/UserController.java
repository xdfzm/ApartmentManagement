package com.example.apartmentmanagement.controller;

import com.example.apartmentmanagement.entity.Student;
import com.example.apartmentmanagement.entity.User;
import com.example.apartmentmanagement.service.UserService;
import com.example.apartmentmanagement.utils.ResultVo;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
//@PreAuthorize("hasAuthority('admin')")
public class UserController {
    @Autowired
    private Gson gson;
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public String getUser(int currentPage, int pageSize, User user){
        ResultVo resultVo = new ResultVo<>();
        PageInfo<User> users = userService.selectUser(currentPage, pageSize, user);
        if(users.getSize() != 0){
            resultVo.setCode(200);
            resultVo.setMsg("查询成功");
            resultVo.setData(users);
        }else {
            resultVo.setCode(500);
            resultVo.setMsg("没有你想要的结果");
        }
        return gson.toJson(resultVo);
    }

    @GetMapping("/getAll")
    public String getUsers(){

        Map<String, Object> result = new HashMap<>();

        return gson.toJson(result);
    }

    @PostMapping("/add")
    public String addStudent(@RequestBody User user){
        ResultVo resultVo = new ResultVo<>();
        int isInsert = userService.insertUser(user);;
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
    public String deleteStudent(@RequestBody String[] userId){
        int isDelete = 0;
        for (String u : userId) {
            isDelete = userService.deleteUser(u);
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
}
