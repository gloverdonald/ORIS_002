package ru.itis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserResponse;
import ru.itis.model.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hashPassword", source = "password")
    UserEntity toUser(UserDto userDto);
    @Mapping(target = "password", source = "hashPassword")
    UserDto toDto(UserEntity user);

    UserEntity toUser(UserResponse userResponse);
    UserResponse toResponse(UserEntity userEntity);
}
