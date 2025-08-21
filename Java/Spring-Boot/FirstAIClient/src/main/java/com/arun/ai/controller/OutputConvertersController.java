package com.arun.ai.controller;

import com.arun.ai.model.Player;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/outputConverters")
public class OutputConvertersController {

    ChatClient chatClient;

    public OutputConvertersController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/getListOfPlayer")
    public ResponseEntity<Object> getListOfPlayer(@RequestParam String sport) {
        var responseConverter = new BeanOutputConverter<>(new ParameterizedTypeReference<List<Player>>() {});

        var template = new PromptTemplate("""
            Generate a list of Career achievements for the sportsperson {sport}.
            Include the Player as the key and achievements as the value of it {format}
        """);
        var prompt = template.create(Map.of(
                "sport", sport,
                "format", responseConverter.getFormat()
        ));

        return ResponseEntity.ok(responseConverter.convert(Objects.requireNonNull(chatClient.prompt(prompt).call().content())));
    }

    @GetMapping("/getPlayerAchievements")
    public List<String> getPlayerAchievements(String playerName) {
        return chatClient.prompt()
                .user(u -> u.text(
                        "List the major achievements of player " + playerName +
                                " as a bullet point list. Group titles with multiple years in a single bullet like: " +
                                "\"Champions League (2006, 2009, 2011)\""
                ))
                .call()
                .entity(new ListOutputConverter(new DefaultConversionService()));
    }

}
