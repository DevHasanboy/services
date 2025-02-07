package com.example.userservice.serviceImpl;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.config.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServiceImpl {
    ApiResponse<UserDto> create(UserDto dto);

    ApiResponse<UserDto> get(Integer id);

    ApiResponse<UserDto> update(UserDto dto, Integer id);

    ApiResponse<UserDto> delete(Integer id);

    ApiResponse<List<UserDto>> getAll();

    ApiResponse<UserDto> getAllCards(Integer userId);

    List<Integer> getAllUserIds();

}
