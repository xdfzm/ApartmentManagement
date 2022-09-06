package com.example.apartmentmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String stuId;
    private String stuName;
    private String stuSex;
    private Integer stuAge;
    private String major;
    private String faculty;
    private Integer grade;
    private Double waterNumber;
    private String phoneNumber;
    private Double electricNumber;

}
