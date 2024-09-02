package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.request.ClinicRequestDTO;
import com.nocountry.telemedicina.dto.response.ClinicResponseDTO;
import com.nocountry.telemedicina.models.Clinic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClinicMapper {

    ClinicMapper INSTANCE = Mappers.getMapper(ClinicMapper.class);

    @Mapping(source = "subsidiaries",target = "subsidiaries")
    @Mapping(source = "user.userId",target = "userId")
    @Mapping(source = "user.username", target = "username")
    ClinicResponseDTO toClinicDTO(Clinic clinic);

    @Mapping(source = "user",target = "user")
    Clinic toClinic(ClinicRequestDTO dto);
}
