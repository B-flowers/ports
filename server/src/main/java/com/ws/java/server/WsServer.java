package com.ws.java.server;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class WsServer extends WebSocketServer {
    private String user;

    public WsServer(int port) {
        super(new InetSocketAddress(port));
    }

    public WsServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket start ...");

    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println(conn.getLocalSocketAddress().getAddress().getHostAddress() + " open ...");

    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        userLeave(conn);
        System.out.println(conn.getLocalSocketAddress().getAddress().getHostAddress() + " close ...");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        //注册信息，心跳信息过滤
        if (null != message && message.startsWith("register_")) {
            String serverName = message.replaceFirst("register_", "");
            userJoin(conn, serverName);
            String serverIp = conn.getLocalSocketAddress().getAddress().getHostAddress();
            System.out.println("server login :" + serverIp + "/" + serverName);
            return;
        } else if (null != message && message.startsWith("offline")) {
            userLeave(conn);
            return;
        } else if (null != message && message.startsWith("@heartbeat")) {
            return;
        }
        System.out.println("message: "+message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.out.println("on error ...");
        ex.printStackTrace();
    }

    private void userLeave(WebSocket conn) {
        WsPool.removeByWs(conn);
    }

    private void userJoin(WebSocket conn, String user) {
        WsPool.addUser(user, conn);
    }

}
