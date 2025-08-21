package com.arun.ai.pgvector.based.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pgVector")
public class PGVectorController {

    ChatClient chatClient;
    VectorStore vectorStore;
    private final String PROMPT = """
        You task is to answer the questions about Indian constituation. Usa the
        section to provide accurate answer. If unsure or if the answer isn't
        simply state that you don't know the answer.
        
        QUESTION:
        {input}
        
        DOCUMENTS:    
        {documents}
        
    """;

    public PGVectorController(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder.build();
        this.vectorStore = vectorStore;
    }

    @GetMapping
    public String getData(@RequestParam String text) {
        PromptTemplate template = new PromptTemplate(PROMPT);
        var a = getFindSimilarData(text);
        System.out.println(a);
        return chatClient
                .prompt(template.create(Map.of(
                        "input", text,
                        "documents", a
                )))
                .call().content();
    }

    private String getFindSimilarData(String query) {
       return vectorStore
               .similaritySearch(SearchRequest.builder().query(query).topK(5).build())
               .stream().map(Document::getFormattedContent)
               .collect(Collectors.joining());
    }

}
