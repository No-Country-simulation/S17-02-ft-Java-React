package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.request.SchedulesConfigRequestDTO;
import com.nocountry.telemedicina.dto.response.SchedulesConfigResponseDTO;
import com.nocountry.telemedicina.models.ScheduleConfig;
import com.nocountry.telemedicina.models.enums.EnumDay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SchedulesConfigMapper {
    SchedulesConfigMapper INSTANCE = Mappers.getMapper(SchedulesConfigMapper.class);
    @Mapping(source = "specialist.specialistId", target = "specialistId")
    @Mapping(source = "specialist.profile.profileName",target = "specialistName")
    SchedulesConfigResponseDTO toSchedulesDTO(ScheduleConfig scheduleConfig);


    @Mapping(source = "days",target = "days",qualifiedByName = "convertDays")
    ScheduleConfig toSchedules(SchedulesConfigRequestDTO dto);

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
