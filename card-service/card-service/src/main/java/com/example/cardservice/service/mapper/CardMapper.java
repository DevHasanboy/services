package com.example.cardservice.service.mapper;
import com.example.cardservice.dto.CardDto;
import com.example.cardservice.model.Card;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class CardMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract Card toEntity(CardDto dto);

    public  abstract CardDto toDto(Card card);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract void toUpdate(CardDto dto, @MappingTarget Card card);
}
