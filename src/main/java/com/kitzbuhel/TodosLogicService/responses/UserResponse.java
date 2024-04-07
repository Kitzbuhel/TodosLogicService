package com.kitzbuhel.TodosLogicService.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class UserResponse {
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private Boolean isLoggedIn;
    @Getter
    @Setter
    private Date timestamp;

    public UserResponse(String email, String password, Boolean isLoggedIn, Date timestamp) {
        this.email = email;
        this.password = password;
        this.isLoggedIn = isLoggedIn;
        this.timestamp = timestamp;
    }

    public UserResponse() {

    }
}
