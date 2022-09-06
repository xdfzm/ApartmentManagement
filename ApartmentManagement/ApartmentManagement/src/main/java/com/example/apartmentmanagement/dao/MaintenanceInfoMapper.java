package com.example.apartmentmanagement.dao;

import com.example.apartmentmanagement.entity.MaintenanceInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 保修信息Mapper接口
 * 
 * @author suqi
 * @date 2022-09-05
 */
@Mapper
public interface MaintenanceInfoMapper 
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
    public List<MaintenanceInfo> selectMaintenanceInfoList(MaintenanceInfo maintenanceInfo);

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
     * 删除保修信息
     * 
     * @param id 保修信息主键
     * @return 结果
     */
    public int deleteMaintenanceInfoById(Long id);

    /**
     * 批量删除保修信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaintenanceInfoByIds(Long[] ids);
}
