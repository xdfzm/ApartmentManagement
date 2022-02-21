package com.rt.test;


import com.github.pagehelper.PageInfo;
import com.rt.dao.BookDao;
import com.rt.dao.BorrowDao;
import com.rt.dao.DetailDao;
import com.rt.dao.ReaderDao;
import com.rt.pojo.Book;
import com.rt.pojo.DTO.detailDTO;
import com.rt.pojo.Detail;
import com.rt.pojo.Reader;
import com.rt.pojo.vo.BookVo;
import com.rt.pojo.vo.DetailDTOVo;
import com.rt.pojo.vo.ReaderVo;
import com.rt.service.BookService;
import com.rt.service.BorrowService;
import com.rt.service.DetailService;
import com.rt.service.ReaderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MyTest {

    @Autowired
    BookDao bookDao;

    @Autowired
    ReaderDao readerDao;

    @Autowired
    ReaderService readerService;

    @Autowired
    DetailDao detailDao;

    @Autowired
    BookService bookService;

    @Autowired
    BorrowDao borrowDao;

    @Autowired
    BorrowService borrowService;

    @Autowired
    DetailService detailService;
//    @Autowired
//    BorrowDao borrowDao;
    @Test
    public void testBookSelectCondition(){
        BookVo bookVo = new BookVo();
        bookVo.setBookName("三");
        bookVo.setAuthor("a");
        List<Book> list = bookDao.selectAllBook();
        List<Book> list2 = bookDao.selectByCondition(bookVo);
        list.forEach(b-> System.out.println(b));
        System.out.println("=================================================================");
        list2.forEach(b-> System.out.println(b));
    }

    @Test
    public void testReaderSelectCondition(){
        ReaderVo readerVo = new ReaderVo();
//        readerVo.setName("三");
        readerVo.setProfession("教师");
        List<Reader> list = readerDao.selectAllReader();
        list.forEach(reader -> System.out.println(reader));
        List<Reader> list1 = readerDao.selectByCondition(readerVo);
        System.out.println("===================================");
        list1.forEach(reader -> System.out.println(reader));
    }

    @Test
    public void testBookSelectByIsbnCondition(){
        Book b = bookDao.selectByIsbn("9787020007");
        System.out.println(b);
    }

    @Test
    public void testBookInsert(){
        Book b = new Book();
        b.setIsbn("9287020007");
        b.setBookName("闭上你的嘴巴");
        b.setAuthor("嘻嘻");
        b.setType("A");
        b.setPrice(12);
        b.setPublisher("神奇出版社");
        b.setPublishYear(2021);
        b.setTotalNumber(20);
        b.setLentNumber(0);
        int insert = bookDao.insert(b);
        System.out.println(insert);
    }

    @Test
    public void testReaderInsert(){
        Reader reader = new Reader();
        reader.setName("孙亚");
        reader.setSex("男");
        reader.setProfession("技术员");
        reader.setPhoneNumber("182736162364");
        reader.setEmail("189237@qq.com");
        reader.setProfessionNumber("t123");
        reader.setAddress("东海龙宫");
        reader.setLeftNumber(5);
        reader.setUnreturnedNumber(0);
        int insert =  readerDao.insert(reader);
        System.out.println(insert);
    }

    @Test
    public void testReaderSelect(){
        Reader reader = readerDao.selectReaderByReaderId(1);
        System.out.println(reader);
    }

    @Test
    public void testReaderServiceSelect(){
        Reader reader= readerService.selectReaderByReaderId(1);
        System.out.println(reader);
    }
    @Test
    public void testSelectLeft(){
        int i = bookDao.selectLeftNumberByIsbn("9787020002207");
        System.out.println(i);
    }
    @Test
    public void testReaderSelectLeft(){
        int i = readerDao.selectLeftNumberByReaderId(6);
        System.out.println(i);
    }
    @Test
    public void updateLentNumberMinusByIsbn(){
        int i = bookDao.updateLentNumberMinusByIsbn("9787020002207");
        System.out.println(i);
    }

    @Test
    public void updateLentNumberPlusByIsbn(){
        int i = bookDao.updateLentNumberPlusByIsbn("9787020002207");
        System.out.println(i);
    }
    @Test
    public void rd(){
        //当天
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        //到期
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH,30);
        Date dueDate = c.getTime();
        java.sql.Date sqlDueDate = new java.sql.Date(dueDate.getTime());
        java.sql.Date returnDate = null;
    }
    @Test
    public void professionSelect(){
        String s = readerDao.selectProfessionByReaderId(1);
        System.out.println(s);
    }

    @Test
    public void testReaderService(){
        int i = readerService.updateLeftNumberPlusByReaderId(1);
        System.out.println(i);
    }

    @Test
    public void testDetail(){
        //当天
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        //到期
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH,30);
        Date dueDate = c.getTime();
        java.sql.Date sqlDueDate = new java.sql.Date(dueDate.getTime());
        java.sql.Date returnDate = null;

        Detail detail = new Detail();
        detail.setIsbn("1111");
        detail.setReaderId(1);
        detail.setBorrowDate(sqlDate);
        detail.setDueDate(sqlDueDate);
        detail.setReturnDate(returnDate);
        System.out.println(detail);

        detailDao.insert(detail);


    }

    @Test
    public void tete(){
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH,30);
        Date returnDate = c.getTime();
        java.sql.Date sqlReturnDate = new java.sql.Date(returnDate.getTime());
        System.out.println(sqlReturnDate);
        int i  = detailDao.updateReturnDateByReaderIdAndIsbn(8,"1111",sqlReturnDate);
        System.out.println(i);
    }

//
//    @Test
//    public void testBorrow(){
//        List<Borrow> borrows = borrowDao.selectAllBorrows();
//        for(Borrow b : borrows){
//            System.out.println(b);
//        }
//    }

//    @Test
//    public void selectByIsbn(){
//        Detail detail = detailDao.selectDetailByIsbn("00000");
//        System.out.println(detail);
//    }

    @Test
    public void deleteByIsbn(){
        int i = bookService.deleteByIsbn("09909");
        System.out.println(i);
    }

    @Test
    public void tesdsasdas(){
        List<detailDTO> detail = borrowDao.detail();
        System.out.println(detail);
    }


    @Test
    public void testasd2asd(){
        DetailDTOVo detailDTOVo = new DetailDTOVo();
        detailDTOVo.setReturned("-1");
        PageInfo<detailDTO> detailDTOS = borrowService.splitPageVo(detailDTOVo,5);
        System.out.println(detailDTOS);
    }

    @Test
    public void testselectDetail(){
        List<Detail> details = detailService.selectDetailByIsbn("9787805212321");
        System.out.println(details.size());
    }

    @Test
    public void testinsert(){
        Book book = new Book();
        book.setIsbn("12312312451241245");
        book.setBookName("tasdasd");
        book.setAuthor("123asd");
        book.setPrice(123123);
        book.setPublisher("asdasfxczx");
        book.setPublishYear(1231);
        book.setTotalNumber(2);
        book.setLentNumber(0);
        int insert = bookDao.insert(book);
        System.out.println(insert);
    }
}
