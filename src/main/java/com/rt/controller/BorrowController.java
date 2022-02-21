package com.rt.controller;


import com.github.pagehelper.PageInfo;
import com.rt.pojo.Book;
import com.rt.pojo.Detail;
import com.rt.pojo.Reader;
import com.rt.pojo.vo.BookVo;
import com.rt.pojo.vo.DetailDTOVo;
import com.rt.service.BookService;
import com.rt.service.BorrowService;
import com.rt.service.DetailService;
import com.rt.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

@Controller
public class BorrowController {
    public static final int PAGE_SIZE = 5;
    @Autowired
    private BookService bookService;

    @Autowired
    private ReaderService readerService;

    @Autowired
    private DetailService detailService;

    @Autowired
    private BorrowService borrowService;
    @RequestMapping(value = "/borrowBook.do",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String BorrowBook(String isbn,String bookName,String name,Integer id){

        //isbn查询书本的剩余量
        int selectLeftNumberByIsbn = bookService.selectLeftNumberByIsbn(isbn);
        if(selectLeftNumberByIsbn<1){
            return "图书剩余量不够";
        }


        //id查询读者剩余可借阅数量
        int selectLeftNumberByReaderId = readerService.selectLeftNumberByReaderId(id);
        if(selectLeftNumberByReaderId<1){
            return "你无法再借阅更多图书";
        }

        //结果该图书的话就不能借了
        Detail oldDetail = detailService.selectDetailByReaderIdAndIsbn(id,isbn);
        if(oldDetail!=null){
            if(oldDetail.getReturnDate()==null){
                return "你已经借过该图书";
            }
        }

        //isbn修改剩余图书lent_number减少1
        int updateLentNumberPlusByIsbn = bookService.updateLentNumberPlusByIsbn(isbn);


        //id修改读者left_number减少1和unreturned_number增加1
        int updateLeftNumberMinusByReaderId = readerService.updateLeftNumberMinusByReaderId(id);

        int updateUnreturnedNumberPlusByReaderId = readerService.updateUnreturnedNumberPlusByReaderId(id);


        //生成借书Date

            //==当天
            Date date = new Date();
            java.sql.Date borrowDate = new java.sql.Date(date.getTime());
            System.out.println("borrowDate:"+borrowDate);
            //根据身份生成Due_Date
            //==得到身份
            //通过id查询身份
            String profession = readerService.selectProfessionByReaderId(id);
            //==得到相差的时间
            int gapDays=0;
            if("学生".equals(profession)){
                gapDays=15;
            }
            if("教师".equals(profession)){
                gapDays=30;
            }
            if("群众".equals(profession)){
                gapDays=15;
            }
            System.out.println("gapDays");
            //==到期日期
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DAY_OF_MONTH,gapDays);
            Date dateDueDate = c.getTime();
            java.sql.Date dueDate = new java.sql.Date(dateDueDate.getTime());
            System.out.println("dueDate:"+dueDate);

            java.sql.Date returnDate=null;
        //写入detail表格
        Detail detail = new Detail();
        detail.setReaderId(id);
        detail.setIsbn(isbn);
        detail.setBorrowDate(borrowDate);
        detail.setDueDate(dueDate);
        detail.setReturnDate(returnDate);

        detailService.insert(detail);

        //
        return "借阅成功";

    }

    @RequestMapping(value = "/getBookNameByIsbn.do",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getBookNameByIsbn(String isbn){
        Book book = null;
        String result = null;
        book = bookService.selectBookByIsbn(isbn);
        if(book!=null){
            result = book.getBookName();
        }
        System.out.println(result);
        return  result;
    }

    @RequestMapping(value = "/getNameByReaderId.do",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getNameByReaderId(Integer id){

        String result=null;

        Reader reader=readerService.selectReaderByReaderId(id);
        if(reader!=null){
            result = reader.getName();
        }
        System.out.println(result);
        return  result;
    }
    @RequestMapping(value = "/returnBook.do",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String returnBook(String isbn,String bookName,String name,Integer id){
        System.out.println(isbn);
        System.out.println(bookName);
        System.out.println(name);
        System.out.println(id);
        //结果该图书的话就不能借了
        if(detailService.selectDetailByReaderIdAndIsbn(id,isbn)==null){
            return "你未借过该图书";
        }
        //isbn修改剩余图书lent_number增加1
        int updateLentNumberPlusByIsbn = bookService.updateLentNumberMinusByIsbn(isbn);


        //id修改读者left_number增加1和unreturned_number减少1
        int updateLeftNumberMinusByReaderId = readerService.updateLeftNumberPlusByReaderId(id);

        int updateUnreturnedNumberPlusByReaderId = readerService.updateUnreturnedNumberMinusByReaderId(id);

        Detail detail = detailService.selectDetailByReaderIdAndIsbn(id, isbn);
        java.sql.Date dueDate = detail.getDueDate();

        //修改detail中的returnDate
        Date date = new Date();
        java.sql.Date returnDate = new java.sql.Date(date.getTime());
        int i = detailService.updateReturnDateByReaderIdAndIsbn(id, isbn, returnDate);
        if(returnDate.compareTo(dueDate)>0){
            return "还书成功，你已超时还书";
        }
        return "还书成功";

    }

    @RequestMapping("/detailSplit.do")
    public String split(HttpServletRequest request) {
        PageInfo info = null;
        info = borrowService.splitPage(1, PAGE_SIZE);
        System.out.println(info);
        request.setAttribute("info", info);
        return "/jsp/details.jsp";
    }

    @ResponseBody
    @RequestMapping("/detailAjaxSplit.do")
    public void detailAjaxsplit(DetailDTOVo detailDTOVo, HttpSession session) {

        PageInfo info = null;
        info = borrowService.splitPageVo(detailDTOVo,PAGE_SIZE);
        if(info.getPageNum()==0){
            info.setPageNum(1);
            if(info.getPages()>1){
                info.setNextPage(2);
            }

        }
        session.setAttribute("info", info);
    }
}
