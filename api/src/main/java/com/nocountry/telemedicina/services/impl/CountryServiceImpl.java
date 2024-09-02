package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Country;
import com.nocountry.telemedicina.repository.ICountryRepo;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.services.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CountryServiceImpl extends CRUDServiceImpl<Country, Long> implements ICountryService {

    @Autowired
    private ICountryRepo repo;

    @Override
    protected IGenericRepo<Country, Long> getRepo() {
        return repo;
    }
}