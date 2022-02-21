package com.rt.dao;

import com.rt.pojo.DTO.detailDTO;
import com.rt.pojo.vo.DetailDTOVo;

import java.util.List;
import java.util.Map;

public interface BorrowDao {
    public List<detailDTO> detail();

    public List<detailDTO> selectByCondition(DetailDTOVo detailDTOVo);

}
