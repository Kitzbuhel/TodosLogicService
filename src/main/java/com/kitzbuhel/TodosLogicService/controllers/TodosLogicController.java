package com.kitzbuhel.TodosLogicService.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitzbuhel.TodosLogicService.responses.TodoResponse;
import com.kitzbuhel.TodosLogicService.responses.UserResponse;
import com.kitzbuhel.TodosLogicService.services.TodoService;
import com.kitzbuhel.TodosLogicService.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todo")
public class TodosLogicController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private UserService userService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/getTodos")
    public ResponseEntity<String> getTodos(@RequestBody Map<String, String> body) throws JsonProcessingException {
        Map<String, String> response = new HashMap<>();
        String email = body.get("email");
        if (email == null) {
            response.put("response", "Email is required");
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.BAD_REQUEST);
        }

        UserResponse userResponse = userService.status(email);
        if (userResponse.isStatus()) {
            return todoService.getTodos(email);
        }

        if (userResponse.isFromError()) {
            response.put("response", userResponse.getErrorMessage());
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("response", "User not logged in");
        return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/getCompletedTodos")
    public ResponseEntity<String> getCompletedTodos(@RequestBody Map<String, String> body) throws JsonProcessingException {
        Map<String, String> response = new HashMap<>();
        String email = body.get("email");
        if (email == null) {
            response.put("response", "Email is required");
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.BAD_REQUEST);
        }

        UserResponse userResponse = userService.status(email);
        if (userResponse.isStatus()) {
            return todoService.getCompletedTodos(email);
        }

        if (userResponse.isFromError()) {
            response.put("response", userResponse.getErrorMessage());
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("response", "User not logged in");
        return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/getNotCompletedTodos")
    public ResponseEntity<String> getNotCompletedTodos(@RequestBody Map<String, String> body) throws JsonProcessingException {
        Map<String, String> response = new HashMap<>();
        String email = body.get("email");
        if (email == null) {
            response.put("response", "Email is required");
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.BAD_REQUEST);
        }

        UserResponse userResponse = userService.status(email);
        if (userResponse.isStatus()) {
            return todoService.getNotCompletedTodos(email);
        }

        if (userResponse.isFromError()) {
            response.put("response", userResponse.getErrorMessage());
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("response", "User not logged in");
        return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/addTodo")
    public ResponseEntity<String> addTodo(@RequestBody Map<String, String> body) throws JsonProcessingException {
        Map<String, String> response = new HashMap<>();
        String email = body.get("email");
        if (email == null) {
            response.put("response", "Email is required");
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.BAD_REQUEST);
        }

        String description = body.get("description");
        if (description == null) {
            response.put("response", "Description is required");
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.BAD_REQUEST);
        }

        UserResponse userResponse = userService.status(email);
        if (userResponse.isStatus()) {
            return todoService.addTodo(email, description);
        }

        if (userResponse.isFromError()) {
            response.put("response", userResponse.getErrorMessage());
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("response", "User not logged in");
        return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/deleteTodo/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id, @RequestBody Map<String, String> body) throws JsonProcessingException {
        Map<String, String> response = new HashMap<>();
        String email = body.get("email");
        if (email == null) {
            response.put("response", "Email is required");
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.BAD_REQUEST);
        }

        UserResponse userResponse = userService.status(email);
        if (userResponse.isStatus()) {
            return todoService.deleteTodo(id, email);
        }

        if (userResponse.isFromError()) {
            response.put("response", userResponse.getErrorMessage());
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("response", "User not logged in");
        return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.UNAUTHORIZED);
    }

    @PatchMapping("/updateTodo/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable Long id, @RequestBody Map<String, String> body) throws JsonProcessingException {
        Map<String, String> response = new HashMap<>();
        String email = body.get("email");
        if (email == null) {
            response.put("response", "Email is required");
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.BAD_REQUEST);
        }

        String description = body.get("description");
        if (description == null) {
            response.put("response", "Description is required");
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.BAD_REQUEST);
        }

        UserResponse userResponse = userService.status(email);
        if (userResponse.isStatus()) {
            return todoService.updateTodo(id, email, description);
        }

        if (userResponse.isFromError()) {
            response.put("response", userResponse.getErrorMessage());
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("response", "User not logged in");
        return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.UNAUTHORIZED);
    }

    @PatchMapping("/toggleTodoStatus/{id}")
    public ResponseEntity<String> toggleTodoStatus(@PathVariable Long id, @RequestBody Map<String, String> body) throws JsonProcessingException {
        Map<String, String> response = new HashMap<>();
        String email = body.get("email");
        if (email == null) {
            response.put("response", "Email is required");
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.BAD_REQUEST);
        }

        UserResponse userResponse = userService.status(email);
        if (userResponse.isStatus()) {
            return todoService.toggleTodoStatus(id, email);
        }

        if (userResponse.isFromError()) {
            response.put("response", userResponse.getErrorMessage());
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("response", "User not logged in");
        return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/deleteCompleted")
    public ResponseEntity<String> deleteCompleted(@RequestBody Map<String, String> body) throws JsonProcessingException {
        Map<String, String> response = new HashMap<>();
        String email = body.get("email");
        if (email == null) {
            response.put("response", "Email is required");
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.BAD_REQUEST);
        }

        UserResponse userResponse = userService.status(email);
        if (userResponse.isStatus()) {
            return todoService.deleteCompleted(email);
        }

        if (userResponse.isFromError()) {
            response.put("response", userResponse.getErrorMessage());
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("response", "User not logged in");
        return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.UNAUTHORIZED);
    }
}
