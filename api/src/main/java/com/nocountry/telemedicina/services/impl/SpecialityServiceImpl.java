package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Specialty;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.ISpecialtyRepo;
import com.nocountry.telemedicina.services.ISpecialityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class SpecialityServiceImpl extends CRUDServiceImpl<Specialty, UUID> implements ISpecialityService {

    @Autowired
    private ISpecialtyRepo repo;

    @Override
    protected IGenericRepo<Specialty, UUID> getRepo() {
        return repo;
    }
}
