<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

  </head>
  
  <body>
    <!-- Left side column. contains the logo and sidebar -->
	<aside class="main-sidebar">
		<!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar">
			<!-- sidebar menu: : style can be found in sidebar.less -->
			<ul class="sidebar-menu">

		<li class="active treeview"><a href="#"> <i
				class="fa fa-dashboard"></i> <span>权限</span> <span
				class="pull-right-container"> <i
					class="fa fa-angle-left pull-right"></i> </span> </a>
			<ul class="treeview-menu">
				<li class="active"><a href="/users"><i
						class="fa fa-circle-o"></i> 用户管理</a>
				</li>
				<li><a href="/user/add"><i class="fa fa-circle-o"></i>add</a></li>
			</ul>
		</li>


				
			</ul>
		</section>
		<!-- /.sidebar -->
	</aside>
  </body>
</html>
