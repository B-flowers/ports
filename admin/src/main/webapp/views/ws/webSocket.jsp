<%@ page contentType="text/html; charset=utf-8" %>
<!doctype html>
<html>
<head>
    <title>ws</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
</head>
<body>

    <br/>
    接收者：<input id="toUser" value="admin"/>
    消息：<input id="message" value="admin"/>
    <button type="button" onclick="openClick();">打开</button>
    <button type="button" onclick="closeClick();">关闭</button>
    <button type="button" onclick="sendClick();">发送</button><br/>
    <a target="_blank" href="/ws/message" target="view_window">administrator</a>

    <div id="showMessage">

    </div>
    </div>
    <script>
        var socket;
        function openClick() {
            //var url = 'ws://' + window.location.host + '/citycom/socket/commodity';
            //var socket = new SockJS('socket/commodity');
            var url = 'ws://' + window.location.host + '/websocket';
            socket = new WebSocket(url);
            socket.onopen = onopen;
            socket.onmessage = onmessage;
            socket.onclose = onclose;
        }
        function closeClick() {
            socket.close();
        }
        function sendClick() {
            var message = document.getElementById("message").value;
            var msg =(Math.random() + "").slice(2,5)+": "+message;
            var toUser = document.getElementById("toUser").value;
            var obj = {
                "sourceUser":"${wsUser}",
                "toUser":toUser,
                "content":msg
            };
            var message = JSON.stringify(obj);
            console.log(message)
            socket.send(message);
        }

        var onopen = function() {
            console.log("open...");
        }
        var onmessage = function(e) {
            if (e.data === "") {
                return;
            }
            $("#showMessage").append( e.data + "<br/>")
        }
        var onclose = function() {
            console.log("close...");
        }


    </script>
</body>
</html>
