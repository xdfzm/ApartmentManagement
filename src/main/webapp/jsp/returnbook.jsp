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
<script>
</script>
<div id="addAll">
    <div id="nav">
        <p>图书管理>图书归还</p>
    </div>

    <div id="table">
        <div id="myform">
            <table>
                <tr>
                    <td class="one">ISBN</td>
                    <td><input type="text" name="isbn" class="two" id="isbn"  onblur="isbnblur()"></td>
                </tr>
                <!--错误提示-->
                <tr class="three">
                    <td class="four"></td>
                    <td><span id="isbnerr" style="color: pink" ></span></td>
                </tr>
                <tr>
                    <td class="one">图书名字</td>
                    <td><input type="text" name="bookName" class="two" id="bookName" required disabled></td>
                </tr>
                <tr>
                    <td class="one">读者id</td>
                    <td><input type="number" name="id" class="two" id="id" required onblur="idblur()"></td>
                </tr>
                <tr>
                    <td class="one">姓名</td>
                    <td><input type="text" name="name" class="two" id="name" required disabled></td>
                </tr>
                <br>
                <tr>
                    <td>
                        <%--                        <input type="submit" value="提交" class="btn btn-success" id="btnsub">--%>
                        <button id="btnsub"  class="btn btn-success" onclick="btnsubclick()">确认归还</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

</body>

<script type="text/javascript">
    <%--    提交填写的图书信息--%>
    function btnsubclick(){
        var isbn        =$("#isbn").val();
        var bookName    =$("#bookName").val();
        var id      =$("#id").val();
        var name = $("#name").val();
        if(bookName==null||bookName==""||name==null||name==""){
            alert("请输入正确的isbn和正确的id");
        }
        else{
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/returnBook.do",
                data: {
                    "isbn":isbn,"bookName":bookName,"name":name,"id":id
                },
                dataType: "text",
                success: function(data) {
                    alert(data);
                    $("#table").load("${pageContext.request.contextPath}/jsp/returnbook.jsp #table");
                }

            });
        }

    }

    function isbnblur(){
        var isbn = $("#isbn").val();
        if(isbn!="" || isbn!=null){
            console.log("intoisbn");
            $.ajax({

                type: "POST",
                url: "${pageContext.request.contextPath}/getBookNameByIsbn.do",
                data: {
                    isbn:isbn
                },
                dataType: "text",
                success: function(data) {
                    $("#bookName").val(data);
                }

            });
        }
    }


    function idblur(){
        var id = $("#id").val();
        console.log(id);
        console.log(id=="");
        console.log(id==null);
        if(id!=""){
            console.log("intoid");
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/getNameByReaderId.do",
                data: {
                    "id":id
                },
                dataType: "text",
                success: function(data) {
                    $("#name").val(data);
                }

            });
        }
    }


</script>
</html>