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

//    List<Student> selectStudent(@Param("stuId") String stuId, @Param("dormId") String dormId,
//                                @Param("stuName") String stuName,@Param("stuSex") String stuSex,
//                                @Param("stuAge") Integer stuAge, @Param("major") String major,
//                                @Param("faculty") String faculty, @Param("grade") Integer  grade);

    List<Student> selectStudent(Student student);
    int insertStudent(Student student);

    int updateStudent(Student student);

    int deleteStudent(String stuId);
}
