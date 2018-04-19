package com.httpServer;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 使用jdk自带sun httpserver组件构建Http服务器，
 * JDK自带的HttpServer是一个非常轻量级的Http服务端框架，但是它非常灵活，易于扩展，
 * @author Administrator
 *
 */
public class MyHttpHandlerDemo implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {

        String requestMethod = exchange.getRequestMethod();
        if (requestMethod.equalsIgnoreCase("GET")) {
            Headers responseHeaders = exchange.getResponseHeaders();
            responseHeaders.set("Content-Type", "text/plain");
            exchange.sendResponseHeaders(200, 0);

            OutputStream responseBody = exchange.getResponseBody();
            Headers headers = exchange.getRequestHeaders();
            Set<String> keySet = headers.keySet();
            Iterator<String> iter = keySet.iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                List values = headers.get(key);
                String s = key + " = " + values.toString() + "\n";
                responseBody.write(s.getBytes());
            }
            responseBody.close();
        }else if (requestMethod.equalsIgnoreCase("POST")) {
            Headers responseHeaders = exchange.getResponseHeaders();
            responseHeaders.set("Content-Type", "text/plain");
            exchange.sendResponseHeaders(200, 0);

            InputStream requestBody = exchange.getRequestBody();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(requestBody));
            StringBuffer result = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                line = new String(line.getBytes(), "utf-8");
                result.append(line);
            }
            System.out.println(result.toString());

            OutputStream responseBody = exchange.getResponseBody();
            Headers headers = exchange.getRequestHeaders();
            Set<String> keySet = headers.keySet();
            Iterator<String> iter = keySet.iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                List values = headers.get(key);
                String s = key + " = " + values.toString() + "\n";
                responseBody.write(s.getBytes());
            }
            responseBody.close();
        }
    }
}

