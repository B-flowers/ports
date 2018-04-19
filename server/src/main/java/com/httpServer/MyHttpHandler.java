package com.httpServer;

import com.common.ByteStreams;
import com.common.Charsets;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class MyHttpHandler implements HttpHandler {
    private static Logger logger = LoggerFactory.getLogger("MyHttpHandler");
    private boolean followRedirects = true;
    public static final int MAX_SIZE = 1024 * 1024;

    public void handle(HttpExchange exchange) throws IOException {
        try {
            String requestMethod = exchange.getRequestMethod();
            URL url = getOriginURL(exchange);
            proxy(url, exchange);
        } catch (Throwable e) {
            byte[] error = "Invalid Request".getBytes();
            exchange.sendResponseHeaders(500, error.length);
            ByteStreams.copy(error, exchange.getResponseBody());
        } finally {
            exchange.close();
        }
    }

    protected void proxy(URL url, HttpExchange exchange) throws IOException {
        HttpURLConnection conn = null;
        try {
            conn = openConnection(url, exchange);
            int statusCode = conn.getResponseCode();
            String contentEncoding = conn.getContentEncoding();
            if (statusCode >= 300) {
                byte[] error = toBytes(conn.getResponseMessage());
                Headers headers = exchange.getResponseHeaders();
                addHeaders(headers, conn.getHeaderFields());
                exchange.sendResponseHeaders(statusCode, error.length);
                ByteStreams.copy(error, exchange.getResponseBody());
            } else if (statusCode == HttpURLConnection.HTTP_OK || statusCode == HttpURLConnection.HTTP_PARTIAL) {
                byte[] data = null;
                if (contentEncoding != null && contentEncoding.equalsIgnoreCase("gzip")) {
                    data = ByteStreams.toBytes(new GZIPInputStream(conn.getInputStream()));
                } else {
                    data = ByteStreams.toBytes(conn.getInputStream());
                }
                if (data.length == MAX_SIZE) {
                    return;
                }
                String response = new String(data, Charsets.UTF_8);
                data = response.getBytes(Charsets.UTF_8);
                Headers headers = exchange.getResponseHeaders();
                addHeaders(headers, conn.getHeaderFields());
                exchange.sendResponseHeaders(statusCode, data.length);
                ByteStreams.copy(data, exchange.getResponseBody());
            }
        } catch (Exception e) {
            logger.debug(e.getMessage() + ", proxy URL:" + url.toString());
            String errMessage = e.getMessage().toString();
            if (errMessage.contains("Connection timed out") || errMessage.contains("Unexpected end of file from server")) {
                byte[] error = "Service Temporarily Unavailable".getBytes();
                Headers headers = exchange.getResponseHeaders();
                headers.set("Retry-After", "30");
//              addHeaders(headers, conn.getHeaderFields());
                exchange.sendResponseHeaders(503, error.length);
                ByteStreams.copy(error, exchange.getResponseBody());
            } else if (errMessage.contains("Connection refused") || errMessage.contains("Connection reset")) {
                byte[] error = "Service Temporarily Unavailable".getBytes();
                Headers headers = exchange.getResponseHeaders();
                headers.set("Retry-After", "60");
//                addHeaders(headers, conn.getHeaderFields());
                exchange.sendResponseHeaders(504, error.length);
                ByteStreams.copy(error, exchange.getResponseBody());
            }

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    protected void addHeaders(Headers headers, Map<String, List<String>> values) {
        for (String header : values.keySet()) {
            if (header == null) {
                continue;
            }
            if ("Transfer-Encoding".equalsIgnoreCase(header) || "Content-Length".equalsIgnoreCase(header) || "Content-Encoding".equalsIgnoreCase(header) || "Vary".equalsIgnoreCase(header)) {
                continue;
            }
            headers.put(header, values.get(header));
        }
    }

    protected URL getOriginURL(HttpExchange exchange) throws IOException {
        URI uri = exchange.getRequestURI();
        String path = uri.getPath();
        String host = exchange.getLocalAddress().getHostName();
        long port = exchange.getLocalAddress().getPort();
        String query = uri.getRawQuery();
        String url = "http://" + host + ":" + port + path + "/demo";
        if (query != null && query.length() > 0) {
            url += "?" + query;
        }
        return new URL(url);
    }

    private static byte[] toBytes(String message) {
        if (message == null) {
            return new byte[0];
        }
        return message.getBytes();
    }

    protected HttpURLConnection openConnection(URL url, HttpExchange exchange) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(30 * 1000);
        conn.setReadTimeout(60 * 1000);
        conn.setUseCaches(false);
        conn.setRequestMethod(exchange.getRequestMethod());
        Headers headers = exchange.getRequestHeaders();
        if (headers != null) {
            for (String header : headers.keySet()) {
                List<String> values = headers.get(header);
                for (int i = 0; i < values.size(); i++) {
                    String value = values.get(i);
                    if (i == 0) {
                        conn.setRequestProperty(header, value);
                    } else {
                        conn.addRequestProperty(header, value);
                    }
                }
            }
        }
        if ("POST".equals(exchange.getRequestMethod())) {
            conn.setDoInput(true);
        }
        conn.setInstanceFollowRedirects(followRedirects);
        conn.connect();
        if ("POST".equals(exchange.getRequestMethod())) {
            ByteStreams.copy(exchange.getRequestBody(), conn.getOutputStream());
        }
        return conn;
    }
}
