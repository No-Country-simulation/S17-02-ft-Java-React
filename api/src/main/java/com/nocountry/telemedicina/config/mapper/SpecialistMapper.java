package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.request.SpecialistRequestDTO;
import com.nocountry.telemedicina.dto.response.SpecialistResponseDTO;
import com.nocountry.telemedicina.models.Specialist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpecialistMapper {

    SpecialistMapper INSTANCE = Mappers.getMapper(SpecialistMapper.class);

    @Mapping(source = "specialty.specialtyName",target = "specialtyName")
    @Mapping(source = "specialty.specialtyId",target = "specialtyId")
    SpecialistResponseDTO toSpecialistDTO(Specialist specialist);

    @Mapping(source = "specialty",target = "specialty")
    Specialist toSpecialist(SpecialistRequestDTO dto);
}
