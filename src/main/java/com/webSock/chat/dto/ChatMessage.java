package com.webSock.chat.dto;

import com.webSock.chat.enums.MessageType;

public record ChatMessage (String content,
                           String sender,
                           MessageType messageType){
}
