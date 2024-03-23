package com.example.userservice.config;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "CARD-SERVICE")
public interface CardClient {

    @GetMapping("api/cart/get-all-userId/{id}")
    ApiResponse<List<CardDto>> getAllCardByUserId(@PathVariable(value = "id") Integer id);


}
