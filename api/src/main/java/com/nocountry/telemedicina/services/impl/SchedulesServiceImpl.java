package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.exception.BadRequestException;
import com.nocountry.telemedicina.exception.CustomException;
import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.Schedule;
import com.nocountry.telemedicina.models.ScheduleConfig;
import com.nocountry.telemedicina.models.Specialist;
import com.nocountry.telemedicina.models.enums.EnumDay;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.IScheduleConfigRepo;
import com.nocountry.telemedicina.repository.IScheduleRepo;
import com.nocountry.telemedicina.repository.ISpecialistRepo;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import com.nocountry.telemedicina.services.ISchedulesService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class SchedulesServiceImpl extends CRUDServiceImpl<ScheduleConfig, Long> implements ISchedulesService {

    @Autowired
    private IScheduleConfigRepo scheduleConfigRepository;

    @Autowired
    private ISpecialistRepo specialistRepo;

    @Autowired
    private IScheduleRepo scheduleRepo;

    @Override
    protected IGenericRepo<ScheduleConfig, Long> getRepo() {
        return scheduleConfigRepository;
    }

    @Override
    public Page<ScheduleConfig> findAllByUserId(UserPrincipal user, int page, int size, String sortField,
            String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, getSort(sortField, sortOrder));
        return scheduleConfigRepository.findAllByUserId(user.getId(), pageable);
    }

    @Transactional
    @Override
    public ScheduleConfig save(ScheduleConfig scheduleConfig, UserPrincipal user) {
        Specialist specialist = specialistRepo.findByUserId(user.getId()).orElseThrow(
                () -> new NotFoundException(String.format("Specialist not found with id: %s", user.getId())));
        Optional<ScheduleConfig> scheduleActiveOptional = scheduleConfigRepository
                .findActiveBySpecialistId(specialist.getSpecialistId());
        if (scheduleActiveOptional.isPresent()) {
            ScheduleConfig scheduleActived = scheduleActiveOptional.get();
            if (scheduleActived.getSchedulesDayEnd().isAfter(LocalDate.now())) {
                throw new BadRequestException(
                        "There are already configured schedules, if you want to create a new configuration please delete the previous configuration.");
            } else {
                scheduleActived.setActive(false);
                scheduleRepo.DeleteScheduleByScheduleConfigId(scheduleActived.getSchedulesConfigId());
            }
        }
        try {
            scheduleConfig.setSpecialist(specialist);
            ScheduleConfig newSchedule = scheduleConfigRepository.save(scheduleConfig);
            createSchedules(newSchedule, user.getId());
            return newSchedule;
        } catch (Exception exception) {
            throw new CustomException(500, exception.getMessage());
        }
    }

    @Override
    public void createSchedules(ScheduleConfig scheduleConfig, UUID userId) {
        int totalTime = scheduleConfig.getSchedulesDuration() + scheduleConfig.getSchedulesRest();
        Specialist specialist = specialistRepo.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(String.format("Specialist not found with id: %s", userId)));
        Set<LocalDate> workingDays = generateWorkingDays(scheduleConfig.getSchedulesDayStart(),
                scheduleConfig.getSchedulesDayEnd(), scheduleConfig.getDays());
        Set<LocalTime> workingHours = generateWorkingHours(
                scheduleConfig.getSchedulesStart(),
                scheduleConfig.getSchedulesStartRest(),
                scheduleConfig.getSchedulesEndRest(),
                scheduleConfig.getSchedulesEnd(),
                scheduleConfig.getSchedulesDuration(),
                scheduleConfig.getSchedulesRest());

        try {
            for (LocalDate day : workingDays) {
                for (LocalTime hour : workingHours) {
                    Schedule schedule = new Schedule();
                    schedule.setDate(day);
                    schedule.setActive(true);
                    schedule.setCreateBy(userId);
                    schedule.setSpecialist(specialist);
                    schedule.setStartTime(hour);
                    schedule.setEndTime(hour.plusMinutes(totalTime));
                    schedule.setScheduleConfig(scheduleConfig);
                    scheduleRepo.save(schedule);
                }
            }
        } catch (Exception ex) {
            throw new CustomException(500, ex.getMessage());
        }

    }

    private Set<LocalDate> generateWorkingDays(LocalDate schedulesDay, LocalDate schedulesDayEnd,
            List<EnumDay> daysToInclude) {
        Set<LocalDate> filteredDays = new HashSet<>();
        LocalDate current = schedulesDay;
        Set<DayOfWeek> dayOfWeeksToInclude = new HashSet<>();
        for (EnumDay day : daysToInclude) {
            dayOfWeeksToInclude.add(DayOfWeek.valueOf(day.name()));
        }
        while (!current.isAfter(schedulesDayEnd)) {
            if (dayOfWeeksToInclude.contains(current.getDayOfWeek())) {
                filteredDays.add(current);
            }
            current = current.plusDays(1);
        }
        return filteredDays;
    }

    private Set<LocalTime> generateWorkingHours(LocalTime start, LocalTime startRest,
            LocalTime endRest, LocalTime end, Integer duration, Integer rest) {
        Integer totalTime = duration + rest;
        Set<LocalTime> workingHours = new HashSet<>();

        generateIntervals(workingHours, start, startRest, totalTime);
        generateIntervals(workingHours, endRest, end, totalTime);

        return workingHours;
    }

    private Sort getSort(String sortField, String sortOrder) {
        Sort sort = Sort.by(sortField);
        if (sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return sort;
    }

    private static void generateIntervals(Set<LocalTime> workingHours, LocalTime start, LocalTime end,
            Integer duration) {
        LocalTime current = start;

        while (current.plusMinutes(duration).isBefore(end) || current.plusMinutes(duration).equals(end)) {
            workingHours.add(current);
            current = current.plusMinutes(duration);
        }
    }

}
