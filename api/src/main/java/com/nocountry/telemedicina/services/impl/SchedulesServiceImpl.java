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
    public Page<ScheduleConfig> findAllByUserId(UserPrincipal user, int page, int size, String sortField, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, getSort(sortField, sortOrder));
        return repo.findAllByUserId(user.getId(), pageable);
    }



        @Override
        public void saveAll(ScheduleConfig schedules) {

            List<EnumDay> days = schedules.getDays();
            List<LocalDate> workingDays = generateWorkingDays(schedules.getSchedulesDayStart(),schedules.getSchedulesDayEnd(),days);
            long rangeBeforeRest = ChronoUnit.MINUTES.between(schedules.getSchedulesStart(), schedules.getSchedulesStartRest());
            long rangeAfterRest = ChronoUnit.MINUTES.between(schedules.getSchedulesEndRest(), schedules.getSchedulesEnd());
            long bookingsquantityBeforeRest = rangeBeforeRest / (schedules.getSchedulesDuration() + schedules.getSchedulesRest());
            long bookingsquantityAfterRest = rangeBeforeRest / (schedules.getSchedulesDuration() + schedules.getSchedulesRest());

        }

//            for (int i = 0; i < dias; i++) {
//                schedules.setSchedulesDay(diaActual);
//                diaActual = diaActual.plusDays(1);
//            }

    private Sort getSort(String sortField, String sortOrder) {
        Sort sort = Sort.by(sortField);
        if (sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return sort;
    }



    private List<LocalDate> generateWorkingDays(LocalDate schedulesDay,LocalDate schedulesDayEnd, List<EnumDay> daysToInclude) {
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

    private static List<EnumDay> convertStringToEnumDayList(String daysString) {
        // Divide la cadena en partes usando la coma como delimitador
        String[] dayStrings = daysString.split(",");
        return Arrays.stream(dayStrings)
                .map(String::trim)
                .map(EnumDay::valueOf)
                .collect(Collectors.toList());
    }
}
