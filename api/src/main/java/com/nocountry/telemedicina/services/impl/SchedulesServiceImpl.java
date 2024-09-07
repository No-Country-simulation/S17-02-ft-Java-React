package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.ScheduleConfig;
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

import java.util.List;

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
    public List<ScheduleConfig> saveAll(ScheduleConfig scheduleConfig) {
        return List.of();
    }

    /*    @Override
        public List<Schedules> saveAll(Schedules schedules) {
            long dias = ChronoUnit.DAYS.between(schedules.getSchedulesDay(), schedules.getSchedulesDayEnd());
            LocalDate diaActual = schedules.getSchedulesDay();
            long rango = ChronoUnit.MINUTES.between(schedules.getSchedulesStart(), schedules.getSchedulesEnd());
            long atencionCantidad = rango / (schedules.getSchedulesDuration() + schedules.getSchedulesRest());
            for (int i = 0; i < dias; i++) {// rango de dias
                schedules.setSchedulesDay(diaActual);
                diaActual = diaActual.plusDays(1);

            }

            return List.of();
        }
    */
    private Sort getSort(String sortField, String sortOrder) {
        Sort sort = Sort.by(sortField);
        if (sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return sort;
    }
}
