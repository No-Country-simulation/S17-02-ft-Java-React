package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.request.DepartmentRequestDTO;
import com.nocountry.telemedicina.dto.response.DepartmentResponseDTO;
import com.nocountry.telemedicina.models.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    DepartmentResponseDTO toDepartmentDTO(Department department);

    Department toDepartment(DepartmentRequestDTO departmentResponseDTO);
}
