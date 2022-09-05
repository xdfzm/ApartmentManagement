package com.example.apartmentmanagement.dao;

import com.example.apartmentmanagement.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {
    Student selectById(@Param("stuId") String stuId);
//    Student selectById();
    List<Student> selectAllStudents();

    List<Student> selectStudent(Student student);
    int insertStudent(Student student);

    int updateStudent(Student student);

    int deleteStudent(String stuId);
}
