package com.kitzbuhel.TodosLogicService.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitzbuhel.TodosLogicService.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class UserService {
    private ObjectMapper objectMapper = new ObjectMapper();

    private WebClient webClient = WebClient.create("http://localhost:8080");

    public Boolean status(String email) throws JsonProcessingException {
        String response = webClient.get()
                .uri("/api/user/status/{email}", email)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Map<String, String> responseMap = objectMapper.readValue(response, Map.class);
        return responseMap.get("value").equals("true");
    }
}
