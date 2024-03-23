package com.example.userservice.controller;

import com.example.userservice.config.CardClient;
import com.example.userservice.dto.ApiResponse;
import com.example.userservice.config.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController implements UserServiceImpl {

    private final UserService userService;
    private final CardClient cardClient;

    @PostMapping
    @Override
    public ApiResponse<UserDto> create(@RequestBody UserDto dto) {
        return this.userService.create(dto);
    }

    @GetMapping("/{id}")
    @Override
    public ApiResponse<UserDto> get(@PathVariable(value = "id") Integer id) {
        return this.userService.get(id);
    }

    @PutMapping("/{id}")
    @Override
    public ApiResponse<UserDto> update(@RequestBody UserDto dto, @PathVariable(value = "id") Integer id) {
        return this.userService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    @Override
    public ApiResponse<UserDto> delete(@PathVariable(value = "id") Integer id) {
        return this.userService.delete(id);
    }

    @GetMapping("/get-all")
    @Override
    public ApiResponse<List<UserDto>> getAll() {
        return this.userService.getAll();
    }


    @GetMapping("/get-all-card/{id}")
    @Override
    public ApiResponse<UserDto> getAllCards(@PathVariable(value = "id") Integer userId) {
        return this.userService.getAllCards(userId);
    }


}
