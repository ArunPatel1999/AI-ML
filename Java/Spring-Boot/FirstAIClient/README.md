# FirstAIClient

> **🤖 Spring AI with PGVector RAG Implementation**: Spring Boot application with AI chat client, vector database integration, and Retrieval-Augmented Generation for Indian Constitution queries.

## 🎯 Key AI Operations Implemented

**In this project, I performed the following operations:**

1) **AI Chat Client Configuration** - Spring AI ChatClient setup with builder pattern
2) **Vector Store Integration** - PGVector database integration for document embeddings
3) **RAG Implementation** - Retrieval-Augmented Generation for context-aware responses
4) **Document Similarity Search** - Vector-based document retrieval with configurable topK
5) **Prompt Template Processing** - Dynamic prompt generation with input and document context
6) **REST API Endpoints** - RESTful endpoints for AI-powered question answering
7) **Indian Constitution QA** - Specialized question-answering system for constitutional queries

## 🎯 What This Project Does

**AI-powered question-answering system that uses vector database for retrieving relevant Indian Constitution documents and generates accurate responses.**

### Core Functionality:
- 🤖 **AI Chat Client**: Spring AI ChatClient for natural language processing
- 📊 **Vector Database**: PGVector integration for document embeddings storage
- 🔍 **Similarity Search**: Vector-based document retrieval for relevant context
- 📝 **RAG Pipeline**: Retrieval-Augmented Generation for accurate responses
- 🏛️ **Constitution QA**: Specialized system for Indian Constitution queries
- 🌐 **REST API**: HTTP endpoints for AI-powered question answering

## 🛠️ Technology Stack

- **Spring AI**: AI integration framework for Spring Boot
- **PGVector**: PostgreSQL vector database extension
- **ChatClient**: AI chat client for natural language processing
- **VectorStore**: Vector database abstraction layer
- **PromptTemplate**: Dynamic prompt generation and processing
- **Spring Boot**: Application framework with AI auto-configuration

## 📚 Learning Objectives

### AI Integration Fundamentals
- **Spring AI Framework**: Understanding Spring AI components and configuration
- **Vector Databases**: PGVector setup and document embedding storage
- **RAG Architecture**: Retrieval-Augmented Generation implementation patterns
- **Prompt Engineering**: Dynamic prompt template creation and processing

### Vector Search Patterns
- **Similarity Search**: Vector-based document retrieval algorithms
- **Embedding Management**: Document embedding storage and retrieval
- **Context Injection**: Relevant document injection into AI prompts
- **Response Generation**: AI-powered response generation with context

---

## 📂 Core Components

<details>
<summary>🤖 PGVectorController</summary>

**REST controller for AI-powered question answering with vector database integration**

- **What it does**: Handles HTTP requests for Indian Constitution questions using RAG pipeline
- **Code implementation**: 
  - **@RestController**: Spring REST controller for HTTP endpoint handling
  - **ChatClient**: AI chat client for natural language processing
  - **VectorStore**: PGVector database integration for document retrieval
  - **PromptTemplate**: Dynamic prompt generation with context injection
- **AI processing features**:
  - **Question Processing**: Receives user questions via REST endpoint
  - **Document Retrieval**: Searches vector database for relevant constitution documents
  - **Context Injection**: Injects retrieved documents into AI prompt template
  - **Response Generation**: Uses ChatClient to generate contextual responses
- **RAG pipeline**:
  - **Similarity Search**: `vectorStore.similaritySearch()` with configurable topK=5
  - **Document Formatting**: `Document.getFormattedContent()` for context preparation
  - **Prompt Template**: Dynamic prompt with input question and document context
  - **AI Generation**: ChatClient processes prompt and generates response
- **REST endpoint**:
  - **Mapping**: `@GetMapping` on `/pgVector` endpoint
  - **Parameter**: `@RequestParam String text` for user questions
  - **Response**: AI-generated answer based on retrieved constitutional documents
  - **Processing Flow**: Question → Vector Search → Context Injection → AI Response

</details>

<details>
<summary>📊 Vector Database Integration</summary>

**PGVector database integration for document embeddings and similarity search**

- **What it does**: Stores and retrieves document embeddings for constitutional text passages
- **Code implementation**: 
  - **VectorStore**: Spring AI abstraction for vector database operations
  - **SearchRequest**: Configurable search parameters with query and topK settings
  - **Document**: Vector document representation with content and metadata
  - **Similarity Search**: Vector-based document retrieval algorithm
- **Vector operations**:
  - **Document Storage**: Constitutional text stored as vector embeddings
  - **Similarity Calculation**: Vector distance calculations for relevant document retrieval
  - **TopK Retrieval**: Configurable number of most relevant documents (topK=5)
  - **Content Formatting**: Document content preparation for AI prompt injection
- **Search configuration**:
  - **Query Processing**: User question converted to vector representation
  - **Similarity Threshold**: Vector distance-based relevance scoring
  - **Result Ranking**: Documents ranked by similarity to user query
  - **Content Aggregation**: Multiple documents joined for context injection
- **Database features**:
  - **PGVector Extension**: PostgreSQL vector database capabilities
  - **Embedding Storage**: High-dimensional vector storage and indexing
  - **Fast Retrieval**: Optimized vector similarity search operations
  - **Scalable Architecture**: Handles large document collections efficiently

</details>

<details>
<summary>📝 Prompt Template System</summary>

**Dynamic prompt generation with context injection for accurate AI responses**

- **What it does**: Creates structured prompts with user questions and retrieved document context
- **Code implementation**: 
  - **PromptTemplate**: Spring AI prompt template processing
  - **Template Variables**: Dynamic injection of input and documents variables
  - **Context Formatting**: Retrieved documents formatted for AI consumption
  - **Prompt Structure**: Structured template for consistent AI interactions
- **Template features**:
  - **Question Injection**: `{input}` variable for user question insertion
  - **Document Context**: `{documents}` variable for retrieved constitutional text
  - **Instruction Clarity**: Clear instructions for AI response generation
  - **Error Handling**: Guidance for uncertain or unknown answers
- **Prompt structure**:
  - **Task Definition**: Clear task description for Indian Constitution questions
  - **Context Provision**: Retrieved documents provide factual basis
  - **Response Guidelines**: Instructions for accurate and honest responses
  - **Fallback Behavior**: "Don't know" response for uncertain queries
- **Processing flow**:
  - **Template Creation**: PromptTemplate instantiation with structured format
  - **Variable Injection**: Map.of() for dynamic variable substitution
  - **Context Integration**: Retrieved documents integrated into prompt
  - **AI Processing**: ChatClient processes complete prompt for response generation

</details>