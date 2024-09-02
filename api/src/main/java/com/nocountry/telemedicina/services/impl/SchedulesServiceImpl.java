package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Schedules;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.ISchedulesRepo;
import com.nocountry.telemedicina.services.ISchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SchedulesServiceImpl extends CRUDServiceImpl<Schedules, Long> implements ISchedulesService {

    @Autowired
    private ISchedulesRepo repo;

    @Override
    protected IGenericRepo<Schedules, Long> getRepo() {
        return repo;
    }
}
