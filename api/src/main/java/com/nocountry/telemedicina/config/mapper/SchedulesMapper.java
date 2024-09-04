package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.request.SchedulesRequestDTO;
import com.nocountry.telemedicina.dto.response.SchedulesResponseDTO;
import com.nocountry.telemedicina.models.Schedules;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SchedulesMapper {

    SchedulesMapper INSTANCE = Mappers.getMapper(SchedulesMapper.class);

    @Mapping(source = "specialist.specialistId", target = "specialistId")
    @Mapping(source = "specialist.profile.profileName",target = "specialistName")
    SchedulesResponseDTO toSchedulesDTO(Schedules schedules);

    @Mapping(source = "specialist",target = "specialist")
    Schedules toSchedules(SchedulesRequestDTO dto);
}
