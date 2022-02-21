package com.rt.dao;

import com.rt.pojo.Reader;
import com.rt.pojo.vo.ReaderVo;

import java.util.List;

public interface ReaderDao {

    List<Reader> selectAllReader();

    List<Reader> selectByCondition(ReaderVo readerVo);

    int insert(Reader reader);

    Reader selectReaderByProfessionNumber(String professionNumber);
    //通过id选择读者
    Reader selectReaderByReaderId(int id);


    //通过id查看读者的剩余借书数量
    int selectLeftNumberByReaderId(int readerId);
    //通过id来减少读者的剩余
    int updateLeftNumberMinusByReaderId(int readerId);
    //通过id来增加读者的剩余
    int updateLeftNumberPlusByReaderId(int readerId);
    //通过id来减少读者的未归还数量
    int updateUnreturnedNumberMinusByReaderId(int readerId);
    //通过id来增加读者的未归还数量
    int updateUnreturnedNumberPlusByReaderId(int readerId);
    //通过id来查询读者的职业
    String selectProfessionByReaderId(int readerId);
}
