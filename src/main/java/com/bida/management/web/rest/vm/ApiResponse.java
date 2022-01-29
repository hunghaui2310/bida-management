package com.bida.management.web.rest.vm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {

    private Boolean success;
    private String message;
    private String field;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(Boolean success, String message, String field) {
        this.success = success;
        this.message = message;
        this.field = field;
    }

    public ApiResponse(Boolean success) {
        this.success = success;
    }
}
