package com.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @ClassDescription: websocket配置类
 * 该配置类用于创建ServerEndpoint注解，此注解用在类上，使得此类成为服务端websocket
 * @JdkVersion: 1.8
 * @Author: 李白
 * @Created: 2023/8/31 14:56
 */
@Configuration
public class WsServerConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return  new ServerEndpointExporter();
    }
}
