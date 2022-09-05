package com.example.apartmentmanagement.service;

import com.example.apartmentmanagement.dao.StudentMapper;
import com.example.apartmentmanagement.entity.Student;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

//    public List<Student> findStudent(Student student){
//        List<Student> students = studentMapper.selectStudent(student);
//        return students;
//    }
    public PageInfo<Student> findStudent(int currentPage, int pageSize, Student student){
        PageHelper.startPage(currentPage, pageSize);

        List<Student> students = studentMapper.selectStudent(student);

        PageInfo<Student> pageInfo = new PageInfo<>(students);

         return pageInfo;
    }
//    public List<Student> findAllStudents(){
//        List<Student> students = studentMapper.selectAllStudents();
//        return students;
//    }
      public PageInfo<Student> findAllStudents(int currentPage, int pageSize){
        PageHelper.startPage(currentPage, pageSize);

        List<Student> students = studentMapper.selectAllStudents();

        PageInfo<Student> pageInfo = new PageInfo<>(students);

        return pageInfo;
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
