package com.rt.service;

import com.github.pagehelper.PageInfo;
import com.rt.pojo.Book;
import com.rt.pojo.Reader;
import com.rt.pojo.vo.ReaderVo;

import java.util.List;

public interface ReaderService {

    List<Reader> selectAllReader();

    PageInfo<Reader> splitPage(int pageNum, int pageSize);

    PageInfo<Reader> splitPageVo(ReaderVo readerVo,int pageSize);

    int insert(Reader reader);

    Reader selectReaderByReaderId(int id);

    //通过id查看读者的剩余借书数量
    int selectLeftNumberByReaderId(int ReaderId);
    //通过id来减少读者的剩余
    int updateLeftNumberMinusByReaderId(int ReaderId);
    //通过id来增加读者的剩余
    int updateLeftNumberPlusByReaderId(int ReaderId);
    //通过id来减少读者的未归还数量
    int updateUnreturnedNumberMinusByReaderId(int ReaderId);
    //通过id来增加读者的未归还数量
    int updateUnreturnedNumberPlusByReaderId(int ReaderId);
    //通过id来查询读者的职业
    String selectProfessionByReaderId(int ReaderId);
}
