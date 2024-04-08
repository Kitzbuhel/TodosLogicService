package com.kitzbuhel.TodosLogicService.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitzbuhel.TodosLogicService.responses.TodoResponse;
import com.kitzbuhel.TodosLogicService.services.TodoService;
import com.kitzbuhel.TodosLogicService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
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

        if (userService.status(email)) {
            return todoService.getTodos(email);
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

        if (userService.status(email)) {
            return todoService.getCompletedTodos(email);
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

        if (userService.status(email)) {
            return todoService.getNotCompletedTodos(email);
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

        if (userService.status(email)) {
            return todoService.addTodo(email, description);
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

        if (userService.status(email)) {
            return todoService.deleteTodo(id, email);
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

        if (userService.status(email)) {
            return todoService.updateTodo(id, email, description);
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

        if (userService.status(email)) {
            return todoService.toggleTodoStatus(id, email);
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

        if (userService.status(email)) {
            return todoService.deleteCompleted(email);
        }

        response.put("response", "User not logged in");
        return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.UNAUTHORIZED);
    }
}
