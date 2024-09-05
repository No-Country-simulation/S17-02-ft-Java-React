package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.request.ProfileRequestDTO;
import com.nocountry.telemedicina.dto.response.ProfileResponseDTO;
import com.nocountry.telemedicina.models.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    @Mapping(source = "user.username", target = "email")
    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "city.cityName", target = "cityName")
    ProfileResponseDTO toProfileDTO(Profile profile);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "city", target = "city")
    Profile toProfile(ProfileRequestDTO profileDTO);
}
