package com.server.controller;

import com.server.server.WsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassDescription: 服务端请求类
 * @JdkVersion: 1.8
 * @Author: 李白
 * @Created: 2023/8/31 16:51
 */
@RestController
public class WsServerController {
    @Autowired
    WsServer wsServer;

    /**
     * 服务端发消息给客户端
     * @param message 消息
     */
    @PostMapping("/send2client")
    public void send2Client(@RequestBody String message){
//        wsServer.sendMessageToAll("this is a test for server to client");
        wsServer.sendMessageToAll(message);
    }


}
