package com.example.apartmentmanagement.service;

import com.example.apartmentmanagement.dao.UserMapper;
import com.example.apartmentmanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> selectUser(User user){
        List<User> users = userMapper.selectUser(user);
        return users;
    }
    public List<User> selectAllUser(){
        List<User> users = userMapper.selectAllUser();
        return users;
    }
    public int insertUser(User user){
        List<User> users = userMapper.selectUser(user);
        int ret = 0;
        if(users.size() == 0){
            ret = userMapper.insertUser(user);
        }
        return ret;
    }

    public int deleteUser(long userId){
        User user = new User();
        user.setUserId(userId);
        List<User> users = userMapper.selectUser(user);
        int ret = 0;
        if(users.size() == 0){
            ret = userMapper.insertUser(user);
        }
        return 0;
    }
}
