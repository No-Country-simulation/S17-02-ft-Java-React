package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.ClinicalHistory;
import com.nocountry.telemedicina.repository.IClinicalHistoryRepo;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.services.IClinicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ClinicalHistoryServiceImpl extends CRUDServiceImpl<ClinicalHistory, UUID> implements IClinicalHistoryService {

    @Autowired
    private IClinicalHistoryRepo repo;

    @Override
    protected IGenericRepo<ClinicalHistory, UUID> getRepo() {
        return repo;
    }
}
