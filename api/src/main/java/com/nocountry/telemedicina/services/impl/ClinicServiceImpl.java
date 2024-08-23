package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Clinic;
import com.nocountry.telemedicina.repository.IClinicRepo;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.services.IClinicService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ClinicServiceImpl extends CRUDServiceImpl<Clinic, UUID> implements IClinicService {

    @Autowired
    private IClinicRepo repo;

    @Override
    protected IGenericRepo<Clinic, UUID> getRepo() {
        return repo;
    }
}
