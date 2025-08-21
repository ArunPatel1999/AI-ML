package com.arun.ai.pgvector.based.rag;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

@Configuration
public class PGVectorLoader {

    @Value("classpath:/indian_constitution.pdf")
    Resource indianConstitutionPdf;
    VectorStore vectorStore;
    JdbcClient jdbcClient;

    public PGVectorLoader(VectorStore vectorStore, JdbcClient jdbcClient) {
        this.vectorStore = vectorStore;
        this.jdbcClient = jdbcClient;
    }

    @PostConstruct
    public void init() {
        var dbDataCount = getDataSize();
        if(dbDataCount == 0) {
            System.out.println("Start data loading inside pgvector");
            var pdfDocumentSplitter = PdfDocumentReaderConfig.builder().withPagesPerDocument(1).build();
            var pagePdf = new PagePdfDocumentReader(indianConstitutionPdf, pdfDocumentSplitter);
            var tokenTextSplitter = new TokenTextSplitter();
            var docs = tokenTextSplitter.apply(pagePdf.get());
            vectorStore.accept(docs);
            System.out.println("Loading data inside pgvector is completed");
        } else {
            System.out.println("Data already present inside table =>"+dbDataCount);
        }
    }

    private int getDataSize() {
        return jdbcClient.sql("select count(*) from vector_store").query(Integer.class).single();
    }

}
