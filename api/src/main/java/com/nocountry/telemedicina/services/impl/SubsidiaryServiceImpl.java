package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Subsidiary;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.ISubsidiaryRepo;
import com.nocountry.telemedicina.services.ISubsidiaryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class SubsidiaryServiceImpl extends CRUDServiceImpl<Subsidiary, UUID> implements ISubsidiaryService {

    @Autowired
    private ISubsidiaryRepo repo;

    @Override
    protected IGenericRepo<Subsidiary, UUID> getRepo() {
        return repo;
    }
}
