package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.ScheduleConfig;
import com.nocountry.telemedicina.models.enums.EnumDay;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.ISchedulesRepo;
import com.nocountry.telemedicina.repository.ISpecialistRepo;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import com.nocountry.telemedicina.services.ISchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SchedulesServiceImpl extends CRUDServiceImpl<ScheduleConfig, Long> implements ISchedulesService {

    @Autowired
    private ISchedulesRepo repo;

    @Autowired
    private ISpecialistRepo specialistRepo;

    @Override
    protected IGenericRepo<ScheduleConfig, Long> getRepo() {
        return repo;
    }

    @Override
    public ScheduleConfig save(ScheduleConfig scheduleConfig, UserPrincipal user) {
        scheduleConfig.setSpecialist(specialistRepo.findByUserId(user.getId()));
        return repo.save(scheduleConfig);
    }

    @Override
    public Page<ScheduleConfig> findAllByUserId(UserPrincipal user, int page, int size, String sortField,
            String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, getSort(sortField, sortOrder));
        return repo.findAllByUserId(user.getId(), pageable);
    }

    @Override
    public void saveAll(ScheduleConfig schedules) {

        List<EnumDay> days = schedules.getDays();
        List<LocalDate> workingDays = generateWorkingDays(schedules.getSchedulesDayStart(),
                schedules.getSchedulesDayEnd(), days);
        List<LocalTime> workingHours = generateWorkingHours(
                schedules.getSchedulesStart(),
                schedules.getSchedulesStartRest(),
                schedules.getSchedulesEndRest(),
                schedules.getSchedulesEnd(),
                schedules.getSchedulesDuration(),
                schedules.getSchedulesRest());
    }


    private List<LocalDate> generateWorkingDays(LocalDate schedulesDay, LocalDate schedulesDayEnd,
            List<EnumDay> daysToInclude) {
        List<LocalDate> filteredDays = new ArrayList<>();
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

    private List<LocalTime> generateWorkingHours(LocalTime start, LocalTime startRest,
                                                     LocalTime endRest, LocalTime end, Integer duration, Integer rest) {
        Integer totalTime = duration + rest;
        List<LocalTime> workingHours = new ArrayList<>();

        generateIntervals(workingHours,start,startRest,totalTime);
        generateIntervals(workingHours,endRest,end,totalTime);

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

    private static void generateIntervals(List<LocalTime> workingHours, LocalTime start, LocalTime end,
            Integer duration) {
        LocalTime current = start;

        while (current.plusMinutes(duration).isBefore(end) || current.plusMinutes(duration).equals(end)) {
            workingHours.add(current);
            current = current.plusMinutes(duration);
        }
    }

}
