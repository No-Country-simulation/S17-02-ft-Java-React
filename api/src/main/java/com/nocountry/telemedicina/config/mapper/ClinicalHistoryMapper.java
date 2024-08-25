package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.request.ClinicalHistoryRequestDTO;
import com.nocountry.telemedicina.dto.response.ClinicalHistoryResponseDTO;
import com.nocountry.telemedicina.models.ClinicalHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClinicalHistoryMapper {

    ClinicalHistoryMapper INSTANCE = Mappers.getMapper(ClinicalHistoryMapper.class);

    @Mapping(source = "user.username",target = "username")
    @Mapping(source = "user.userId",target = "userId")
    ClinicalHistoryResponseDTO toClinicalHistoryDTO(ClinicalHistory clinicalHistory);

    @Mapping(source = "user",target = "user")
    ClinicalHistory toClinicalHistory(ClinicalHistoryRequestDTO dto);
}
