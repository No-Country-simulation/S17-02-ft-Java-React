package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.District;
import com.nocountry.telemedicina.repository.IDistrictRepo;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.services.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DistrictServiceImpl extends CRUDServiceImpl<District, UUID> implements IDistrictService {

    @Autowired
    private IDistrictRepo repo;

    @Override
    protected IGenericRepo<District, UUID> getRepo() {
        return repo;
    }
}
