package com.example.apartmentmanagement.service;

import com.example.apartmentmanagement.dao.StudentMapper;
import com.example.apartmentmanagement.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public int insertStudent(Student student){
        Student student1 = studentMapper.selectById(student.getStuId());
        int result = 0;//0为失败，1为插入成功
        System.out.println(student1);
        if(student1 == null){
             result = studentMapper.insertStudent(student);
        }
        return result;
    }

    public List<Student> findStudent(Student student){
        List<Student> students = studentMapper.selectStudent(student);
        return students;
    }
    public List<Student> findAllStudents(){
        List<Student> students = studentMapper.selectAllStudents();
        return students;
    }

    public Student findStudentById(String stuId){
        Student student = studentMapper.selectById(stuId);
        return student;
    }

    public int updateStudent(Student student){
         Student student1 = studentMapper.selectById(student.getStuId());
         int result = 0;
         if(student1 != null){
             result = studentMapper.updateStudent(student);
         }
        return result;
    }
    public int deleteStudent(String stuId){
        Student student1 = studentMapper.selectById(stuId);
        int result = 0;
        if(student1 != null){
            result = 1;
            studentMapper.deleteStudent(stuId);
        }
        return result;
    }

}
