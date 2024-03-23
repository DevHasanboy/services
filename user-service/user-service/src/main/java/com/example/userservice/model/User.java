package com.example.userservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user7070")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String lastname;
     private String firstname;
     private Integer age;

     private LocalDateTime createdAt;
     private LocalDateTime updatedAt;
     private LocalDateTime deletedAt;

}
