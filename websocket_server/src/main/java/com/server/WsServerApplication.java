package com.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @ClassDescription: websocket服务端
 * EnableWebSocket注解用于开启websocket服务
 * @JdkVersion: 1.8
 * @Author: 李白
 * @Created: 2023/8/31 14:53
 */
@EnableWebSocket
@SpringBootApplication
public class WsServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WsServerApplication.class, args);
    }
}
