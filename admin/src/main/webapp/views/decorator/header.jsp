<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>header</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
  </head>
  
  <body>
    <header class="main-header">
					<!-- Logo -->
					<a href="index2.html" class="logo">
						<!-- mini logo for sidebar mini 50x50 pixels -->
						<span class="logo-mini"><b>A</b>LT</span>
						<!-- logo for regular state and mobile devices -->
						<span class="logo-lg"><b>Admin</b>LTE</span>
					</a>
					<!-- Header Navbar: style can be found in header.less -->
					<nav class="navbar navbar-static-top">
						<!-- Sidebar toggle button-->
						<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
							<span class="sr-only">Toggle navigation</span>
						</a>

						<div class="navbar-custom-menu">
							<ul class="nav navbar-nav">
								<!-- Messages: style can be found in dropdown.less-->
								<li class="dropdown messages-menu">
									<i class="fa fa-envelope-o"></i>
								</li>
								<!-- Control Sidebar Toggle Button -->
								<li>
									<a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
								</li>
							</ul>
						</div>
					</nav>
				</header>
  </body>
</html>
