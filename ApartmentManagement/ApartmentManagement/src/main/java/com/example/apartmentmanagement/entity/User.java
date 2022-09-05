package com.example.apartmentmanagement.entity;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private Long userId;
    private String userName;

    private String password;
    private String userType; //admin为系统管理员 dorm为宿舍管理员

}
