package com.kitzbuhel.TodosLogicService.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitzbuhel.TodosLogicService.responses.TodoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class TodoService {
    @Value("${IOSERVICE_BASE_URL}")
    private String ioServiceBaseUrl;
    private ObjectMapper objectMapper = new ObjectMapper();
    private WebClient webClient = null;
    private Logger logger = Logger.getLogger(TodoService.class.getName());
    public ResponseEntity<String> getTodos(String email) throws JsonProcessingException {
        if (webClient == null) {
            webClient = WebClient.create(ioServiceBaseUrl);
        }
        try {
            Map<String, String> body = Map.of("email", email);
            Map<String, String> responseMap;
            WebClient.ResponseSpec response = webClient.method(HttpMethod.GET)
                    .uri("/todos/getByEmail")
                    .body(BodyInserters.fromValue(body))
                    .retrieve().onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new Exception(clientResponse.statusCode().toString())));

            responseMap = objectMapper.readValue(Objects.requireNonNull(response.bodyToMono(String.class).block()), Map.class);
            return new ResponseEntity<>(objectMapper.writeValueAsString(responseMap), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(Map.of("response", "Error " + e.getMessage() + " from Database")), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> getCompletedTodos(String email) throws JsonProcessingException {
        if (webClient == null) {
            webClient = WebClient.create(ioServiceBaseUrl);
        }
        try {
            Map<String, String> body = Map.of("email", email);
            Map<String, String> responseMap;
            WebClient.ResponseSpec response = webClient.method(HttpMethod.GET)
                    .uri("/todos/getCompletedByEmail")
                    .body(BodyInserters.fromValue(body))
                    .retrieve().onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new Exception(clientResponse.statusCode().toString())));

            responseMap = objectMapper.readValue(Objects.requireNonNull(response.bodyToMono(String.class).block()), Map.class);
            return new ResponseEntity<>(objectMapper.writeValueAsString(responseMap), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(Map.of("response", "Error " + e.getMessage() + " from Database")), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> getNotCompletedTodos(String email) throws JsonProcessingException {
        if (webClient == null) {
            webClient = WebClient.create(ioServiceBaseUrl);
        }
        try {
            Map<String, String> body = Map.of("email", email);
            Map<String, String> responseMap;
            WebClient.ResponseSpec response = webClient.method(HttpMethod.GET)
                    .uri("/todos/getNotCompletedByEmail")
                    .body(BodyInserters.fromValue(body))
                    .retrieve().onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new Exception(clientResponse.statusCode().toString())));

            responseMap = objectMapper.readValue(Objects.requireNonNull(response.bodyToMono(String.class).block()), Map.class);
            return new ResponseEntity<>(objectMapper.writeValueAsString(responseMap), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(Map.of("response", "Error " + e.getMessage() + " from Database")), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addTodo(String email, String description) throws JsonProcessingException {
        if (webClient == null) {
            webClient = WebClient.create(ioServiceBaseUrl);
        }
        try {
            Map<String, String> body = Map.of("email", email, "description", description);
            Map<String, String> responseMap;
            WebClient.ResponseSpec response = webClient.method(HttpMethod.POST)
                    .uri("/todos/addTodo")
                    .body(BodyInserters.fromValue(body))
                    .retrieve().onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new Exception(clientResponse.statusCode().toString())));

            responseMap = objectMapper.readValue(Objects.requireNonNull(response.bodyToMono(String.class).block()), Map.class);
            return new ResponseEntity<>(objectMapper.writeValueAsString(responseMap), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(Map.of("response", "Error " + e.getMessage() + " from Database")), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteTodo(Long id, String email) throws JsonProcessingException {
        if (webClient == null) {
            webClient = WebClient.create(ioServiceBaseUrl);
        }
        try {
            Map<String, String> body = Map.of("email", email);
            Map<String, String> responseMap;
            WebClient.ResponseSpec response = webClient.method(HttpMethod.DELETE)
                    .uri("/todos/deleteTodo/{id}", id)
                    .body(BodyInserters.fromValue(body))
                    .retrieve().onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new Exception(clientResponse.statusCode().toString())));

            responseMap = objectMapper.readValue(Objects.requireNonNull(response.bodyToMono(String.class).block()), Map.class);
            return new ResponseEntity<>(objectMapper.writeValueAsString(responseMap), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(Map.of("response", "Error " + e.getMessage() + " from Database")), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateTodo(Long id, String email, String description) throws JsonProcessingException {
        if (webClient == null) {
            webClient = WebClient.create(ioServiceBaseUrl);
        }
        try {
            Map<String, String> body = Map.of("email", email, "description", description);
            Map<String, String> responseMap;
            WebClient.ResponseSpec response = webClient.method(HttpMethod.PATCH)
                    .uri("/todos/updateTodo/{id}", id)
                    .body(BodyInserters.fromValue(body))
                    .retrieve().onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new Exception(clientResponse.statusCode().toString())));

            responseMap = objectMapper.readValue(Objects.requireNonNull(response.bodyToMono(String.class).block()), Map.class);
            return new ResponseEntity<>(objectMapper.writeValueAsString(responseMap), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(Map.of("response", "Error " + e.getMessage() + " from Database")), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> toggleTodoStatus(Long id, String email) throws JsonProcessingException {
        if (webClient == null) {
            webClient = WebClient.create(ioServiceBaseUrl);
        }
        try {
            Map<String, String> body = Map.of("email", email);
            Map<String, String> responseMap;
            WebClient.ResponseSpec response = webClient.method(HttpMethod.PATCH)
                    .uri("/todos/toggleDone/{id}", id)
                    .body(BodyInserters.fromValue(body))
                    .retrieve().onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new Exception(clientResponse.statusCode().toString())));

            responseMap = objectMapper.readValue(Objects.requireNonNull(response.bodyToMono(String.class).block()), Map.class);
            return new ResponseEntity<>(objectMapper.writeValueAsString(responseMap), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(Map.of("response", "Error " + e.getMessage() + " from Database")), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteCompleted(String email) throws JsonProcessingException {
        if (webClient == null) {
            webClient = WebClient.create(ioServiceBaseUrl);
        }
        try {
            Map<String, String> body = Map.of("email", email);
            Map<String, String> responseMap;
            WebClient.ResponseSpec response = webClient.method(HttpMethod.DELETE)
                    .uri("/todos/deleteCompleted")
                    .body(BodyInserters.fromValue(body))
                    .retrieve().onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new Exception(clientResponse.statusCode().toString())));

            responseMap = objectMapper.readValue(Objects.requireNonNull(response.bodyToMono(String.class).block()), Map.class);
            return new ResponseEntity<>(objectMapper.writeValueAsString(responseMap), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(Map.of("response", "Error " + e.getMessage() + " from Database")), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
