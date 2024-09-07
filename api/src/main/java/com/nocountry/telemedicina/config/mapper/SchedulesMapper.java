package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.request.SchedulesRequestDTO;
import com.nocountry.telemedicina.dto.response.SchedulesResponseDTO;
import com.nocountry.telemedicina.models.Schedules;
import com.nocountry.telemedicina.models.enums.EnumDay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SchedulesMapper {
    SchedulesMapper INSTANCE = Mappers.getMapper(SchedulesMapper.class);
    @Mapping(source = "specialist.specialistId", target = "specialistId")
    @Mapping(source = "specialist.profile.profileName",target = "specialistName")
    SchedulesResponseDTO toSchedulesDTO(Schedules schedules);
    @Mapping(source = "specialist",target = "specialist")
    @Mapping(source = "specialist.days",target = "specialist.days",qualifiedByName = "convertDays")
    Schedules toSchedules(SchedulesRequestDTO dto);

    @Named("convertDays")
    default String convertDaysToString(List<EnumDay> days) {
        if (days == null || days.isEmpty()) {
            return "";
        }
        return days.stream()
                .map(EnumDay::name) //
                .collect(Collectors.joining(", "));
    }
}
