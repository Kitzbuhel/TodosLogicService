package com.kitzbuhel.services;

import com.kitzbuhel.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserService {
    @Autowired
    private WebClient webClient;

    public Boolean status(String email) {
        return webClient.get()
                .uri("/api/user/status/" + email)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
