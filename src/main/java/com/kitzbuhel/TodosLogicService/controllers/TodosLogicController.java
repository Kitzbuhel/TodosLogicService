package com.kitzbuhel.TodosLogicService.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitzbuhel.TodosLogicService.responses.TodoResponse;
import com.kitzbuhel.TodosLogicService.services.TodoService;
import com.kitzbuhel.TodosLogicService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class TodosLogicController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private UserService userService;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = Logger.getLogger(TodosLogicController.class.getName());

    @GetMapping("/getTodos")
    public ResponseEntity<String> getTodos(@RequestBody Map<String, String> body) throws JsonProcessingException {
        logger.info("Getting todos");
        Map<String, String> response = new HashMap<>();
        String email = body.get("email");
        if (email == null) {
            response.put("response", "Email is required");
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.BAD_REQUEST);
        }

        if (userService.status(email)) {
            response.put("response", objectMapper.writeValueAsString(todoService.getTodos(email)));
            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.OK);
        }

        response.put("response", "User not logged in");
        return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.UNAUTHORIZED);
    }
}
