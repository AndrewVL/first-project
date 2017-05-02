package ru.avl.simpleweb.chat;

import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

/**
 * Created by Andrey on 16.04.2017.
 */
@WebSocket
public class ChatWebSocket {
    private static final Logger logger = Log.getLogger(ChatWebSocket.class);

    private ChatService chatService;
    private Session session;

    public ChatWebSocket(ChatService chatService) {
        this.chatService = chatService;
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        this.session = session;
        chatService.add(this);
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        chatService.sendMessage(data);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        chatService.remove(this);
    }

    public void sendString(String message) {
        try {
            session.getRemote().sendString(message);
        } catch (IOException e) {
            logger.warn(e);
        }
    }
}
