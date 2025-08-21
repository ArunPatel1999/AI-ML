package com.arun.ai.file.based.rag;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;


@Configuration
public class FileBasedVectorSearchConfig {

    @Value("classpath:/indian_constitution.pdf")
    Resource indianConstitutionPdf;

    @Bean
    SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel) {
        var simpleVectorStore = SimpleVectorStore.builder(embeddingModel).build();
        var vectoryFile = new File("/Users/apatel2/ARUN/Projects/learning/AI-ML/Java/Spring-Boot/FirstAIClient/src/main/resources/vector-data/indian_constitution.json");
        if(vectoryFile.exists()) {
            System.out.println("Vector File already Present.");
            simpleVectorStore.load(vectoryFile);
        } else {
            System.out.println("Vector File not Present.");
            var pdfDocumentSplitter = PdfDocumentReaderConfig.builder().withPagesPerDocument(1).build();
            var pagePdf = new PagePdfDocumentReader(indianConstitutionPdf, pdfDocumentSplitter);
            var tokenTextSplitter = new TokenTextSplitter();
            var docs = tokenTextSplitter.apply(pagePdf.get());
            simpleVectorStore.add(docs);
            simpleVectorStore.save(vectoryFile);
            System.out.println("vector File Save");
        }
        return simpleVectorStore;
    }

}
