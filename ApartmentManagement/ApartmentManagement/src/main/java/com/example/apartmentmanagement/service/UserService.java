package com.example.apartmentmanagement.service;

import com.example.apartmentmanagement.dao.UserMapper;
import com.example.apartmentmanagement.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    public List<User> selectUser(User user){
//        List<User> users = userMapper.selectUser(user);
//        return users;
//    }
    public PageInfo<User> selectUser(int currentPage, int pageSize, User user){
        PageHelper.startPage(currentPage, pageSize);
       List<User> users = userMapper.selectUser(user);
       PageInfo<User> pageInfo = new PageInfo<>(users);
       return pageInfo;
    }
    public PageInfo<User> selectAllUser(int currentPage, int pageSize){
        PageHelper.startPage(currentPage, pageSize);
        List<User> users = userMapper.selectAllUser();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }
    public int insertUser(User user){
        List<User> users = userMapper.selectUser(user);
        int ret = 0;
        if(users.size() == 0){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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


            System.out.println("sjabi");
            return 0;

        }else {
            System.out.println("=======");
            System.out.println("enterservice");
            System.out.println("=======");
            return userMapper.deleteUser(userId);

        }

    }
}
