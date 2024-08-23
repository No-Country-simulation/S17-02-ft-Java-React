package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Profile;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.IProfileRepo;
import com.nocountry.telemedicina.services.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ProfileServiceImpl extends CRUDServiceImpl<Profile, UUID> implements IProfileService {

    @Autowired
    private IProfileRepo repo;

    @Override
    protected IGenericRepo<Profile, UUID> getRepo() {
        return repo;
    }
}
