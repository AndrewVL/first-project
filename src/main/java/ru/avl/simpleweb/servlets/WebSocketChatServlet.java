package ru.avl.simpleweb.servlets;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import ru.avl.simpleweb.chat.ChatService;
import ru.avl.simpleweb.chat.ChatWebSocket;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Andrey on 16.04.2017.
 */
@WebServlet(name="WebSocketChatServlet", urlPatterns = {"/chat"})
public class WebSocketChatServlet extends WebSocketServlet {
    private static final int LOGOUT_TIME = 10*60*1000;
    private final ChatService chatService;

    public WebSocketChatServlet() {
        this.chatService = new ChatService();
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator((req, resp) -> new ChatWebSocket(chatService));
    }
}
