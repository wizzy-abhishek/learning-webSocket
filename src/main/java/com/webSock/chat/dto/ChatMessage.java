package com.webSock.chat.dto;

import com.webSock.chat.enums.MessageType;
import lombok.*;
import org.springframework.messaging.handler.annotation.SendTo;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatMessage {

   private String content;
   private String sender;
   private MessageType type;
}
