package com.server.server;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassDescription: websocket服务端
 * @JdkVersion: 1.8
 * @Author: 李白
 * @Created: 2023/8/31 14:59
 */
@Slf4j
@Component
//@RestController
@ServerEndpoint("/websocket-server")
//@ServerEndpoint("/")
public class WsServer {

    private Session session;
    /**
     * 记录在线连接客户端数量
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);
    /**
     * 存放每个连接进来的客户端对应的websocketServer对象，用于后面群发消息
     */
    private static CopyOnWriteArrayList<WsServer> wsServers = new CopyOnWriteArrayList<>();

    /**
     * 服务端与客户端连接成功时执行
     * @param session 会话
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        //接入的客户端+1
        int count = onlineCount.incrementAndGet();
        //集合中存入客户端对象+1
        wsServers.add(this);
        log.info("与客户端连接成功，当前连接的客户端数量为：{}", count);
    }

    /**
     * 收到客户端的消息时执行
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session){
        log.info("收到来自客户端的消息，客户端地址：{}，消息内容：{}", session.getMessageHandlers(), message);
        //业务逻辑，对消息的处理
//        sendMessageToAll("群发消息的内容");
    }

    /**
     * 连接发生报错时执行
     * @param session 会话
     * @param throwable 报错
     */
    @OnError
    public void onError(Session session, @NonNull Throwable throwable){
        log.error("连接发生报错");
        throwable.printStackTrace();
    }

    /**
     * 连接断开时执行
     */
    @OnClose
    public void onClose(){
        //接入客户端连接数-1
        int count = onlineCount.decrementAndGet();
        //集合中的客户端对象-1
        wsServers.remove(this);
        log.info("服务端断开连接，当前连接的客户端数量为：{}", count);
    }


    /**
     * 向客户端推送消息
     * @param message 消息
     */
    public void sendMessage(String message){
        this.session.getAsyncRemote().sendText(message);
        log.info("推送消息给客户端:{}，消息内容为：{}", this.session.getMessageHandlers(), message);
    }

//    @PostMapping("/send2c")
//    public void sendMessage1(@RequestBody String message){
////        this.session.getAsyncRemote().sendText(message);
//        try {
//            this.session.getBasicRemote().sendText(message);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        log.info("推送消息给客户端，消息内容为：{}", message);
//    }


    /**
     * 群发消息
     * @param message 消息
     */
    public void sendMessageToAll(String message){
        CopyOnWriteArrayList<WsServer> ws = wsServers;
        for (WsServer wsServer : ws){
            wsServer.sendMessage(message);
        }
    }

//    @PostMapping("/send2AllC")
//    public void sendMessageToAll1(@RequestBody String message){
//        CopyOnWriteArrayList<WsServer> ws = wsServers;
//        for (WsServer wsServer : ws){
//            wsServer.sendMessage(message);
//        }
//    }


}
