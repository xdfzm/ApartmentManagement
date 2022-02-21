<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <%--如果增或者失败则提示用户  不会刷新当前页--%>
    <script type="text/javascript">
        if ("${msg}" != "") {
            alert("${msg}");
        }
    </script>
    <%--删除提示--%>
    <c:remove var="msg"></c:remove>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bright.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/addBook.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <title></title>
</head>
<body>
<div id="brall">
    <div id="nav">
        <p>图书管理>图书列表</p>
    </div>
    <div id="condition" style="text-align: center">
        <form id="myform">
            图书名称：<input name="bookName" id="bookName">&nbsp;&nbsp;&nbsp;
            图书类型：<select name="type" id="type">
            <option value="-1">请选择</option>
            <c:forEach items="${bookTypeList}" var="b">
                <option value="${b.type}">${b.typeDetail}</option>
            </c:forEach>
        </select>&nbsp;&nbsp;&nbsp;
            作者：<input name="author" id="author">
            <input type="button" value="查询" onclick="condition()">

        </form>
    </div>
    <br>
    <div id="table">
        <%--条件判断--%>
        <c:choose>
            <%--如果 集合有数据就展示--%>
            <c:when test="${info.list.size()!=0}">
                <!--显示分页后的商品-->
                <div id="middle">
                    <table class="table table-bordered table-striped">
                        <tr>
                            <th>图书名</th>
                            <th>ISBN</th>
                            <th>作者</th>
                            <th>价格</th>
                            <th>出版社</th>
                            <th>出版年份</th>
                            <th>剩余数量</th>
                            <th></th>
                        </tr>

                        <c:forEach items="${info.list}" var="b">
                            <tr>
                                <td>${b.bookName}</td>
                                <td>${b.isbn}</td>
                                <td>${b.author}</td>
                                <td>${b.price}</td>
                                <td>${b.publisher}</td>
                                <td>${b.publishYear}</td>
                                <td>${b.totalNumber-b.lentNumber}</td>
                                <td>
                                    <button type="button" class="btn btn-warning" id="mydel"
                                            onclick="del(${b.isbn},${info.pageNum})">删除
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>


                    <!--分页栏-->
                    <div id="bottom">
                        <div>
                            <nav aria-label="..." style="text-align:center;">
                                <ul class="pagination">
                                    <li>
                                        <a href="javascript:ajaxsplit(${info.prePage})" aria-label="Previous">

                                            <span aria-hidden="true">«</span></a>
                                    </li>
                                    <c:forEach begin="1" end="${info.pages}" var="i">
                                        <%--
                                        如果当前页 == 循环页,如果当前页等于循环页就让它背景色改变
                                        --%>
                                        <c:if test="${info.pageNum==i}">
                                            <li>
                                                <a href="javascript:ajaxsplit(${i})"
                                                   style="background-color: hotpink">${i}</a>
                                            </li>
                                        </c:if>

                                        <c:if test="${info.pageNum!=i}">
                                            <li>

                                                <a href="javascript:ajaxsplit(${i})">${i}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                    <li>
                                        <a href="javascript:ajaxsplit(${info.nextPage})" aria-label="Next">
                                            <span aria-hidden="true">»</span></a>
                                    </li>
                                    <li style=" margin-left:150px;color: #0e90d2;height: 35px; line-height: 35px;">总共&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">${info.pages}</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <%--如果当前第几页不等于0,那么是第几页就让他显示第几页--%>
                                        <c:if test="${info.pageNum!=0}">
                                            当前&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">${info.pageNum}</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:if>
                                            <%--如果当前第几页等于0,就让他显示 1--%>
                                        <c:if test="${info.pageNum==0}">
                                            当前&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">1</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:if>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </c:when>
            <%--如果 没有就显示:展示没有符合条件的图书--%>
            <c:otherwise>
                <div>
                    <h2 style="width:1200px; text-align: center;color: orangered;margin-top: 100px">暂时没有符合条件的图书！</h2>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>

<script type="text/javascript">
    //单个删除
    function del(isbn, page) {
        if (confirm("确定删除吗")) {
            //取出查询条件
            var bookName = $("#bookName").val();
            var type = $("#type").val();
            var author = $("#author").val();
            var isbn=""+isbn;
            console.log("====="+isbn);
            //alert(pid)
            //向服务器提交请求完成删除
            //window.location = "\${pageContext.request.contextPath}/prod/delete.action?pid=" + pid;
            $.ajax({
                url: "${pageContext.request.contextPath}/delete.do",
                data: {"isbn": isbn, "bookName": bookName, "type": type, "author": author,"page": page},
                type: "post",
                dataType: "text", //删完之后要将删除成功的这句话从服务器端扔回来在 success中提示,ajax执行结束之后直接弹窗提示
                success: function (msg) {
                    /*
                    * 在这里琢磨了1个多小时因为没有传入 msg Ajax对象               * */
                    alert(msg);

                    //重新加载 #table容器
                    $("#table").load("${pageContext.request.contextPath}/jsp/books.jsp #table")
                }
            })
        }
    }

    //查询条件 异步ajax 发送到 服务器
    function condition() {
        //取出查询条件
        var bookName = $("#bookName").val();
        var type = $("#type").val();
        var author = $("#author").val();
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/ajaxSplit.do",
            data: {"bookName": bookName, "type": type, "author": author},
            success: function () {
                //刷新数据
                $("#table").load("${pageContext.request.contextPath}/jsp/books.jsp #table");
            }
        })
    }


    <!--分页的AJAX实现有条件版-->
    function ajaxsplit(page) {
        //取出查询条件
        //取出查询条件
        var bookName = $("#bookName").val();
        var type = $("#type").val();
        var author = $("#author").val();

        //向服务发出 ajax请求,请示 page页中的所有数据,在当前页面上局部刷新
        //异步ajax分页请求
        $.ajax({
            url: "${pageContext.request.contextPath}/ajaxSplit.do",
            //将所有条件也带上去
            data: {"bookName": bookName, "type": type, "author": author,"page": page},
            type: "post",
            success: function () {
                $("#table").load("${pageContext.request.contextPath}/jsp/books.jsp #table");
            }
        })
    };
</script>

</html>