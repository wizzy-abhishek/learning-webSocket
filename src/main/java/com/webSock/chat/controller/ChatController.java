package com.webSock.chat.controller;

import com.webSock.chat.dto.ChatMessage;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage
    ){

    }
}
