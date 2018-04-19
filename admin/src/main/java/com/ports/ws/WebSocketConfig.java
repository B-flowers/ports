package com.ports.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author ggaoly
 * 创建一个WebSocket配置类（这里也可以用配置文件来实现其实），实现接口来配置Websocket请求的路径和拦截器。
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    public final static String SESSION_WEBSOCKET = "SESSION_WEBSOCKET";

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        String websocket_url = "/websocket";
        registry.addHandler(webSocketHandler(), websocket_url).
                addInterceptors(new WebSocketHandshakeInterceptor());

        //2.注册SockJS，提供SockJS支持(主要是兼容ie8)
        String sockjs_url = "/sockjs";
        registry.addHandler(webSocketHandler(), sockjs_url).
                addInterceptors(new WebSocketHandshakeInterceptor()).
                withSockJS();
    }

    @Bean
    public TextWebSocketHandler webSocketHandler() {
        return new WebSocketHandler();
    }
}

