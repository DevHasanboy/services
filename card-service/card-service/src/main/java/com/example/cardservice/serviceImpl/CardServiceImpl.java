package com.example.cardservice.serviceImpl;

import com.example.cardservice.dto.ApiResponse;
import com.example.cardservice.dto.CardDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardServiceImpl {
    ApiResponse<CardDto> create(CardDto dto);
    ApiResponse<CardDto> get(Integer id);
    ApiResponse<CardDto> update(CardDto dto,Integer id);
    ApiResponse<CardDto> delete(Integer id);

    ApiResponse<List<CardDto>> getAll();
    ApiResponse<List<CardDto>> getAllCardByUserId(Integer id);
}
