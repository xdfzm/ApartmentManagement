package com.example.apartmentmanagement;

import com.example.apartmentmanagement.dao.*;
import com.example.apartmentmanagement.entity.Live;
import com.example.apartmentmanagement.entity.Student;
import com.example.apartmentmanagement.entity.User;
import com.example.apartmentmanagement.service.LiveService;
import com.example.apartmentmanagement.service.StudentService;
import com.example.apartmentmanagement.service.UserService;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ApartmentManagementApplicationTests {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentService studentService;

    @Autowired
    private DormMapper dormMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysMapper sysMapper;

    @Autowired
    private LiveService liveService;

    @Autowired
    private LiveMapper liveMapper;
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
//        List<Student> allStudent = studentService.findAllStudents();

//        System.out.println(allStudent);
    }

    @Test
    void testUpdate(){
        Student student = new Student();
        student.setStuName("官");
         List<Student> list = studentMapper.selectStudent(student);
        System.out.println(list);
    }

    @Test
    void testDelete(){
        int i = studentMapper.deleteStudent("22020200024");
        System.out.println(i);
    }

    @Test
    void test() {
        User user = new User();
        user.setUserName("a");
        List<User> user1 = userMapper.selectUser(user);
        System.out.println(user1);
    }


    @Test
    void test1(){

    }
    
    @Test
    void test123(){

    }

    @Test
    void test1234(){
        User user = new User();
        user.setUserName("dorm2");
        user.setPassword("asdasd");
        user.setUserType("dorm");
        int i = userMapper.insertUser(user);
        System.out.println(i);


    }


    @Test
    void test1123123(){
        UserDetails admin = userDetailsService.loadUserByUsername("admin");
        System.out.println(admin);
    }

    @Test
    public void testBcry(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //加密
        String encode = bCryptPasswordEncoder.encode("123456");
//        String encode1 = bCryptPasswordEncoder.encode("1234");
        System.out.println(passwordEncoder.matches("admin",
                "$2a$10$HOsLKt3z8kRYcvKYm0coVOr2mSdphbrDb/5s.QXS1dpjhMWHm8pSK"));
        System.out.println(encode);
//        System.out.println(encode1);

    }

    @Test
    public void testslpit(){

    }

    @Test
    public void testInsert(){
        String s = liveService.selectDormIdByStuId("109");
        System.out.println(s);
    }
}
