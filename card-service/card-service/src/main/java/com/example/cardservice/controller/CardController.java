package com.example.cardservice.controller;

import com.example.cardservice.dto.ApiResponse;
import com.example.cardservice.dto.CardDto;
import com.example.cardservice.service.CardService;
import com.example.cardservice.serviceImpl.CardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cart")
@RequiredArgsConstructor
public class CardController implements CardServiceImpl {
    private final CardService cardService;

    @PostMapping
    @Override
    public ApiResponse<CardDto> create(@RequestBody CardDto dto) {
        return this.cardService.create(dto);
    }

    @GetMapping("/{id}")
    @Override
    public ApiResponse<CardDto> get(@PathVariable(value = "id") Integer id) {
        return this.cardService.get(id);
    }

    @PutMapping("/{id}")
    @Override
    public ApiResponse<CardDto> update(@RequestBody CardDto dto,@PathVariable(value = "id") Integer id) {
        return this.cardService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    @Override
    public ApiResponse<CardDto> delete(@PathVariable(value = "id") Integer id) {
        return this.cardService.delete(id);
    }

    @GetMapping("/get-all")
    @Override
    public ApiResponse<List<CardDto>> getAll() {
        return this.cardService.getAll();
    }

    @GetMapping("/get-all-userId/{id}")
    @Override
    public ApiResponse<List<CardDto>> getAllCardByUserId(@PathVariable(value = "id") Integer id) {
        return this.cardService.getAllCardByUserId(id);
    }
}
