package com.nocountry.telemedicina.services;

import com.nocountry.telemedicina.models.City;

import java.util.List;


public interface ICityService extends ICRUDService<City, Long>{

    List<City> findAll();
}