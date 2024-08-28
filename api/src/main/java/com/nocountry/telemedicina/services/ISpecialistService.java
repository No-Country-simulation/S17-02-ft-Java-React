package com.nocountry.telemedicina.services;

import com.nocountry.telemedicina.models.Specialist;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.UUID;

public interface ISpecialistService extends ICRUDService<Specialist, UUID> {
    Page<Specialist> findSpecialistsByDistrict(String districtName, int page, int size);
    Page<Specialist> findSpecialistByRangeOfPrices(Double startPrice, Double endPrice, int page, int size);
    Page<Specialist> findSpecialistBySpeciality(String specialtyName, int page, int size);
    Page<Specialist> findSpecialistByReputation(Integer reputation, int page, int size);
    Page<Specialist> findSpecialistByName(String specialistName, int page, int size);
    Page<Specialist> findSpecialistByAvailableShifts(LocalDate afterDate, int page, int size);
    Page<Specialist> findSpecialistByClinic(String clinicName, int page, int size);
    Page<Specialist> findSpecialistByClinicReputation(Integer reputation, int page, int size);
}
