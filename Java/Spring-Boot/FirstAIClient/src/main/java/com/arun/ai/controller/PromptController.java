package com.arun.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/prompt")
public class PromptController {

    ChatClient chatClient;
    Resource resource;

    public PromptController(ChatClient.Builder builder, @Value("classpath:/prompts/CelebDetails.st") Resource resource) {
        this.chatClient = builder.build();
        this.resource = resource;
    }

    @GetMapping("/getCelebDetails")
    public ResponseEntity<Object> getCelebDetails(@RequestParam String name) {
        var template = new PromptTemplate(resource);
        var prompt = template.create(Map.of("name", name));
        return ResponseEntity.ok(chatClient.prompt(prompt).call().content());
    }

    @GetMapping("/getSystemAndUserPrompt")
    public ResponseEntity<Object> getSystemAndUserPrompt(@RequestParam String name) {
        var userPrompt = new UserMessage(String.format("""
            List of details of the Sport %s along with their Rules and Regulations.
            Show the details in the readable format
         """, name));

        var systemPrompt = new SystemMessage("""
            You are a smart Virtual Assistant.
            Your task is to give the details about the Sports.
            If Someone ask about something else and you do not know the answer, Just Say that you do not know the answer.
         """);

        var prompt = new Prompt(List.of(userPrompt, systemPrompt));
        return ResponseEntity.ok(chatClient.prompt(prompt).call().chatResponse().getResult().getOutput().getText());
    }

}
