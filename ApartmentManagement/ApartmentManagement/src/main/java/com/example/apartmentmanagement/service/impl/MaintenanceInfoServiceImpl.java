package com.example.apartmentmanagement.service.impl;

import java.util.List;

import com.example.apartmentmanagement.dao.MaintenanceInfoMapper;
import com.example.apartmentmanagement.entity.MaintenanceInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.apartmentmanagement.service.IMaintenanceInfoService;

/**
 * 保修信息Service业务层处理
 * 
 * @author suqi
 * @date 2022-09-05
 */
@Service
public class MaintenanceInfoServiceImpl implements IMaintenanceInfoService 
{
    @Autowired
    private MaintenanceInfoMapper maintenanceInfoMapper;

    /**
     * 查询保修信息
     * 
     * @param id 保修信息主键
     * @return 保修信息
     */
    @Override
    public MaintenanceInfo selectMaintenanceInfoById(Long id)
    {
        return maintenanceInfoMapper.selectMaintenanceInfoById(id);
    }

    /**
     * 查询保修信息列表
     * 
     * @param maintenanceInfo 保修信息
     * @return 保修信息
     */
    @Override
    public PageInfo<MaintenanceInfo> selectMaintenanceInfoList(int currentPage, int pageSize, MaintenanceInfo maintenanceInfo)
    {
        PageHelper.startPage(currentPage, pageSize);

        List<MaintenanceInfo> maintenanceInfos = maintenanceInfoMapper.selectMaintenanceInfoList(maintenanceInfo);

        PageInfo<MaintenanceInfo> maintenanceInfoPageInfo = new PageInfo<>(maintenanceInfos);

        return maintenanceInfoPageInfo;
    }

    /**
     * 新增保修信息
     * 
     * @param maintenanceInfo 保修信息
     * @return 结果
     */
    @Override
    public int insertMaintenanceInfo(MaintenanceInfo maintenanceInfo)
    {
        return maintenanceInfoMapper.insertMaintenanceInfo(maintenanceInfo);
    }

    /**
     * 修改保修信息
     * 
     * @param maintenanceInfo 保修信息
     * @return 结果
     */
    @Override
    public int updateMaintenanceInfo(MaintenanceInfo maintenanceInfo)
    {
        return maintenanceInfoMapper.updateMaintenanceInfo(maintenanceInfo);
    }

    /**
     * 批量删除保修信息
     * 
     * @param ids 需要删除的保修信息主键
     * @return 结果
     */
    @Override
    public int deleteMaintenanceInfoByIds(Long[] ids)
    {
        return maintenanceInfoMapper.deleteMaintenanceInfoByIds(ids);
    }

    /**
     * 删除保修信息信息
     * 
     * @param id 保修信息主键
     * @return 结果
     */
    @Override
    public int deleteMaintenanceInfoById(Long id)
    {
        return maintenanceInfoMapper.deleteMaintenanceInfoById(id);
    }
}
