<%@ page contentType="text/html;charset=UTF-8"  language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书馆</title>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
<%--    <link rel="stylesheet" href="static/css/login.css">--%>
    <script src="static/js/jquery-3.2.1.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
    <style>
        #login{
            height: 50%;
            width: 28%;
            margin-left: auto;
            margin-right: auto;
            margin-top: 5%;
            display: block;
            position: center;
        }

        .form-group {
            margin-bottom: 0;
        }
        * {
            padding:0;
            margin:0;
        }
    </style>
</head>



<body>
<script type="text/javascript">
    if ("${msg}" != "") {
        alert("${msg}");
    }
</script>
<%--删除提示--%>
<c:remove var="msg"></c:remove>
<%--<h2 style="text-align: center; color: black; font-family: '华文行楷'; font-size: 500%">图 书 馆</h2>--%>

<div class="panel panel-default" id="login">
    <div class="panel-heading" style="background-color: #0099cc">
        <h3 class="panel-title" style="text-align: center ;color: white" >图书馆</h3>
    </div>
    <div class="panel-body">
        <div class="form-group">
            <label for="id">账号</label>
            <input type="text" class="form-control" id="id" placeholder="请输入账号">
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" class="form-control" id="password" placeholder="请输入密码">
        </div>

        <p style="text-align: right;color: red;position: absolute" id="info" ></p><br/>
        <button id="loginButton"  class="btn btn-primary  btn-block" onclick="btnclick() " style="background-color: #0099CC">登陆
        </button>
    </div>
</div>

<script>
    function keyboard() {
        document.onkeydown = function() {
            let keycode = event.keyCode;
            if (keycode == 13) {
                btnclick();
            }
        }
    }
    keyboard();


        function btnclick(){
            var id =$("#id").val();
            var password =$("#password").val();
            if(id!="admin" || password!="admin"){
                $("#info").text("账号或者密码错误");
            }
            else{
                window.location.href="login.do";
            }
        }




</script>
</body>






</html>
