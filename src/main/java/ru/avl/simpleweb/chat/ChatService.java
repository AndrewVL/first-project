package ru.avl.simpleweb.chat;

import org.eclipse.jetty.util.ConcurrentHashSet;

import java.util.Set;

/**
 * Created by Andrey on 16.04.2017.
 */
public class ChatService {
    private Set<ChatWebSocket> webSockets;

    public ChatService() {
        this.webSockets = new ConcurrentHashSet<>();
    }

    public void sendMessage(String message) {
        for (ChatWebSocket webSocket : webSockets)
            webSocket.sendString(message);
    }

    public void add(ChatWebSocket webSocket) {
        webSockets.add(webSocket);
    }

    public void remove(ChatWebSocket webSocket) {
        webSockets.remove(webSocket);
    }
}
