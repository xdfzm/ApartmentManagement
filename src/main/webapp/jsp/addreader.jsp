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
        <p>读者管理>新增读者</p>
    </div>

    <div id="table">
        <div id="myform">
            <table>
                <tr>
                    <td class="one">姓名</td>
                    <td><input type="text" name="name" class="two" id="name" required></td>
                </tr>
                <tr>
                    <td class="one">性别</td>
                    <td>
                        <select name="sex" id="sex" required>
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="one" >职业</td>
                    <td>
                        <select name="profession" id="profession" required>
                            <c:forEach items="${readerTypeList}" var="r">
                                <option value="${r.rTypeDetail}">${r.rTypeDetail}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="one">电话</td>
                    <td><input type="text" name="phoneNumber" class="two" id="phoneNumber" required></td>
                </tr>
                <tr>
                    <td class="one" >邮箱</td>
                    <td>
                        <input type="text" name="email" class="two" id="email" required>
                    </td>
                </tr>
                <tr>
                    <td class="one">职业号码</td>
                    <td><input type="number" name="professionNumber" class="two" id="professionNumber" required></td>
                </tr>
                <tr>
                    <td class="one">地址</td>
                    <td><input type="text" name="address" class="two" id="address" required></td>
                </tr>


                <tr>
                    <td>
                        <button id="btnsub"  class="btn btn-success" onclick="btnclick()">提交</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

<script>
<%--    提交填写的读者信息--%>
        function btnclick(){
            var name = $("#name").val();
            var sex = $("#sex").val();
            var profession = $("#profession").val();
            var phoneNumber = $("#phoneNumber").val();
            var email = $("#email").val();
            var professionNumber = $("#professionNumber").val();
            var address = $("#address").val();
            console.log("name"+name);
            console.log("sex"+sex);
            console.log("profession"+profession);
            console.log("phoneNumber"+phoneNumber);
            console.log(email);
            console.log(professionNumber);
            console.log(address);
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/addReader.do",
                data: {
                    "name":name,"sex":sex,"profession":profession,"phoneNumber":phoneNumber,"email":email,"professionNumber":professionNumber,"address":address
                },
                dataType: "text",
                success: function(data) {
                    alert(data);
                    $("#table").load("${pageContext.request.contextPath}/jsp/addreader.jsp #table");
                }

            });

        }

</script>
</body>

</html>