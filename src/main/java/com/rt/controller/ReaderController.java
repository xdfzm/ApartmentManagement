package com.rt.controller;


import com.github.pagehelper.PageInfo;
import com.rt.dao.ReaderDao;
import com.rt.pojo.Book;
import com.rt.pojo.Reader;
import com.rt.pojo.vo.BookVo;
import com.rt.pojo.vo.ReaderVo;
import com.rt.service.ReaderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ReaderController {
    public static final int PAGE_SIZE = 5;
    @Autowired
    private ReaderService readerService;

    @RequestMapping("/readerSplit.do")
    public String split(HttpServletRequest request) {
        PageInfo info = null;
        info = readerService.splitPage(1, PAGE_SIZE);
        System.out.println(info);
        request.setAttribute("info", info);
        System.out.println("============================================");
        return "/jsp/readers.jsp";
    }

    @ResponseBody
    @RequestMapping("/readerAjaxSplit.do")
    public void ajaxsplit(ReaderVo readerVo, HttpSession session) {
        PageInfo info = null;
        info = readerService.splitPageVo(readerVo,PAGE_SIZE);
        if(info.getPageNum()==0){
            info.setPageNum(1);
            if(info.getPages()>1){
                info.setNextPage(2);
            }

        }
        System.out.println(info.getPageNum());
        System.out.println(info.getNextPage());
        session.setAttribute("info", info);
    }

    @RequestMapping(value = "/addReader.do", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addReader(String name,String sex,String profession,String phoneNumber,String email,String professionNumber,String address){
        Reader reader = new Reader();
        reader.setName(name);
        reader.setSex(sex);
        reader.setProfession(profession);
        reader.setPhoneNumber(phoneNumber);
        reader.setEmail(email);
        reader.setProfessionNumber(professionNumber);
        reader.setAddress(address);
        String msg = "";
        if("学生".equals(profession)){
            reader.setLeftNumber(6);
        }
        if("群众".equals(profession)){
            reader.setLeftNumber(3);
        }
        if("教师".equals(profession)){
            reader.setLeftNumber(6);
        }
        reader.setUnreturnedNumber(0);
        if(readerService.insert(reader)>0){
            msg="添加成功";
        }
        else {
            msg="添加失败";
        }
        return msg;
    }
}
