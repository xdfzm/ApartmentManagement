package com.rt.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rt.dao.BookDao;
import com.rt.pojo.Book;
import com.rt.pojo.vo.BookVo;
import com.rt.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    @Override
    public List<Book> selectALlBooks() {
        return bookDao.selectAllBook();
    }

    @Override
    public  PageInfo<Book> splitPage(int pageNum, int pageSize){
        //分页插件使用 pageHelper工具类完成分页设置
        PageHelper.startPage(pageNum, pageSize);

        //进行 PageInfo的数据封装
        //进行条件的查询操作,必须要创建 ProductInfoExample对象
//        ProductInfoExample example = new ProductInfoExample();
        //设置 排序,按主键降序排序
        //select * from product_info order by p_id desc ;
//        example.setOrderByClause("p_id desc");
        //设置完排序后,取集合,切记切记:一定要在取集合之前,设置 PageHelper.startPage(pageNum, pageSize);
//        List<ProductInfo> list = productInfoMapper.selectByExample(example);
        List<Book> list = bookDao.selectAllBook();
        //将查询到的集合封装到 pageInfo 对象中
        PageInfo<Book> pageInfo = new PageInfo<>(list);
        for(Book b: list){
            System.out.println(b);
        }
        return pageInfo;
    }

    @Override
    public PageInfo<Book> splitPageVo(BookVo bookVo, int pageSize) {
        PageHelper.startPage(bookVo.getPage(), pageSize);
        List<Book> list = bookDao.selectByCondition(bookVo);
        return new PageInfo<>(list);
    }


    @Override
    public Book selectBookByIsbn(String isbn) {
        Book result = null;
        result=bookDao.selectByIsbn(isbn);
        return result;
    }

    @Override
    public int insert(Book book) {
        int result = -1;
        if(bookDao.insert(book)>0){
            result=1;
        }
        return result;
    }

    @Override
    public Integer selectLeftNumberByIsbn(String isbn) {
        System.out.println("-123-----------------========servicebook:");
        return bookDao.selectLeftNumberByIsbn(isbn);
    }

    @Override
    public int updateLentNumberMinusByIsbn(String isbn) {
        return bookDao.updateLentNumberMinusByIsbn(isbn);
    }

    @Override
    public int updateLentNumberPlusByIsbn(String isbn) {
        return bookDao.updateLentNumberPlusByIsbn(isbn);
    }


    @Override
    public int deleteByIsbn(String isbn) {
        int result = -1;
        result = bookDao.deleteByIsbn(isbn);
        return result;
    }
}
