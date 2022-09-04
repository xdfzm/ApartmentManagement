package com.example.apartmentmanagement;

import com.alibaba.fastjson.JSON;
import com.example.apartmentmanagement.dao.StudentMapper;
import com.example.apartmentmanagement.dao.UserMapper;
import com.example.apartmentmanagement.entity.Student;
import com.example.apartmentmanagement.entity.User;
import com.example.apartmentmanagement.service.StudentService;
import com.example.apartmentmanagement.service.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ApartmentManagementApplicationTests {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
    }

    @Test
    void testStudentMapper(){

        List<Student> students = studentMapper.selectAllStudents();
        Gson gson = new Gson();
        System.out.println(gson.toJson(students));
        System.out.println();
        System.out.println(students);
    }

    @Test
    void testInsertMapper(){
        Student student = new Student();
        student.setStuId("22020200027");
        student.setDormId("121");
        student.setFaculty("计算机科学与技术");
        student.setMajor("软件工程");
        student.setGrade(1);
        student.setStuName("kiki");
        student.setStuAge(20);
        student.setPhoneNumber("8278271881");
        student.setStuSex("男");

        int i = studentService.insertStudent(student);
        System.out.println(i);
    }
    @Test
    void testService(){
        List<Student> allStudent = studentService.findAllStudents();

        System.out.println(allStudent);
    }

    @Test
    void testUpdate(){
        Student student = new Student();
        student.setStuId("220202000222");
        student.setDormId("121");
        student.setFaculty("计算机科学与技术");
        student.setMajor("软件工程");
        student.setGrade(1);
//        student.setStuName("kiki");
        student.setStuAge(20);
        student.setPhoneNumber("8278271881");
        student.setStuSex("男");
        int i = studentService.updateStudent(student);
        System.out.println(i);
    }

    @Test
    void testDelete(){
        int i = studentMapper.deleteStudent("22020200024");
        System.out.println(i);
    }

    @Test
    void test(){

    }

    @Test
    void test1(){
        int i = userMapper.deleteUser("admin2");
        System.out.println(i);
    }
    
    @Test
    void test123(){
        List<User> users = userService.selectUser(null);
        System.out.println(users);
    }

    @Test
    void test1234(){
        User user = new User();
        user.setUserName("dorm2");
        user.setUserType("dorm");
        int i = userMapper.insertUser(user);
        System.out.println(i);


    }


}
