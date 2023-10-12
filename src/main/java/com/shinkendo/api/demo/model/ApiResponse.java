package com.shinkendo.api.demo.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private T payload;
    private String message;

    public ApiResponse(T payload) {
        this.payload = payload;
    }

    public ApiResponse(String message) {
        this.message = message;
    }
}
