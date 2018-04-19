<%@ page contentType="text/html; charset=utf-8" %>
<!doctype html>
<html>
<head>
    <title>ws</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
</head>
<body>
<input type="text" placeholder="请输入Ws用户" id="webSocketId" value="${wsUser}"/><br/>
<textarea rows="5" placeholder="请输入发送的内容" id="message" ></textarea><br/>
    <button id="sendMsg">发送</button>
    <button id="resetMessage">清空发送内容</button>
    <button id="resetid">重置id</button>
</div>

</body>
<script>

    $("#sendMsg").click(function () {
        var webSocketId = $("#webSocketId").val();
        var message = $("#message").val();
        var url = "${pageContext.request.contextPath}/ws/message";
        $.post(url, {"message": message}, function (data) {
            console.log(data)

        })
    })
    $("#resetid").click(function () {
        $("#webSocketId").val("");
    })
    $("#resetMessage").click(function () {
        $("#message").val("");
    })
</script>

</html>
