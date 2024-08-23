package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Role;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.IRoleRepo;
import com.nocountry.telemedicina.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class RoleServiceImpl extends CRUDServiceImpl<Role, UUID> implements IRoleService {

    @Autowired
    private IRoleRepo repo;

    @Override
    protected IGenericRepo<Role, UUID> getRepo() {
        return repo;
    }
}
