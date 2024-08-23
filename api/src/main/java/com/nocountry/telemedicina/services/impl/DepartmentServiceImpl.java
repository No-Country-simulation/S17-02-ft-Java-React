package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Department;
import com.nocountry.telemedicina.repository.IDepartmentRepo;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.services.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DepartmentServiceImpl extends CRUDServiceImpl<Department, UUID> implements IDepartmentService {

    @Autowired
    private IDepartmentRepo repo;

    @Override
    protected IGenericRepo<Department, UUID> getRepo() {
        return repo;
    }
}
