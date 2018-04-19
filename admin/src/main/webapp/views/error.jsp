<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>错误页面</title>
</head>

<body>
	<script type="text/javascript">
		window.onload = function() {
			setTimeout('window.location.href="index.jsp";', 3000);
			//setTimeout('parent.location.reload();', 3000);//3秒后重新加载整个页面
			//但如果要在原页面的基础上传递参数，则可以使用下面的方法：
			// 2.top.document.location.href='xxx.aspx?id=xx'。

		}
	</script>
	<div
		style="width:98%; height: 600px; text-align: center; padding:100px atuo 0px auto;">
		<h3 style="margin-top: 100px;">错误！3秒后跳转到首页。。。</h3>
	</div>

</body>
</html>
