package com.httpServer;

import com.common.DefaultThreadFactory;
import com.sun.net.httpserver.HttpServer;
import com.appliance.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class TrackerServer {
    private static Logger logger = LoggerFactory.getLogger("tracker");

    public static void startServer() throws IOException {
        String bindHost = Application.getProperty("tracker.host", "127.0.0.1");
        int bindPort = Application.getProperty("tracker.port", 8080);
        logger.debug("Start tracker server: " + bindHost + ":" + bindPort);
        InetSocketAddress address = new InetSocketAddress(InetAddress.getByName(bindHost), bindPort);
        HttpServer server = HttpServer.create(address, 10);//监听端口8080,能同时接 受10个请求
        server.createContext("/my", new MyHttpHandler());
        server.createContext("/my/demo", new MyHttpHandlerDemo());

        server.setExecutor(Executors.newFixedThreadPool(10, new DefaultThreadFactory("tracker")));
        server.start();
    }

    public static void stopServer() {

    }

    public static void main(String[] args) throws IOException {
        TrackerServer.startServer();
    }
}
