package com.ijobhawk.jobhawk.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LlmConfig {

    @Bean
    ChatModel chatModel(
            @Value("${jobhawk.llm.api-key}") String apiKey,
            @Value("${jobhawk.llm.base-url}") String baseUrl,
            @Value("${jobhawk.llm.model-name}") String modelName,
            @Value("${jobhawk.llm.temperature}") double temperature) {

        return OpenAiChatModel.builder()
                .apiKey(apiKey)
                .baseUrl(baseUrl)
                .modelName(modelName)
                .temperature(temperature)
                .logRequests(true)
                .logResponses(true)
                .build();
    }
}
