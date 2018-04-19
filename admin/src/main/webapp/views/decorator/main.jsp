<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

		
<html>

<head>
	<base href="<%=basePath%>">

	<title>Dashboard</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>

<body class="hold-transition skin-blue sidebar-mini">
	<jsp:include page="./config.jsp"></jsp:include> 
	<div class="wrapper">
		<jsp:include page="./header.jsp"></jsp:include>  
       	<jsp:include page="./aside.jsp"></jsp:include>
       	<sitemesh:write property='body' />
       	<jsp:include page="./footer.jsp"></jsp:include> 
		<div class="control-sidebar-bg"></div>
	</div>

 
</body>

</html>