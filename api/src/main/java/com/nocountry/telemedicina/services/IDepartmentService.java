package com.nocountry.telemedicina.services;

import com.nocountry.telemedicina.models.Department;

import java.util.List;


public interface IDepartmentService extends ICRUDService<Department, Long> {

    List<Department> findAll();
}
