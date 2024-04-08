package com.kitzbuhel.TodosLogicService.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitzbuhel.TodosLogicService.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Service
public class UserService {
    private ObjectMapper objectMapper = new ObjectMapper();

    private WebClient webClient = WebClient.create("http://localhost:8080");

    public Boolean status(String email) throws JsonProcessingException {
        try {
            WebClient.ResponseSpec response = webClient.get()
                    .uri("/api/user/status/{email}", email)
                    .retrieve().onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.empty());

            Map<String, String> responseMap = objectMapper.readValue(Objects.requireNonNull(response.bodyToMono(String.class).block()), Map.class);
            return responseMap.get("value").equals("true");
        } catch (Exception e) {
            return false;
        }
    }
}
