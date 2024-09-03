package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.User;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.IUserRepo;
import com.nocountry.telemedicina.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl extends CRUDServiceImpl<User, UUID> implements IUserService {

    @Autowired
    private IUserRepo repo;

    @Override
    protected IGenericRepo<User, UUID> getRepo() {
        return repo;
    }
}
