package com.example.apartmentmanagement.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 违纪信息对象 disciplinary_info
 * 
 * @author suqi
 * @date 2022-09-05
 */
public class DisciplinaryInfo
{
    private static final long serialVersionUID = 1L;

    /** 学号 */
    private String stuId;

    /** 姓名 */
    private String stuName;

    /** 违纪内容 */
    private String disciplinaryInfo;

    /** 所属宿舍 */
    private String dormitoryNo;

    /** 违纪时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date disciplinaryTime;

    public void setStuId(String stuId) 
    {
        this.stuId = stuId;
    }

    public String getStuId() 
    {
        return stuId;
    }
    public void setStuName(String stuName) 
    {
        this.stuName = stuName;
    }

    public String getStuName() 
    {
        return stuName;
    }
    public void setDisciplinaryInfo(String disciplinaryInfo) 
    {
        this.disciplinaryInfo = disciplinaryInfo;
    }

    public String getDisciplinaryInfo() 
    {
        return disciplinaryInfo;
    }
    public void setDormitoryNo(String dormitoryNo) 
    {
        this.dormitoryNo = dormitoryNo;
    }

    public String getDormitoryNo() 
    {
        return dormitoryNo;
    }
    public void setDisciplinaryTime(Date disciplinaryTime) 
    {
        this.disciplinaryTime = disciplinaryTime;
    }

    public Date getDisciplinaryTime() 
    {
        return disciplinaryTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stuId", getStuId())
            .append("stuName", getStuName())
            .append("disciplinaryInfo", getDisciplinaryInfo())
            .append("dormitoryNo", getDormitoryNo())
            .append("disciplinaryTime", getDisciplinaryTime())
            .toString();
    }
}
