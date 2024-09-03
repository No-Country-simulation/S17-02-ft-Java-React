package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.request.ClinicalRecordRequestDTO;
import com.nocountry.telemedicina.dto.response.ClinicalRecordResponseDTO;
import com.nocountry.telemedicina.models.ClinicalRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClinicalRecordsMapper {

    ClinicalRecordsMapper INSTANCE = Mappers.getMapper(ClinicalRecordsMapper.class);

    @Mapping(source = "clinicalHistory.clinicalHistoryId",target = "clinicalHistoryId")
    @Mapping(source = "clinicalHistory.historyCode",target = "historyCode")
    @Mapping(source = "booking",target = "booking")
    ClinicalRecordResponseDTO toClinicalRecordDTO(ClinicalRecord clinicalRecord);

    @Mapping(source = "booking",target = "booking")
    @Mapping(source = "clinicalHistory",target = "clinicalHistory")
    ClinicalRecord toClinicalRecord(ClinicalRecordRequestDTO dto);

}
