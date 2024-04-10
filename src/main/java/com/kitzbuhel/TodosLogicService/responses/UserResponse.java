package com.kitzbuhel.TodosLogicService.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class UserResponse {
    @Getter
    @Setter
    private boolean status;

    @Getter
    @Setter
    private boolean fromError;

    @Getter
    @Setter
    private String errorMessage;

    public UserResponse(boolean status, boolean fromError, String errorMessage) {
        this.status = status;
        this.fromError = fromError;
        this.errorMessage = errorMessage;
    }
}
