package com.example.apartmentmanagement.entity;

public class Student {

    private String stuId;
    private String dormId;
    private String stuName;
    private String stuSex;
    private Integer stuAge;
    private String major;
    private String faculty;
    private Integer grade;
    private Double useNumber;
    private String phoneNumber;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId='" + stuId + '\'' +
                ", dormId='" + dormId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuSex='" + stuSex + '\'' +
                ", stuAge=" + stuAge +
                ", major='" + major + '\'' +
                ", faculty='" + faculty + '\'' +
                ", grade=" + grade +
                ", useNumber=" + useNumber +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Double getUseNumber() {
        return useNumber;
    }

    public void setUseNumber(Double useNumber) {
        this.useNumber = useNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Student(String stuId, String dormId, String stuName, String stuSex, Integer stuAge, String major, String faculty, Integer grade, Double useNumber, String phoneNumber) {
        this.stuId = stuId;
        this.dormId = dormId;
        this.stuName = stuName;
        this.stuSex = stuSex;
        this.stuAge = stuAge;
        this.major = major;
        this.faculty = faculty;
        this.grade = grade;
        this.useNumber = useNumber;
        this.phoneNumber = phoneNumber;
    }
}
