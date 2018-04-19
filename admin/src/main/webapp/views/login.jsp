<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

        <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
        <html>

        <head>
            <base href="<%=basePath%>">

            <title>ports</title>
            <meta http-equiv="pragma" content="no-cache">
            <meta http-equiv="cache-control" content="no-cache">
            <meta http-equiv="expires" content="0">
            <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
            <meta http-equiv="description" content="This is my page">
            <!--
<link rel="stylesheet" type="text/css" href="styles.css">
-->
			<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css">
            <script src="/resources/bootstrap/js/bootstrap.min.js"></script>
        </head>

        <body>
            <div class="container">
            <div class="loginDiv">
                <form class="form-horizontal loginForm" action="/login" method="post">
                    <div class="form-group">
                        <label for="user" class="col-sm-2 control-label">user</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="user" name="user">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Sign in</button>
                        </div>
                    </div>
                </form>
            </div>
            </div>
        </body>
        <style type="text/css">
            .loginDiv {
                border: solid 1px rgba(255, 30, 60, 0.8);
                margin:100px auto;
                padding:15px;
                border-radius: 15px;
                background-color:rgba(80, 30, 60, 0.8);
                height:360px;
                width:46%;
            }
            .loginForm{
            margin:100px 0px 0px 0px;;
            }
        </style>

        </html>