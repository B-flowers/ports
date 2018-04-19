package com.ports.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ServerWsOperation {
    private static ServerWsOperation operation;
    private static final Map<String, WebSocketSession> users = new HashMap<String, WebSocketSession>();
    private ObjectMapper mapper = new ObjectMapper();

    public static Map<String, WebSocketSession> getUsers() {
        return users;
    }

    public synchronized void send(String json){
        try {
            WsModel model = jsonObject(json);
            TextMessage message = new TextMessage(model.getContent());
            sendMessageToUser(model.getToUser(),message);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public synchronized static ServerWsOperation getInstance() {
            if (operation == null) {
                return new ServerWsOperation();
            }
        return operation;
    }


    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users.values()) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessageToUser(String userName, TextMessage message) {
        WebSocketSession user = users.get(userName);
        try {
            if (user != null && user.isOpen()) {
                user.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WsModel jsonObject(String json)throws IOException {
        WsModel model = mapper.readValue(json, WsModel.class);
        return model;
    }

    public String toString(WsModel model)throws IOException{
        String json = mapper.writeValueAsString(model);
        return json;
    }
}
