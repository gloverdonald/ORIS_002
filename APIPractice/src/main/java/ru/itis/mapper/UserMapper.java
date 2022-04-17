package ru.itis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.request.UserRequest;
import ru.itis.dto.response.UserResponse;
import ru.itis.model.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hashPassword", source = "password")
    UserEntity toUser(UserRequest userRequest);
    @Mapping(target = "password", source = "hashPassword")
    UserRequest toRequest(UserEntity user);

    UserEntity toUser(UserResponse userResponse);
    @Mapping(target = "password", source = "hashPassword")
    UserResponse toResponse(UserEntity userEntity);
}
