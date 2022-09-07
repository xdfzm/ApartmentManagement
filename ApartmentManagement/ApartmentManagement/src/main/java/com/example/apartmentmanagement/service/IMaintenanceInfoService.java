package com.example.apartmentmanagement.service;

import com.example.apartmentmanagement.entity.MaintenanceInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 保修信息Service接口
 * 
 * @author suqi
 * @date 2022-09-05
 */
public interface IMaintenanceInfoService 
{
    /**
     * 查询保修信息
     * 
     * @param id 保修信息主键
     * @return 保修信息
     */
    public MaintenanceInfo selectMaintenanceInfoById(Long id);

    /**
     * 查询保修信息列表
     * 
     * @param maintenanceInfo 保修信息
     * @return 保修信息集合
     */
    public PageInfo<MaintenanceInfo> selectMaintenanceInfoList(int currentPage, int pageSize, MaintenanceInfo maintenanceInfo);

    /**
     * 新增保修信息
     * 
     * @param maintenanceInfo 保修信息
     * @return 结果
     */
    public int insertMaintenanceInfo(MaintenanceInfo maintenanceInfo);

    /**
     * 修改保修信息
     * 
     * @param maintenanceInfo 保修信息
     * @return 结果
     */
    public int updateMaintenanceInfo(MaintenanceInfo maintenanceInfo);

    /**
     * 批量删除保修信息
     * 
     * @param ids 需要删除的保修信息主键集合
     * @return 结果
     */
    public int deleteMaintenanceInfoByIds(Long[] ids);

    /**
     * 删除保修信息信息
     * 
     * @param id 保修信息主键
     * @return 结果
     */
    public int deleteMaintenanceInfoById(Long id);
}
