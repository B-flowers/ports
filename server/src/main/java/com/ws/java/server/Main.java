package com.ws.java.server;

import org.java_websocket.WebSocketImpl;

import javax.annotation.PostConstruct;

public class Main {
    public static void main(String[] args) {
        try {
            WebSocketImpl.DEBUG = false;
            int port = 8001;
            WsServer s = new WsServer(port);
            s.start();

            Thread.sleep(5000);

            //模拟给登录的用户发送消息
            for(;;){
                Thread.sleep(3000);
                WsOperation.getInstance().operation("I am message!");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
