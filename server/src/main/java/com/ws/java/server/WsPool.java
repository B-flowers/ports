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
     * ����user��ȡWebSocket,����һ��list,�˴�ȡ��һ��
     * ��Ϊ�п��ܶ��websocket��Ӧһ��user����һ����ֻ��һ������Ϊ��close�����У����ǽ�ʧЧ��websocket����ȥ���ˣ�
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
     * ��ȡ�������ӳ��е��û�����Ϊset�ǲ������ظ��ģ����Կ��Եõ����ظ���user����
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
     * �Ƴ����ӳ��е�����
     *
     */
    public static boolean removeByUser(String user) {
        if (wsServers.containsKey(user)) {
            wsServers.remove(user); // �Ƴ�����
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
     * ���ض����û���������
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
     * �����е��û�������Ϣ
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
