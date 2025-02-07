package com.example.cardservice.config;

import com.example.cardservice.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {
    @GetMapping("/api/user/user-ids/list")
    List<Integer> getAllUserIds();
}
