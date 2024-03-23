package com.example.userservice.service;

import com.example.userservice.config.CardClient;
import com.example.userservice.dto.ApiResponse;
import com.example.userservice.config.UserDto;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.mapper.UserMapper;
import com.example.userservice.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserService implements UserServiceImpl {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CardClient cardClient;

    @Override
    public ApiResponse<UserDto> create(UserDto dto) {
        try {
            User user = this.userMapper.toEntity(dto);
            user.setCreatedAt(LocalDateTime.now());
            userRepository.save(user);
            return ApiResponse.<UserDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.userMapper.toDto(user))
                    .build();
        } catch (Exception e) {
            return ApiResponse.<UserDto>builder()
                    .code(-1)
                    .message(String.format("while is saving %s error", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ApiResponse<UserDto> get(Integer id) {
        return this.userRepository.findByIdAndDeletedAtIsNull(id)
                .map(user ->

                        ApiResponse.<UserDto>builder()
                                .success(true)
                                .message("ok")
                                .data(this.userMapper.toDto(user))
                                .build()

                ).orElse(ApiResponse.<UserDto>builder()
                        .code(-1)
                        .message(String.format("not found %d id", id))
                        .build());
    }

    @Override
    public ApiResponse<UserDto> update(UserDto dto, Integer id) {
        try {
            return this.userRepository.findByIdAndDeletedAtIsNull(id)
                    .map(user -> {
                        this.userMapper.toUpdate(dto, user);
                        user.setUpdatedAt(LocalDateTime.now());
                        userRepository.save(user);
                        return ApiResponse.<UserDto>builder()
                                .success(true)
                                .message("ok")
                                .data(this.userMapper.toDto(user))
                                .build();

                    }).orElse(ApiResponse.<UserDto>builder()
                            .code(-1)
                            .message(String.format("not found %d id", id))
                            .build());
        } catch (Exception e) {
            return ApiResponse.<UserDto>builder()
                    .code(-1)
                    .message(String.format("while is saving %s error", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ApiResponse<UserDto> delete(Integer id) {
        return this.userRepository.findByIdAndDeletedAtIsNull(id)
                .map(user -> {
                    user.setDeletedAt(LocalDateTime.now());
                    userRepository.delete(user);
                    return ApiResponse.<UserDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.userMapper.toDto(user))
                            .build();

                }).orElse(ApiResponse.<UserDto>builder()
                        .code(-1)
                        .message(String.format("not found %d id", id))
                        .build());
    }

    @Override
    public ApiResponse<List<UserDto>> getAll() {
        List<User> list = this.userRepository.findAllByUserAnddeletedAtIsNull();
        if (list.isEmpty()) {
            return ApiResponse.<List<UserDto>>builder()
                    .code(-1)
                    .message("not found users")
                    .build();
        }
        return ApiResponse.<List<UserDto>>builder()
                .success(true)
                .message("ok")
                .data(list.stream().map(this.userMapper::toDto).toList())
                .build();
    }

    @Override
    public ApiResponse<UserDto> getAllCards(Integer userId) {
        var user=this.userRepository.findByUserIdwithCard(userId);
        if (user.isEmpty()){
            return ApiResponse.<UserDto>builder()
                    .code(-1)
                    .message("not found id")
                    .build();
        }
       var dto=userMapper.toDto(user.get());
        dto.setGetAllCardsByUserId(cardClient.getAllCardByUserId(userId).getData());
        return ApiResponse.<UserDto>builder()
                .success(true)
                .message("ok")
                .data(dto)
                .build();



    }

}

