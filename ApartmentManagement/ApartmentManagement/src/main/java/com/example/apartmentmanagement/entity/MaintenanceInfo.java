package com.example.apartmentmanagement.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 保修信息对象 maintenance_info
 * 
 * @author suqi
 * @date 2022-09-05
 */
public class MaintenanceInfo
{
    private static final long serialVersionUID = 1L;

    /** 保修ID */
    private Long id;

    /** 保修编号 */
    private String warrantyNo;

    /** 保修内容 */
    private String warrantyInfo;

    /** 所属宿舍 */
    private String dormitoryNo;

    /** 申请日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date applicationTime;

    /** 是否审核 */
    private String isState;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWarrantyNo(String warrantyNo) 
    {
        this.warrantyNo = warrantyNo;
    }

    public String getWarrantyNo() 
    {
        return warrantyNo;
    }
    public void setWarrantyInfo(String warrantyInfo) 
    {
        this.warrantyInfo = warrantyInfo;
    }

    public String getWarrantyInfo() 
    {
        return warrantyInfo;
    }
    public void setDormitoryNo(String dormitoryNo) 
    {
        this.dormitoryNo = dormitoryNo;
    }

    public String getDormitoryNo() 
    {
        return dormitoryNo;
    }
    public void setApplicationTime(Date applicationTime) 
    {
        this.applicationTime = applicationTime;
    }

    public Date getApplicationTime() 
    {
        return applicationTime;
    }
    public void setIsState(String isState) 
    {
        this.isState = isState;
    }

    public String getIsState() 
    {
        return isState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("warrantyNo", getWarrantyNo())
            .append("warrantyInfo", getWarrantyInfo())
            .append("dormitoryNo", getDormitoryNo())
            .append("applicationTime", getApplicationTime())
            .append("isState", getIsState())
            .toString();
    }
}
