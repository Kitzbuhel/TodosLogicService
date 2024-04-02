package com.kitzbuhel.controllers;

import com.kitzbuhel.responses.TodoResponse;
import com.kitzbuhel.services.TodoService;
import com.kitzbuhel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodosLogicController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private UserService userService;

    @GetMapping("/getTodos")
    public List<TodoResponse> getTodos(String email) {
        if (userService.status(email)) {
            return todoService.getTodos(email);
        }
        return null;
    }
}
