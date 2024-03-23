package com.example.userservice.service.mapper;

import com.example.userservice.config.UserDto;
import com.example.userservice.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public  abstract class UserMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public  abstract User toEntity(UserDto dto);

    public  abstract UserDto toDto(User user);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract void toUpdate(UserDto dto, @MappingTarget User user);
}
