package com.example.userservice.config;

import com.example.userservice.dto.CardDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String lastname;
    private String firstname;
    private Integer age;

    private List<CardDto> getAllCardsByUserId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
