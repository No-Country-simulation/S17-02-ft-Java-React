package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.response.UserResponseDTO;
import com.nocountry.telemedicina.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "roles", target = "roles")
    UserResponseDTO toUserDTO(User user);
}
