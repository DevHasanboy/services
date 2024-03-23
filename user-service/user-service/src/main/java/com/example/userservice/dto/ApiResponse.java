package com.example.userservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T>{
    private int code;
    private String message;
    private boolean success;
    private T data;
}
