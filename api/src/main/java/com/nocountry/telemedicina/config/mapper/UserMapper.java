package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.response.UserResponseDTO;
import com.nocountry.telemedicina.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User INSTANCE = Mappers.getMapper(User.class);
    @Mapping(source = "roles", target = "roles")
    UserResponseDTO toUserDTO(User user);
}
