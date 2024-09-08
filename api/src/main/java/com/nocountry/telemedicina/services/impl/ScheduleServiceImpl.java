package com.nocountry.telemedicina.services.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nocountry.telemedicina.exception.CustomException;
import com.nocountry.telemedicina.models.Schedule;
import com.nocountry.telemedicina.repository.IScheduleRepo;
import com.nocountry.telemedicina.services.IScheduleService;

@Service
public class ScheduleServiceImpl implements IScheduleService {

    IScheduleRepo scheduleRepo;

    public ScheduleServiceImpl(IScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    @Override
    public List<Schedule> findSchedulesByDate(LocalDate date, UUID specialistId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);
        try {
            return scheduleRepo.findSchedulesByDate(formattedDate, specialistId);
        } catch (Exception e) {
            throw new CustomException(500, e.getMessage());
        }
    }

    @Override
    public List<Schedule> findSchedulesBySpecialist(UUID specialistId) {
        try {
            return scheduleRepo.findSchedulesBySpecialistId(specialistId);
        } catch (Exception e) {

            throw new CustomException(500, e.getMessage());
        }
    }

}
