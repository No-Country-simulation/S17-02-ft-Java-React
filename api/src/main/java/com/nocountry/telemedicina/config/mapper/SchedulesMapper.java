package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.request.SchedulesRequestDTO;
import com.nocountry.telemedicina.dto.response.SchedulesResponseDTO;
import com.nocountry.telemedicina.models.Schedules;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SchedulesMapper {

    SchedulesMapper INSTANCE = Mappers.getMapper(SchedulesMapper.class);

    SchedulesResponseDTO toSchedulesDTO(Schedules schedules);

    Schedules toSchedules(SchedulesRequestDTO dto);
}
