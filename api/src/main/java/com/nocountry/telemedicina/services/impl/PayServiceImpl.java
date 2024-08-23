package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Pay;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.IPayRepo;
import com.nocountry.telemedicina.services.IPayService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class PayServiceImpl extends CRUDServiceImpl<Pay, UUID> implements IPayService {

    @Autowired
    private IPayRepo repo;

    @Override
    protected IGenericRepo<Pay, UUID> getRepo() {
        return repo;
    }
}
