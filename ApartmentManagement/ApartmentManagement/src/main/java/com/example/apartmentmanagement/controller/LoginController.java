package com.example.apartmentmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {


    @GetMapping("/login")
    public String test(@RequestBody List<Integer> ids){
        for (Integer id : ids) {
            System.out.println(id);
        }
        return null;
    }
}
