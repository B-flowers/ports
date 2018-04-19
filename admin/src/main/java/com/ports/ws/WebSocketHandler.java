package com.ports.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;

/**
 * 实现Websocket建立连接、发送消息、断开连接等时候的处理类
 *
 */

public class WebSocketHandler extends TextWebSocketHandler {
    private final static Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);


    Map<String, WebSocketSession> users = ServerWsOperation.getUsers();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message){
        ServerWsOperation.getInstance().send(message.getPayload());
        logger.info("get message: "+message.getPayload());

    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String username = (String) session.getAttributes().get(WebSocketConfig.SESSION_WEBSOCKET);
        if (!users.containsKey(username)) users.put(username, session);
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String username = (String) session.getAttributes().get(WebSocketConfig.SESSION_WEBSOCKET);
        users.remove(username);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        String username = (String) session.getAttributes().get(WebSocketConfig.SESSION_WEBSOCKET);
        if (session.isOpen()) {
            session.close();
        }
        users.remove(username);
    }

}
