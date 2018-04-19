package com.ws.java;

import java.io.Serializable;

public class JsonModel implements Serializable {
    private String user;
    private String serverUser;
    private String content;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getServerUser() {
        return serverUser;
    }

    public void setServerUser(String serverUser) {
        this.serverUser = serverUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString(){
        return "";
    }

}
