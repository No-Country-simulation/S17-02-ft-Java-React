package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Specialist;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.ISpecialistRepo;
import com.nocountry.telemedicina.services.ISpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SpecialistServiceImpl extends CRUDServiceImpl<Specialist, UUID> implements ISpecialistService {

    @Autowired
    private ISpecialistRepo repo;

    @Override
    protected IGenericRepo<Specialist, UUID> getRepo() {
        return repo;
    }
}
