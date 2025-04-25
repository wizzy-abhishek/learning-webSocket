package com.webSock.chat.config;

import com.webSock.chat.dto.ChatMessage;
import com.webSock.chat.enums.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageTemplate ;

    @EventListener
    public void handleWebSocketDisconnectListener(
            SessionDisconnectEvent disconnectEvent){
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor
                .wrap(disconnectEvent.getMessage());
        String username = (String) stompHeaderAccessor.
                getSessionAttributes()
                .get("username");

        if (username != null){
            log.info("User disconnected {}" , username);
            var chatMessage = new ChatMessage("" , username , MessageType.LEAVE);
            messageTemplate.convertAndSend("/topic/public" , chatMessage);
        }

    }
}
