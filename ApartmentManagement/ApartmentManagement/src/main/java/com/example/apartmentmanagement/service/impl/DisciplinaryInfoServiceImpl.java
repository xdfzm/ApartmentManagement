package com.example.apartmentmanagement.service.impl;

import java.util.List;

import com.example.apartmentmanagement.dao.DisciplinaryInfoMapper;
import com.example.apartmentmanagement.entity.DisciplinaryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.apartmentmanagement.service.IDisciplinaryInfoService;

/**
 * 违纪信息Service业务层处理
 * 
 * @author suqi
 * @date 2022-09-05
 */
@Service
public class DisciplinaryInfoServiceImpl implements IDisciplinaryInfoService 
{
    @Autowired
    private DisciplinaryInfoMapper disciplinaryInfoMapper;

    /**
     * 查询违纪信息
     * 
     * @param stuId 违纪信息主键
     * @return 违纪信息
     */
    @Override
    public DisciplinaryInfo selectDisciplinaryInfoByStuId(String stuId)
    {
        return disciplinaryInfoMapper.selectDisciplinaryInfoByStuId(stuId);
    }

    /**
     * 查询违纪信息列表
     * 
     * @param disciplinaryInfo 违纪信息
     * @return 违纪信息
     */
    @Override
    public List<DisciplinaryInfo> selectDisciplinaryInfoList(DisciplinaryInfo disciplinaryInfo)
    {
        return disciplinaryInfoMapper.selectDisciplinaryInfoList(disciplinaryInfo);
    }

    /**
     * 新增违纪信息
     * 
     * @param disciplinaryInfo 违纪信息
     * @return 结果
     */
    @Override
    public int insertDisciplinaryInfo(DisciplinaryInfo disciplinaryInfo)
    {
        return disciplinaryInfoMapper.insertDisciplinaryInfo(disciplinaryInfo);
    }

    /**
     * 修改违纪信息
     * 
     * @param disciplinaryInfo 违纪信息
     * @return 结果
     */
    @Override
    public int updateDisciplinaryInfo(DisciplinaryInfo disciplinaryInfo)
    {
        return disciplinaryInfoMapper.updateDisciplinaryInfo(disciplinaryInfo);
    }

    /**
     * 批量删除违纪信息
     * 
     * @param stuIds 需要删除的违纪信息主键
     * @return 结果
     */
    @Override
    public int deleteDisciplinaryInfoByStuIds(String[] stuIds)
    {
        return disciplinaryInfoMapper.deleteDisciplinaryInfoByStuIds(stuIds);
    }

    /**
     * 删除违纪信息信息
     * 
     * @param stuId 违纪信息主键
     * @return 结果
     */
    @Override
    public int deleteDisciplinaryInfoByStuId(String stuId)
    {
        return disciplinaryInfoMapper.deleteDisciplinaryInfoByStuId(stuId);
    }
}
