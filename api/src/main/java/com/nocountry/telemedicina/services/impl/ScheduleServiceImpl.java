package com.nocountry.telemedicina.services.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.nocountry.telemedicina.config.mapper.ScheduleMapper;
import com.nocountry.telemedicina.dto.response.ScheduleResponseDTO;
import org.springframework.stereotype.Service;

import com.nocountry.telemedicina.exception.CustomException;
import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.Schedule;
import com.nocountry.telemedicina.repository.IScheduleRepo;
import com.nocountry.telemedicina.services.IScheduleService;

@Service
public class ScheduleServiceImpl implements IScheduleService {

    IScheduleRepo scheduleRepo;

    ScheduleMapper scheduleMapper;

    public ScheduleServiceImpl(IScheduleRepo scheduleRepo, ScheduleMapper scheduleMapper) {
        this.scheduleRepo = scheduleRepo;
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    public List<ScheduleResponseDTO> findSchedulesByDate(LocalDate date, UUID specialistId) {
        try {
            return scheduleMapper.toScheduleResponseDTOList(scheduleRepo.findSchedulesByDate(date, specialistId));
        } catch (Exception e) {
            throw new CustomException(500, e.getMessage());
        }
    }

    @Override
    public List<ScheduleResponseDTO> findSchedulesBySpecialist(UUID specialistId) {
        try {
            return scheduleMapper.toScheduleResponseDTOList(scheduleRepo.findSchedulesBySpecialistId(specialistId));
        } catch (Exception e) {
            throw new CustomException(500, e.getMessage());
        }
    }

    @Override
    public ScheduleResponseDTO findScheduleById(UUID scheduleId) {
        Schedule schedule =  scheduleRepo.findById(scheduleId)
                .orElseThrow(() -> new NotFoundException(String.format("Schedule with id: %s, not found", scheduleId)));
        return scheduleMapper.toScheduleResponseDTO(schedule);
    }

}
