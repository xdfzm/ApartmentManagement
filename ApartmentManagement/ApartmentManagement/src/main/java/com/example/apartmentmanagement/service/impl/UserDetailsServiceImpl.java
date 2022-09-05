package com.example.apartmentmanagement.service.impl;


import com.example.apartmentmanagement.dao.UserMapper;
import com.example.apartmentmanagement.entity.LoginUser;
import com.example.apartmentmanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("==="+username);
        //查询用户信息
        User u = new User();
        u.setUserName(username);
        System.out.println(u);

        List<User> users = userMapper.selectUser(u);
        System.out.println(users);

        //没有查询到用户就抛出异常
        if(users.size() == 0){
            throw new RuntimeException("用户名或者密码错误");
        }
        User user = users.get(0);
        System.out.println(user);
        // 对应权限信息
//        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        List<String> list = new ArrayList<>();
        list.add(users.get(0).getUserType());


//        List<String> list = new ArrayList<>(Arrays.asList("test","admin"));

//有用户把用户封装成userdetails返回
        return new LoginUser(user, list);

    }
}
