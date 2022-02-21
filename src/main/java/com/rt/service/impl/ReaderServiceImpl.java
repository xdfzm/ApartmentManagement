package com.rt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rt.dao.ReaderDao;
import com.rt.pojo.Book;
import com.rt.pojo.Reader;
import com.rt.pojo.vo.ReaderVo;
import com.rt.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    private ReaderDao readerDao;

    @Override
    public List<Reader> selectAllReader() {
        return readerDao.selectAllReader();
    }

    @Override
    public PageInfo<Reader> splitPage(int pageNum, int pageSize){
        //分页插件使用 pageHelper工具类完成分页设置
        PageHelper.startPage(pageNum, pageSize);

        List<Reader> list = readerDao.selectAllReader();
        //将查询到的集合封装到 pageInfo 对象中
        PageInfo<Reader> pageInfo = new PageInfo<>(list);
        for(Reader b: list){
            System.out.println(b);
        }
        return pageInfo;
    }

    @Override
    public PageInfo<Reader> splitPageVo(ReaderVo readerVo, int pageSize) {
        PageHelper.startPage(readerVo.getPage(),pageSize);
        List<Reader> list = readerDao.selectByCondition(readerVo);
        return new PageInfo<>(list);
    }

    @Override
    public int insert(Reader reader) {
        int result = -1;
        if(readerDao.insert(reader)>0){
            result=1;
        }
        return result;
    }

    @Override
    public Reader selectReaderByReaderId(int id) {
        Reader result =null;
        result = readerDao.selectReaderByReaderId(id);
        return result;
    }

    @Override
    public int selectLeftNumberByReaderId(int readerId) {
        return readerDao.selectLeftNumberByReaderId(readerId);
    }

    @Override
    public int updateLeftNumberMinusByReaderId(int readerId) {
        return readerDao.updateLeftNumberMinusByReaderId(readerId);
    }

    @Override
    public int updateLeftNumberPlusByReaderId(int readerId) {
        return readerDao.updateLeftNumberPlusByReaderId(readerId);
    }

    @Override
    public int updateUnreturnedNumberMinusByReaderId(int readerId) {
        return readerDao.updateUnreturnedNumberMinusByReaderId(readerId);
    }

    @Override
    public int updateUnreturnedNumberPlusByReaderId(int readerId) {
        return readerDao.updateUnreturnedNumberPlusByReaderId(readerId);
    }

    @Override
    public String selectProfessionByReaderId(int readerId) {
        return readerDao.selectProfessionByReaderId(readerId);
    }
}
