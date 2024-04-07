package com.kitzbuhel.TodosLogicService.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitzbuhel.TodosLogicService.responses.TodoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class TodoService {
    private ObjectMapper objectMapper = new ObjectMapper();
    private WebClient webClient = WebClient.create("http://localhost:8081");

    public List<TodoResponse> getTodos(String email) throws JsonProcessingException {
        Map<String, String> body = Map.of("email", email);
        String response = webClient.method(HttpMethod.GET)
                .uri("/todos/getByEmail")
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Map<String, String> responseMap = objectMapper.readValue(response, Map.class);
        return objectMapper.readValue(responseMap.get("response"), List.class);
    }
}
