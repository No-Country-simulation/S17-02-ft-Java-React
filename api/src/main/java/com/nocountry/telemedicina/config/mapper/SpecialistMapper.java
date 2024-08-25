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

    @Mapping(source = "clinic.clinicId",target = "clinicId")
    @Mapping(source = "clinic.clinicName",target = "clinicName")
    @Mapping(source = "specialty.name",target = "specialtyName")
    @Mapping(source = "specialty.id",target = "specialtyId")
    SpecialistResponseDTO toSpecialistDTO(Specialist specialist);

    @Mapping(source = "clinic",target = "clinic")
    @Mapping(source = "specialty",target = "specialty")
    Specialist toSpecialist(SpecialistRequestDTO dto);
}
