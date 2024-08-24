package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.response.ProfileResponseDTO;
import com.nocountry.telemedicina.models.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    @Mapping(source = "user.username",target = "email")
    @Mapping(source = "user.userId",target = "userId")
    @Mapping(source = "district.districtName",target = "districtName")
    ProfileResponseDTO toProfileDTO(Profile profile);

    @Mapping(source = "email",target = "user.username")
    @Mapping(source = "userId",target = "user.userId")
    @Mapping(source = "districtName",target = "district.districtName")
    Profile toProfile(ProfileResponseDTO profileDTO);
}
