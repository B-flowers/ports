<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
      + request.getServerName() + ":" + request.getServerPort()
      + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <base href="<%=basePath%>">

  <title></title>
  <style type="text/css">

  </style>
</head>

<body class="hold-transition skin-blue sidebar-mini">
<jsp:include page="/views/decorator/config.jsp"></jsp:include>
<div class="wrapper">
  <jsp:include page="/views/decorator/header.jsp"></jsp:include>
  <jsp:include page="/views/decorator/aside.jsp"></jsp:include>
  <!-- Content Header (Page header) -->
  <div class="content-wrapper" style="min-height: 916px;">

    <section class="content-header">
      <h1>用户</h1>
      <ol class="breadcrumb">
        <li><a href="javascript:void(0)"><i class="fa fa-dashboard"></i> Home</a></li>
        </li>
        <li class="active">用户</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <div class="row">
        <div class="col-md-12">
          <div class="box box-primary">
            <div class="box-header">
              <i class="fa fa-edit"></i>

              <h3 class="box-title">用户</h3>
            </div>

            <form modelAttribute="user" class="form-horizontal loginForm"
                  action="/user" method="post" id="from">
              <input type="hidden" id="id" name="id" value="${user.id}"/>
              <div class="form-group">
                <label for="name" class="col-sm-2 control-label">name</label>
                <div class="col-sm-8">
                  <input class="form-control" name="name" id="name" value="${user.name}"/>
                </div>
              </div>
              <div class="form-group">
                <label for="password" class="col-sm-2 control-label">password</label>
                <div class="col-sm-8">
                  <input type="text" class="form-control" id="password" name="password" value="${user.password}"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">sex</label>
                <div class="col-sm-10">
                  <input type="radio" name="sex"<c:if test="${user.sex eq 'boy'}"> checked="checked"</c:if> value="boy"/>boy&nbsp;
                  <input type="radio" name="sex"<c:if test="${user.sex eq 'girl'}"> checked="checked"</c:if> value="girl"/>girl</td>
                </div>
              </div>
              <div class="form-group">
                <label for="birthday" class="col-sm-2 control-label">birthday</label>
                <div class="col-sm-10">
                  <input type="text" path="birthday" id="birthday"/>
                </div>
              </div>
              <div class="form-group">
                <label for="message" class="col-sm-2 control-label">CLOB</label>
                <div class="col-sm-10">
                  <textarea rows="3" cols="30" path="message" id="message"></textarea>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label"></label>
                <div class="col-sm-10">
                  <input class="btn btn-primary" type="button" onclick="insert()" value="保存"/>
                  <button class="btn btn-primary" onclick="update()" type="button">修改</button>
                </div>
              </div>

            </form>
            <form class="form-horizontal" action="/user/insertPic" method="post" id="update"
                  enctype="multipart/form-data">

              <div class="form-group">
                <label class="col-sm-2 control-label">文件</label>
                <div class="col-sm-10">
                  <input type="file" name="photo" id="photo"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label"></label>
                <div class="col-sm-10">
                  <input class="btn btn-primary" type="submit" value="保存">
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </section>
  </div>
  <jsp:include page="/views/decorator/footer.jsp"></jsp:include>
  <div class="control-sidebar-bg"></div>
</div>

<script type="text/javascript">

    function insert() {
        var action = $("#from");
        action.submit();

    }

    $(document).ready(function () {
        //回车事件，添加
        $(document).keydown(function (event) {
            if (event.keyCode == 13) {
                insert();
            }
        });
    });

    function update() {
        var action = $("#from");
        action.attr("action","/user/${user.id}");
        action.submit();

    }
</script>
</body>
</html>
