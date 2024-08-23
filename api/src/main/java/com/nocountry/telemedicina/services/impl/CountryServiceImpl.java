package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Country;
import com.nocountry.telemedicina.repository.ICountryRepo;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.services.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class CountryServiceImpl extends CRUDServiceImpl<Country, UUID> implements ICountryService {

    @Autowired
    private ICountryRepo repo;

    @Override
    protected IGenericRepo<Country, UUID> getRepo() {
        return repo;
    }
}
