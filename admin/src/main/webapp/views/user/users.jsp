<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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

    <title>ports</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <style type="text/css">

    </style>
</head>

<body class="hold-transition skin-blue sidebar-mini">
<jsp:include page="../decorator/config.jsp"></jsp:include>
<div class="wrapper">
    <jsp:include page="../decorator/header.jsp"></jsp:include>
    <jsp:include page="../decorator/aside.jsp"></jsp:include>
    <!-- Content Header (Page header) -->
    <div class="content-wrapper" style="min-height: 916px;">
        <section class="content-header">
            <h1>
                用户列表
            </h1>
            <ol class="breadcrumb">
                <li><a href="javascript:void(0)"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">用户列表</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-md-12">
                    <div class="box box-primary">
                        <div class="box-header">
                            <i class="fa fa-edit"></i>

                            <h3 class="box-title">用户列表</h3>
                        </div>


                        <div>

                            <form class="form-inline">
                                <div class="form-group">
                                    <label class="sr-only" for="searchName">姓名：</label>
                                    <input type="text" class="form-control" id="searchName" placeholder="name">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="searchSex">性别</label>
                                    <input type="text" class="form-control" id="searchSex" placeholder="sex">
                                </div>
                                <div class="checkbox">
                                    <button type="button" onclick="select(1)" class="btn btn-default">查询</button>
                                </div>
                            </form>
                            <div>
                                <table class="table table-striped" id="show_tab">
                                    <tr>
                                        <th>编号</th>
                                        <th>姓名</th>
                                        <th>性别</th>
                                        <th>操作</th>
                                    </tr>
                                    <c:forEach var="user" items="${users}" varStatus="status">
                                        <tr>
                                            <td>
                                                <c:out value="${status.index+1}"/>
                                            </td>
                                            <td>
                                                <c:out value="${user.name}"/>
                                            </td>
                                            <td>
                                                <c:out value="${user.sex}"/>
                                            </td>
                                            <td><a href="javascript:void(0);" onclick="queryById('${user.id}')">修改</a>&nbsp;|&nbsp;
                                                <a href="javascript:void(0);" onclick="deleteSql('${user.id}')">删除</a></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                            <div style="width:100%; height:35px; background: silver; line-height: 35px; color:blue;">
                                <a href="javascript:gotoPage('begin');">首页</a>&nbsp;
                                <a href="javascript:gotoPage('last');">上一页</a>&nbsp;
                                <a href="javascript:gotoPage('next');">下一页</a>&nbsp;
                                <a href="javascript:gotoPage('end');">末尾</a>&nbsp;|&nbsp;
                                <input type="hidden" value="${page.gotoPage}" id="pageIndex">
                                <input type="text" style="width:40px;height:26px;" id="gotoPage" value="${page.gotoPage}"/>&nbsp;/&nbsp;
                                <span id="pageCount">${page.pageCount}</span>页
                                <a href="javascript:gotoPage('input');">跳转</a>&nbsp;|&nbsp;每页
                                <select id="pagenum" >
                                    <option <c:if test="${page.pageSizeNum== 10}">selected="true"</c:if>>10</option>
                                    <option <c:if test="${page.pageSizeNum== 20}">selected="true"</c:if>>20</option>
                                    <option <c:if test="${page.pageSizeNum== 50}">selected="true"</c:if>>50</option>
                                    <option <c:if test="${page.pageSizeNum== 100}">selected="true"</c:if>>100</option>
                                </select>条
                            </div>
                        </div>

                    </div>
                </div>
                <!-- /.col -->
            </div>
        </section>
    </div>
    <jsp:include page="../decorator/footer.jsp"></jsp:include>
    <div class="control-sidebar-bg"></div>
</div>
<script type="text/javascript">

    function error(data) {
        var result = data.responseText;
        if (result != null && result != '') { //后台异常时，并在后台捕获
            var url = getRootPath() + "/error.jsp"; //获取工程路径
            location.href = url;
            //$(document.body).html(result);
        } else { //后台异常，且没有被捕获
            var url = getRootPath() + "/error.jsp"; //获取工程路径
            location.href = url;
        }
    }

    //js获取项目根路径，如：http://localhost:8099/UniqueduHome
    function getRootPath() {
        //获取当前网址，如： http://localhost:8099/UniqueduHome/view/error/notAuthorize.jsp
        var curWwwPath = window.document.location.href;
        //获取主机地址之后的目录，如： UniqueduHome/view/error/notAuthorize.jsp
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        //获取主机地址，如： http://localhost:8099
        var localhostPaht = curWwwPath.substring(0, pos);
        //获取带"/"的项目名，如：/UniqueduHome
        var projectName = pathName.substring(0,
            pathName.substr(1).indexOf('/') + 1);
        return (localhostPaht + projectName);
    }

    function select(gotoPage) {
        //分页的方法:调用的时候传一个参数，要去的页数沟通gotoPage
        //对gotopage的最大和最小控制
        if (gotoPage < 1) {
            gotoPage = 1;
        }
        //每页的条数
        var pageNum = $("#pagenum  option:selected").text();
        var searchName = $("#searchName").val();
        var searchSex = $("#searchSex").val();
        var url="/users?sex="+searchSex+"&go="+gotoPage+"&page="+pageNum+"&name="+searchName;
        window.location.href=encodeURI(encodeURI(url));
    }


    //查询条件使用
    function gotoPage(condition) {
        var gotopage = $("#pageIndex").val();
        if (condition == "begin") {
            gotopage = 1;
        } else if (condition == "last") {
            gotopage = Number(gotopage) - Number(1);
        } else if (condition == "next") {
            gotopage = Number(gotopage) + Number(1);
        } else if (condition == "end") {
            gotopage = $("#pageCount").text();
        } else if (condition == "input") {
            gotopage = $("#gotoPage").val();
        }
        select(gotopage);
    }

    //下拉框改变的方法
    function pageNum() {
        var pageSelect = $("#pagenum");
        pageSelect.change(function () {
            select(1);
        });
    }

    function deleteSql(id) {
        //弹窗功能演示
        var name = prompt("删除", ""); //将输入的内容赋给变量 name ，
        //这里需要注意的是，prompt有两个参数，前面是提示的话，后面是当对话框出来后，在对话框里的默认值
        if (name) //如果返回的有内容
        {
            alert("欢迎您：" + name)
        }
        if (confirm('确定删除？')) {
            $.ajax({
                url: "user/"+id,
                type: 'delete',
                async: false,
                complete: function (request, dArra) {
                    if (request.responseText == 'true') {
                        select(1);
                        //$(obj).parent().parent().remove();
                    } else {
                        error(data); //失败时的处理方法
                    }
                }
            });
        } else {
            alert('取消');
        }

    }

    function queryById(id) {
        window.location.href = "/user/" + id;
    }

</script>
</body>

</html>