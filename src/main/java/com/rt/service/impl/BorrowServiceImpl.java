package com.rt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rt.dao.BorrowDao;
import com.rt.pojo.Book;
import com.rt.pojo.DTO.detailDTO;
import com.rt.pojo.vo.DetailDTOVo;
import com.rt.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    BorrowDao borrowDao;
    @Override
    public PageInfo<detailDTO> splitPage(int pageNum, int pageSize) {
        //分页插件使用 pageHelper工具类完成分页设置
        PageHelper.startPage(pageNum, pageSize);
        List<detailDTO> list = borrowDao.detail();
        //将查询到的集合封装到 pageInfo 对象中
        PageInfo<detailDTO> pageInfo = new PageInfo<>(list);
        for(detailDTO d: list){
            System.out.println(d);
        }
        return pageInfo;
    }

    @Override
    public PageInfo<detailDTO> splitPageVo(DetailDTOVo detailDTOVo, int pageSize) {

        PageHelper.startPage(detailDTOVo.getPage(), pageSize);

        List<detailDTO> list = borrowDao.selectByCondition(detailDTOVo);

        return new PageInfo<>(list);
    }
}
