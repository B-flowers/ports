package com.ws.java.client;

import com.appliance.Application;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

public class WsClientStart{
    private final static Logger logger = LoggerFactory.getLogger(WsClientStart.class);

    private static String number = Application.getProperty("application.number", "666666");
    private static String host = Application.getProperty("application.host", "127.0.0.1");
    private static int port = Application.getProperty("application.port", 8001);
    private static String location="ws://"+host+":"+port;;

    public WsClientStart(){
    }
    public static void start() {
        WebSocketClient ws = null;
        WebSocketImpl.DEBUG = false;
location="ws://localhost:80/websocket";
        try {
            ws = new WsClient(new URI(location), new Draft_17());
            ws.connect();
            while (ws.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
                Thread.sleep(10);
                break;
            }
            logger.debug("server start ...");
            Thread.sleep(1000);
            register(ws);
            heart(ws);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ws.close();
        }
    }
    public static void register(WebSocketClient ws){
        ws.send("register_"+number);
    }
    public static void heart(WebSocketClient ws) throws InterruptedException{
        while (true) {
            ws.send("@heartbeat");
            Thread.sleep(1000 * 10);
        }
    }
}
