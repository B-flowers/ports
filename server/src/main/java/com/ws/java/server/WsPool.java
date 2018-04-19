package com.ws.java.server;

import org.java_websocket.WebSocket;

import java.util.*;

public class WsPool {
    public static final Map<String,WebSocket> wsServers = new HashMap<String,WebSocket>();

    /**
     * get WebSocket by user
     *
     * @return
     */
    public static WebSocket getWsByUser(String user) {
        return wsServers.get(user);
    }

    /**
     * 根据user获取WebSocket,这是一个list,此处取第一个
     * 因为有可能多个websocket对应一个user（但一般是只有一个，因为在close方法中，我们将失效的websocket连接去除了）
     *
     */
    public static String getUserByWs(WebSocket conn) {
        Set<String> keySet = wsServers.keySet();
        synchronized (keySet) {
            for (String user : keySet) {
                WebSocket ws = wsServers.get(user);
                if (ws.equals(conn)) {
                    return user;
                }
            }
        }
        return null;
    }

    /**
     * add WebSocket
     *
     */
    public static void addUser(String user, WebSocket conn) {
        if(wsServers.get(user)==null){
            wsServers.put(user, conn);
        }
    }

    /**
     * 获取所有连接池中的用户，因为set是不允许重复的，所以可以得到无重复的user数组
     *
     * @return
     */
    public static Collection<String> getOnlineUser() {
        List<String> setUsers = new ArrayList<String>();
        Collection<String> setUser = wsServers.keySet();
        for (String u : setUser) {
            setUsers.add(u);
        }
        return setUsers;
    }

    /**
     * 移除连接池中的连接
     *
     */
    public static boolean removeByUser(String user) {
        if (wsServers.containsKey(user)) {
            wsServers.remove(user); // 移除连接
            return true;
        } else {
            return false;
        }
    }
    public static boolean removeByWs(WebSocket conn) {
        if (wsServers.containsValue(conn)) {
            for (String user : wsServers.keySet()) {
                WebSocket ws = wsServers.get(user);
                if (ws.equals(conn)) {
                    wsServers.remove(user);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 向特定的用户发送数据
     *
     * @param message
     */
    public static void sendMessageToUser(String user, String message) {
        WebSocket conn = wsServers.get(user);
        if (null != conn) {
            conn.send(message);
        }
    }

    /**
     * 向所有的用户发送消息
     *
     * @param message
     */
    public static void sendMessageToAll(String message) {
        Collection<WebSocket> wss = wsServers.values();
        synchronized (wss) {
            for (WebSocket conn : wss) {
                conn.send(message);
            }
        }
    }

}
