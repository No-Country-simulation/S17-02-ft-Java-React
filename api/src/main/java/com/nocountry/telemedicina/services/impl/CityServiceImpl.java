package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.City;
import com.nocountry.telemedicina.repository.ICityRepo;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.services.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CityServiceImpl extends CRUDServiceImpl<City, Long> implements ICityService {

    @Autowired
    private ICityRepo repo;

    @Override
    protected IGenericRepo<City, Long> getRepo() {
        return repo;
    }
}
