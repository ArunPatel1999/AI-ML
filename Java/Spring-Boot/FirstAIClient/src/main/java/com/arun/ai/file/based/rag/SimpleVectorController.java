package com.arun.ai.file.based.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simpleVector")
public class SimpleVectorController {

    ChatClient chatClient;
    VectorStore vectorStore;

    public SimpleVectorController(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder.defaultAdvisors(new QuestionAnswerAdvisor(vectorStore)).build();
        this.vectorStore = vectorStore;
    }

    @GetMapping
    public String getData(@RequestParam String text) {
        return chatClient.prompt().user(text).call().content();
    }

}
