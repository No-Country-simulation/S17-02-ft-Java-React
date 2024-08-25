package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.request.SpecialtyRequestDTO;
import com.nocountry.telemedicina.dto.response.SpecialtyResponseDTO;
import com.nocountry.telemedicina.models.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {

    SpecialistMapper INSTANCE = Mappers.getMapper(SpecialistMapper.class);

    @Mapping(source = "specialists",target = "specialists")
    SpecialtyResponseDTO toSpecialtyDTO(Specialty specialty);

    Specialty toSpecialty(SpecialtyRequestDTO dto);
}
