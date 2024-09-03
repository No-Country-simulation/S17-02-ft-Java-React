package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Specialty;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.ISpecialtyRepo;
import com.nocountry.telemedicina.services.ISpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SpecialtyServiceImpl extends CRUDServiceImpl<Specialty, Long> implements ISpecialtyService {

    @Autowired
    private ISpecialtyRepo repo;

    @Override
    protected IGenericRepo<Specialty, Long> getRepo() {
        return repo;
    }
}
