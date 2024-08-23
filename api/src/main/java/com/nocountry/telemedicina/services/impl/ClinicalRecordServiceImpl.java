package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.ClinicalRecord;
import com.nocountry.telemedicina.repository.IClinicalRecordRepo;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.services.IClinicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ClinicalRecordServiceImpl extends CRUDServiceImpl<ClinicalRecord, UUID> implements IClinicalRecordService {

    @Autowired
    private IClinicalRecordRepo repo;

    @Override
    protected IGenericRepo<ClinicalRecord, UUID> getRepo() {
        return repo;
    }
}
