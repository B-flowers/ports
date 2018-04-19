<%@ page contentType="text/html; charset=utf-8" %>
<!doctype html>
<html>
<head>
    <title>ws</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/ws/login" method="post">
    用户名:
    <input name="userTest" type="text" value="admin"><br>
    密码:
    <input name="password" type="text" value="admin"><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
