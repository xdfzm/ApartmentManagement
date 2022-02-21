<%--
  Created by IntelliJ IDEA.
  User: 官瑞涛
  Date: 2021/10/30
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书管理系统</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/index.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.2.1.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<body>
    <!--整体部分-->
    <div id="all">
        <!--上部分-->
        <div id="top">
            <div id="top1">
                <span>图书管理系统</span>
            </div>
            <div id="top2"></div>
            <div id="top3">
                <span>欢迎您</span>
            </div>

        </div>
        <!--下部分-->
        <div id="bottom">
            <!--下部分左边-->
            <div id="bleft">

                <div id="lbottom">
                    <ul>

                        <!--                    target=myright 目标的页面打开会在指定的位置打开-->
                        <!--                    会在 iframe 指定的位置打开-->
                        <li class="two"><span class="glyphicon glyphicon-book" style="color: white;"></span>&nbsp;&nbsp;&nbsp;&nbsp;图书管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
                                class="glyphicon glyphicon-chevron-down" style="color: white;"></span></li>

                        <ul>
                            <a href="${pageContext.request.contextPath}/jsp/addbook.jsp" target="myright"><li>图书录入</li></a>
                            <a href="${pageContext.request.contextPath}/jsp/borrowbook.jsp" target="myright"><li>图书借出</li></a>
                            <a href="${pageContext.request.contextPath}/jsp/returnbook.jsp" target="myright"><li>图书归还</li></a>
                            <a href="${pageContext.request.contextPath}/split.do" target="myright"><li>图书查询</li></a>
                            <a href="${pageContext.request.contextPath}/detailSplit.do" target="myright"><li>借阅详情</li></a>
                        </ul>
                        <br>

                        <li class="one"><span class="glyphicon glyphicon-user" style="color: white;"></span>&nbsp;&nbsp;&nbsp;&nbsp;读者管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
                                class="glyphicon glyphicon-chevron-down" style="color: white;"></span></li>

                        <ul>
                            <a href="${pageContext.request.contextPath}/readerSplit.do" target="myright"><li>基本信息管理</li></a>
                        </ul>
                        <ul>
                            <a href="${pageContext.request.contextPath}/jsp/addreader.jsp" target="myright"><li>添加读者</li></a>
                        </ul>
                    </ul>
                </div>
            </div>
            <!--下部分右边-->
            <div id="bright">
                <iframe frameborder="0" scrolling="yes" name="myright" width="1235px" height="700px" ></iframe>
            </div>
        </div>
    </div>
</body>

</html>
