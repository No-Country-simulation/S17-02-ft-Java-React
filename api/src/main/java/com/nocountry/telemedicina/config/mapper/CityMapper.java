package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.response.CityResponseDTO;
import com.nocountry.telemedicina.models.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @Mapping(source = "department.departmentId", target = "departmentId")
    @Mapping(source = "department.departmentName", target = "departmentName")
    CityResponseDTO toDepartmentDTO(City city);

    @Mapping(source = "department",target = "department")
    City toCity(CityResponseDTO cityResponseDTO);
}
