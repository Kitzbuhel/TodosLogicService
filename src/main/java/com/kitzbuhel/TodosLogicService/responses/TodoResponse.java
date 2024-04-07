package com.kitzbuhel.TodosLogicService.responses;

import lombok.Getter;
import lombok.Setter;

public class TodoResponse {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private Boolean done;

    public TodoResponse(Long id, String email, String description, Boolean done) {
        this.id = id;
        this.email = email;
        this.description = description;
        this.done = done;
    }

    public TodoResponse() {

    }
}
