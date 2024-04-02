package com.kitzbuhel.services;

import com.kitzbuhel.responses.TodoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private WebClient webClient;

    public List<TodoResponse> getTodos(String email) {
        return webClient.method(HttpMethod.GET)
                .uri("/todos/getByEmail")
                .body(Mono.just(email), String.class)
                .retrieve()
                .bodyToFlux(TodoResponse.class)
                .collectList()
                .block();
    }
}
