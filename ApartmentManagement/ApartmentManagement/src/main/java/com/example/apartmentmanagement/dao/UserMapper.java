package com.example.apartmentmanagement.dao;

import com.example.apartmentmanagement.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {

    int insertUser(User user);

    int deleteUser(String userId);
    List<User> selectUser(User user);
    List<User> selectAllUser();
}
