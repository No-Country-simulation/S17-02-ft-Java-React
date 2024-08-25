package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.request.SubsidiaryRequestDTO;
import com.nocountry.telemedicina.dto.response.SubsidiaryResponseDTO;
import com.nocountry.telemedicina.models.Subsidiary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubsidiaryMapper {

    SubsidiaryMapper INSTANCE = Mappers.getMapper(SubsidiaryMapper.class);

    @Mapping(source = "district.districtName",target = "districtName")
    @Mapping(source = "clinic.clinicId",target = "clinicId")
    @Mapping(source = "clinic.clinicName",target = "clinicName")
    SubsidiaryResponseDTO toSubsidiaryDTO(Subsidiary subsidiary);

    @Mapping(source = "district",target = "district")
    @Mapping(source = "clinic",target = "clinic")
    Subsidiary toSubsidiary(SubsidiaryRequestDTO dto);
}
