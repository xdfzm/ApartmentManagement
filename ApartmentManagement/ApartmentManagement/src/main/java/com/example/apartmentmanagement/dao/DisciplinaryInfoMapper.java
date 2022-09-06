package com.example.apartmentmanagement.dao;

import com.example.apartmentmanagement.entity.DisciplinaryInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 违纪信息Mapper接口
 * 
 * @author suqi
 * @date 2022-09-05
 */
@Mapper
public interface DisciplinaryInfoMapper 
{
    /**
     * 查询违纪信息
     * 
     * @param stuId 违纪信息主键
     * @return 违纪信息
     */
    public DisciplinaryInfo selectDisciplinaryInfoByStuId(String stuId);

    /**
     * 查询违纪信息列表
     * 
     * @param disciplinaryInfo 违纪信息
     * @return 违纪信息集合
     */
    public List<DisciplinaryInfo> selectDisciplinaryInfoList(DisciplinaryInfo disciplinaryInfo);

    /**
     * 新增违纪信息
     * 
     * @param disciplinaryInfo 违纪信息
     * @return 结果
     */
    public int insertDisciplinaryInfo(DisciplinaryInfo disciplinaryInfo);

    /**
     * 修改违纪信息
     * 
     * @param disciplinaryInfo 违纪信息
     * @return 结果
     */
    public int updateDisciplinaryInfo(DisciplinaryInfo disciplinaryInfo);

    /**
     * 删除违纪信息
     * 
     * @param stuId 违纪信息主键
     * @return 结果
     */
    public int deleteDisciplinaryInfoByStuId(String stuId);

    /**
     * 批量删除违纪信息
     * 
     * @param stuIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDisciplinaryInfoByStuIds(String[] stuIds);
}
