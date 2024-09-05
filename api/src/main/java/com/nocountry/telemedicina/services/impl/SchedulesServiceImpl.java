package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Schedules;
import com.nocountry.telemedicina.models.User;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.ISchedulesRepo;
import com.nocountry.telemedicina.repository.ISpecialistRepo;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import com.nocountry.telemedicina.services.ISchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulesServiceImpl extends CRUDServiceImpl<Schedules, Long> implements ISchedulesService {

    @Autowired
    private ISchedulesRepo repo;

    @Autowired
    private ISpecialistRepo specialistRepo;
    @Override
    protected IGenericRepo<Schedules, Long> getRepo() {
        return repo;
    }

    @Override
    public Schedules save(Schedules schedules, UserPrincipal user) {
        schedules.setSpecialist(specialistRepo.findByUserId(user.getId()));
        return repo.save(schedules);
    }
}
