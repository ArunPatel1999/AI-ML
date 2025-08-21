package com.arun.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic")
public class BasicController {

    ChatClient chatClient;

    public BasicController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping
    public ResponseEntity<Object> getResponse(@RequestParam String text) {
        return ResponseEntity.ok(chatClient.prompt(text).call().content());
    }

    @GetMapping("/getDetailResponse")
    public ResponseEntity<Object> getDetailResponse(@RequestParam String text) {
        return ResponseEntity.ok(chatClient.prompt(text).call().chatClientResponse());
    }

}
