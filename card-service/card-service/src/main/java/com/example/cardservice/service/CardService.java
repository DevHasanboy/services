package com.example.cardservice.service;

import com.example.cardservice.config.UserClient;
import com.example.cardservice.dto.ApiResponse;
import com.example.cardservice.dto.CardDto;
import com.example.cardservice.model.Card;
import com.example.cardservice.repository.CardRepository;
import com.example.cardservice.service.mapper.CardMapper;
import com.example.cardservice.serviceImpl.CardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CardService implements CardServiceImpl {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final UserClient userClient;

    @Override
    public ApiResponse<CardDto> create(CardDto dto) {
        try {
            Card card ;
            List<Integer> allUserIds = userClient.getAllUserIds();
            if (allUserIds.contains(dto.getUserId())) {
                card = this.cardMapper.toEntity(dto);
                card.setCreatedAt(LocalDateTime.now());
                this.cardRepository.save(card);
            } else {
                return ApiResponse.<CardDto>builder()
                        .message("User Id Not found")
                        .code(-1)
                        .build();
            }

            return ApiResponse.<CardDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.cardMapper.toDto(card))
                    .build();
        } catch (Exception e) {
            return ApiResponse.<CardDto>builder()
                    .code(-1)
                    .message(String.format("while is saving %s  :: error", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ApiResponse<CardDto> get(Integer id) {
        return this.cardRepository.findByIdAndDeletedAtIsNull(id)
                .map(card ->
                        ApiResponse.<CardDto>builder()
                                .success(true)
                                .message("ok")
                                .data(this.cardMapper.toDto(card))
                                .build()
                ).orElse(ApiResponse.<CardDto>builder()
                        .code(-1)
                        .message(String.format(" %d id not found ", id))
                        .build());
    }

    @Override
    public ApiResponse<CardDto> update(CardDto dto, Integer id) {
        try {

            return this.cardRepository.findByIdAndDeletedAtIsNull(id)
                    .map(card -> {
                        this.cardMapper.toUpdate(dto, card);
                        card.setUpdatedAt(LocalDateTime.now());
                        this.cardRepository.save(card);
                        return ApiResponse.<CardDto>builder()
                                .success(true)
                                .message("ok")
                                .data(this.cardMapper.toDto(card))
                                .build();

                    }).orElse(ApiResponse.<CardDto>builder()
                            .code(-1)
                            .message(String.format(" %d id not found ", id))
                            .build());
        } catch (Exception e) {
            return ApiResponse.<CardDto>builder()
                    .code(-1)
                    .message(String.format("while is updating %s  :: error", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ApiResponse<CardDto> delete(Integer id) {
        return this.cardRepository.findByIdAndDeletedAtIsNull(id)
                .map(card -> {
                    card.setDeletedAt(LocalDateTime.now());
                    this.cardRepository.delete(card);
                    return ApiResponse.<CardDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.cardMapper.toDto(card))
                            .build();
                }).orElse(ApiResponse.<CardDto>builder()
                        .code(-1)
                        .message(String.format(" %d id not found ", id))
                        .build());
    }

    @Override
    public ApiResponse<List<CardDto>> getAll() {
        List<Card> list = this.cardRepository.getAllCardByAndDeletedAtIsNull();
        return getListApiResponse(list);
    }

    @Override
    public ApiResponse<List<CardDto>> getAllCardByUserId(Integer id) {
        List<Card> list1 = this.cardRepository.getAllByUserIdAndDeletedAtIsNull(id);
        return getListApiResponse(list1);
    }

    private ApiResponse<List<CardDto>> getListApiResponse(List<Card> list1) {
        if (list1.isEmpty()) {
            return ApiResponse.<List<CardDto>>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
        return ApiResponse.<List<CardDto>>builder()
                .success(true)
                .message("ok")
                .data(list1.stream().map(this.cardMapper::toDto).toList())
                .build();
    }
}
