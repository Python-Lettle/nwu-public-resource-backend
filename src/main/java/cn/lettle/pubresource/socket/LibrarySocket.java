package cn.lettle.pubresource.socket;


import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ServerEndpoint("/socket/library/{userId}")
public class LibrarySocket {
    /**
     * 记录在线连接客户端数量
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);
    /**
     * 存放每个连接进来的客户端对应的websocketServer对象，用于后面群发消息
     */
    private static CopyOnWriteArrayList<LibrarySocket> wsServers = new CopyOnWriteArrayList<>();

    private static Map<String, String> uid_user = new HashMap<>();
}
