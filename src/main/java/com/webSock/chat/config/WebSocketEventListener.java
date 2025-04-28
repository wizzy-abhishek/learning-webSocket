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

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate ;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            log.info("user disconnected: {}", username);
            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }

    /*

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent disconnectEvent){

        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor
                .wrap(disconnectEvent.getMessage());
        String username = (String) stompHeaderAccessor.
                getSessionAttributes()
                .get("username");

        if (username != null){
            log.info("user disconnected {}" , username);
            var chatMessage = new ChatMessage(username + " left" , username , MessageType.LEAVE);
            messagingTemplate.convertAndSend("/topic/public" , chatMessage);
        }

    }
     */
}
