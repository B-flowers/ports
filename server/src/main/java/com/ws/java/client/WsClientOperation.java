package com.ws.java.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ws.java.JsonModel;
import com.ws.java.UdpClient;

import java.io.IOException;

public class WsClientOperation implements Runnable {
    private ObjectMapper mapper;
    private WsClient client;
    private String json;

    public WsClientOperation(WsClient client, String json) {
        this.client = client;
        this.mapper = new ObjectMapper();
        this.json = json;
    }

    public void run() {
        try {
            JsonModel model = jsonObject(json);
            //String result = GatherHelper.executeCommand(model.getContent());
            String result = UdpClient.start(model.getContent());
            model.setContent(result);
            client.send(toString(model));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
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
