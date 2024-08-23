package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Schedules;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.ISchedulesRepo;
import com.nocountry.telemedicina.services.ISchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SchedulesServiceImpl extends CRUDServiceImpl<Schedules, UUID> implements ISchedulesService {

    @Autowired
    private ISchedulesRepo repo;

    @Override
    protected IGenericRepo<Schedules, UUID> getRepo() {
        return repo;
    }
}
