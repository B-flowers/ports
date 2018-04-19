package com.ws.java.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ws.java.JsonModel;

import java.io.IOException;

public class WsOperation {
    private static WsOperation operation = null;
    private ObjectMapper mapper = new ObjectMapper();


    public synchronized void operation(String message) {
        try {
            JsonModel model = new JsonModel();
            model.setServerUser("server");
            model.setUser("666666");
            model.setContent(message);
            WsPool.sendMessageToUser(model.getUser(), model.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static WsOperation getInstance() {
        if (operation == null) {
            return new WsOperation();
        }
        return operation;
    }

    public JsonModel jsonObject(String json) throws IOException {
        JsonModel model = mapper.readValue(json, JsonModel.class);
        return model;
    }

    public String toString(JsonModel model) throws IOException {
        String json = mapper.writeValueAsString(model);
        return json;
    }


}
