<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/addBook.css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.2.1.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

</head>
<body>

<div id="addAll">
    <div id="nav">
        <p>图书管理>录入图书</p>
    </div>

    <div id="table">
        <div id="myform">
            <table>
                <tr>
                    <td class="one">ISBN</td>
                    <td><input type="text" name="isbn" class="two" id="isbn" required onblur="iblur()"></td>
                </tr>
                <!--错误提示-->
                <tr class="three">
                    <td class="four"></td>
                    <td><span id="isbnerr" style="color: pink" ></span></td>
                </tr>
                <tr>
                    <td class="one">图书名字</td>
                    <td><input type="text" name="bookName" class="two" id="bookName" required></td>
                </tr>
                <tr>
                    <td class="one">作者</td>
                    <td><input type="text" name="author" class="two" id="author" required></td>
                </tr>

                <tr>
                    <td class="one" >类别</td>
                    <td>
                        <select name="type" id="type" required>
                            <c:forEach items="${bookTypeList}" var="b">
                                <option value="${b.type}">${b.typeDetail}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="one">图书价格</td>
                    <td><input type="number" name="price" class="two" id="price" step="0.01" required onblur="pblur()"></td>
                </tr>
                <!--错误提示-->
                <tr class="three">
                    <td class="four"></td>
                    <td><span id="priceerr" style="color: pink"></span></td>
                </tr>
                <tr>
                    <td class="one">出版社</td>
                    <td><input type="text" name="publisher" class="two" id="publisher" required ></td>
                </tr>


                <tr>
                    <td class="one">出版年份</td>
                    <td><input type="number" name="publishYear" class="two" id="publishYear" required onblur="pyblur()"></td>
                </tr>
                <!--错误提示-->
                <tr class="three">
                    <td class="four"></td>
                    <td><span id="yearerr" style="color: pink"></span></td>
                </tr>
                <tr>
                    <td class="one">总数量</td>
                    <td><input type="number" name="totalNumber" class="two" id="totalNumber" required onblur="tnblur()"></td>
                </tr>
                <!--错误提示-->
                <tr class="three">
                    <td class="four"></td>
                    <td><span id="numerr" style="color: pink"></span></td>
                </tr>
                <tr hidden="hidden">
                    <td class="one"></td>
                    <td><input type="number" name="lentNumber" class="two" value="0" id="lentNumber" required></td>
                </tr>

                <tr>
                    <td>
<%--                        <input type="submit" value="提交" class="btn btn-success" id="btnsub">--%>
                        <button id="btnsub"  class="btn btn-success" onclick="btnclick()">提交</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

</body>

<script>
    <%--    提交填写的图书信息--%>
    function btnclick(){
        console.log("hello");
        var isbn        =$("#isbn").val();
        var bookName    =$("#bookName").val();
        var author      =$("#author").val();
        var type        =$("#type").val();
        var price       =$("#price").val();
        var publisher       =$("#publisher").val();
        var publishYear = $("#publishYear").val();
        var totalNumber = $("#totalNumber").val();
        var lentNumber = $("#lentNumber").val();
        var isbnerr = $("#isbnerr").text();
        var priceerr = $("#priceerr").text();
        var yearerr = $("#yearerr").text();
        var numerr = $("#numerr").text();

        if(isbnerr!="" ||priceerr!="" || yearerr!="" || numerr!=""){
            alert("请修改数据再提交");
        }
        else{
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/addBook.do",
                data: {
                    "isbn":isbn,"bookName":bookName,"author":author,"type":type,"price":price,"publisher":publisher
                    ,"publishYear":publishYear,"totalNumber":totalNumber,"lentNumber":lentNumber
                },
                dataType: "text",
                success: function(data) {
                    alert(data);
                    $("#table").load("${pageContext.request.contextPath}/jsp/addbook.jsp #table");
                }

            });
        }
    }
    <%--    ajax通过是否有isbn码判断图书库中是否有该图书--%>
    function iblur(){
        var isbn = $("#isbn").val();
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/checkIsbn.do",
            data: {
                isbn:isbn
            },
            dataType: "text",
            success: function(data) {
                if(data=='exist') {
                    $("#isbnerr").text("该图书已存在");
                }
                if(data=='notexist'){
                    $("#isbnerr").text("");
                }

            }

        });
    }
    //    判断图书价格是否合法
    function pblur(){
        var price = $("#price").val();
        console.log("====price:"+price);
        console.log("price==null"+(price==null));
        console.log("price=="+(price==""));
        if(price!=""){
            if(price<=0){
                $("#priceerr").text("图书价格必须大于0");
            }
            else {
                $("#priceerr").text("");
            }
        }
        else {
            $("#priceerr").text("");
        }

    }
    //    判断图书出版日期是否合法
    function pyblur(){
        var publishYear = $("#publishYear").val();
        if(publishYear!=""){
            if(publishYear<=0 ||publishYear>2021){
                $("#yearerr").text("图书出版日期不合法");
            }
            else {
                $("#yearerr").text("");
            }
        }
        else {
            $("#yearerr").text("");
        }

    }
    //    判断图书数量是否合法
    function tnblur(){
        var totalNumber = $("#totalNumber").val();
        if(totalNumber!=""){
            if(totalNumber<=0){
                $("#numerr").text("图书数量必须大于0");
            }
            else {
                $("#numerr").text("");
            }
        }else{
            $("#numerr").text("");
        }

    }
</script>
</html>