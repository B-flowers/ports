package com.ws.java.client;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class WsClient extends WebSocketClient {
    private final static Logger logger = LoggerFactory.getLogger(WebSocketClient.class);

    public WsClient(URI uri, Draft draft) {
        super(uri, draft);
    }

    @Override
    public void onMessage(String message) {
        logger.debug(message);
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        logger.debug("server open ");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        logger.debug("server close");
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

}
