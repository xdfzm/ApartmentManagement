package com.rt.lister;

/**
 * @author shkstart
 * @create 2021-09-15 22:36
 */

import com.rt.pojo.BookType;
import com.rt.pojo.Reader;
import com.rt.pojo.ReaderType;
import com.rt.service.BookTypeService;
import com.rt.service.ReaderService;
import com.rt.service.ReaderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

//监听器 用于商品类型回显
@WebListener
public class TypeListener implements ServletContextListener {

    //对象被创建之后执行该方法
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //创建 productTypeService 对象
        //手工从 spring容器中获取 ProductTypeServiceImpl对象


        ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");
        BookTypeService bookTypeService = (BookTypeService)context.getBean("bookTypeServiceImpl");
        List<BookType> bookTypes = bookTypeService.selectAllBookType();



        ReaderTypeService readerTypeService = (ReaderTypeService)context.getBean("readerTypeServiceImpl");
        List<ReaderType> readerTypes = readerTypeService.selectAllReaderType();

        servletContextEvent.getServletContext().setAttribute("bookTypeList", bookTypes);
        servletContextEvent.getServletContext().setAttribute("readerTypeList", readerTypes);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
