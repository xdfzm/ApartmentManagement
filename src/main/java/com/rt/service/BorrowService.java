package com.rt.service;

import com.github.pagehelper.PageInfo;
import com.rt.pojo.Book;
import com.rt.pojo.DTO.detailDTO;
import com.rt.pojo.vo.BookVo;
import com.rt.pojo.vo.DetailDTOVo;

public interface BorrowService {

    PageInfo<detailDTO> splitPage(int pageNum, int pageSize);

    PageInfo<detailDTO> splitPageVo(DetailDTOVo detailDTOVo, int pageSize);
}
