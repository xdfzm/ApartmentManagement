package com.rt.controller;

import com.github.pagehelper.PageInfo;
import com.rt.pojo.Book;
import com.rt.pojo.Detail;
import com.rt.pojo.vo.BookVo;
import com.rt.service.BookService;
import com.rt.service.DetailService;
import com.rt.util.constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookController {
    public static final int PAGE_SIZE = 5;
    @Autowired
    private BookService bookService;

    @Autowired
    private DetailService detailService;
    @RequestMapping("/login.do")
    public String login(HttpServletRequest request) {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        request.getSession().setAttribute(constant.USER_SESSION,request.getSession().getId());
        return "/jsp/main.jsp";
    }

    @RequestMapping("/bookList.do")
    public String getAll(HttpServletRequest request){
        List<Book> list = bookService.selectALlBooks();
        request.setAttribute("list",list);
        System.out.println("listsizzzzzzzzzzzzzzz"+list.size());
        System.out.println("login.do   sout");
        for(Book b : list){
            System.out.println(b);
        }

        return "/jsp/books.jsp";
    }


    @RequestMapping("/split.do")
    public String split(HttpServletRequest request) {
        PageInfo info = null;
        info = bookService.splitPage(1, PAGE_SIZE);
        System.out.println(info);
        request.setAttribute("info", info);
        return "/jsp/books.jsp";
    }


//    带参数的
        @ResponseBody
        @RequestMapping("/ajaxSplit.do")
        public void ajaxsplit(BookVo bookVo, HttpSession session) {
            PageInfo info = null;
            info = bookService.splitPageVo(bookVo,PAGE_SIZE);
            if(info.getPageNum()==0){
                info.setPageNum(1);
                if(info.getPages()>1){
                    info.setNextPage(2);
                }

            }
            session.setAttribute("info", info);
        }

    @ResponseBody
    @RequestMapping(value = "/delete.do", produces = "text/html;charset=utf-8")
    public String delete(String isbn,BookVo bookVo, HttpSession session) {
        System.out.println(isbn);
        System.out.println(bookVo);

        //从detail表中查询，是否有未归还的书
        List<Detail> detail = detailService.selectDetailByIsbn(isbn);

        PageInfo info = null;
        info = bookService.splitPageVo(bookVo,PAGE_SIZE);
        session.setAttribute("info", info);
        //如果有未归还的则无法删除
        if(detail.size()>0){
            return "请先归还图书";
        }  //无未归还的直接删除
        else {
            int currentPage = bookVo.getPage();
            int i = bookService.deleteByIsbn(isbn);
            if(i<1){
                return "删除失败";
            }
            info = bookService.splitPageVo(bookVo,PAGE_SIZE);
            //当删除的是最后一条记录的时候就跳转到上一页。
            if(info.getList().size()==0){
                bookVo.setPage(currentPage-1);
                info=bookService.splitPageVo(bookVo,PAGE_SIZE);
            }
            session.setAttribute("info", info);
            return "删除成功";
        }


    }

    @RequestMapping("/checkIsbn.do")
    @ResponseBody
    public String checkIsbn(String isbn){
        Book book = null;
        String result = "exist";
        book = bookService.selectBookByIsbn(isbn);
        if(book==null){
            result = "notexist";
        }
        System.out.println("进入了checkisbn"+result+"=================");
        return  result;
    }
    @RequestMapping(value = "/addBook.do", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addBook(@ModelAttribute Book book,HttpServletRequest request){
        String msg = "";
        if(bookService.insert(book)>0){
            msg="添加成功";
        }
        else{
            msg="添加失败";
        }

        return msg;
    }
}
