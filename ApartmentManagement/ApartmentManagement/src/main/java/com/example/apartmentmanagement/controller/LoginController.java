package com.example.apartmentmanagement.controller;

import com.example.apartmentmanagement.entity.ResponseResult;
import com.example.apartmentmanagement.entity.User;
import com.example.apartmentmanagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        //登入
        return loginService.login(user);
    }


    @RequestMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
