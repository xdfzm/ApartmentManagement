package com.example.apartmentmanagement.service;

import com.example.apartmentmanagement.entity.ResponseResult;
import com.example.apartmentmanagement.entity.User;


public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
