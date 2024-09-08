package com.nocountry.telemedicina.config.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.nocountry.telemedicina.dto.response.ScheduleResponseDTO;
import com.nocountry.telemedicina.models.Schedule;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    Schedule INSTANCE = Mappers.getMapper(Schedule.class);

    @Mapping(source = "specialist.specialistId", target = "specialistId")
    @Mapping(source = "scheduleConfig.schedulesConfigId", target = "scheduleConfigId")
    ScheduleResponseDTO toScheduleResponseDTO(Schedule schedule);

}
