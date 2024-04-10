package com.kitzbuhel.TodosLogicService.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitzbuhel.TodosLogicService.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class UserService {
    @Value("${AUTHSERVICE_BASE_URL}")
    private String userServiceBaseUrl;
    private ObjectMapper objectMapper = new ObjectMapper();
    private WebClient webClient = WebClient.create(userServiceBaseUrl);
    private Logger logger = Logger.getLogger(UserService.class.getName());

    public UserResponse status(String email) throws JsonProcessingException {
        logger.info(userServiceBaseUrl);
        try {
            WebClient.ResponseSpec response = webClient.get()
                    .uri("/api/user/status/{email}", email)
                    .retrieve().onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new Exception(clientResponse.statusCode().toString())));

            Map<String, String> responseMap = objectMapper.readValue(Objects.requireNonNull(response.bodyToMono(String.class).block()), Map.class);
            return new UserResponse(responseMap.get("value").equals("true"), false, null);
        } catch (Exception e) {
            return new UserResponse(false, true, e.getMessage());
        }
    }
}
