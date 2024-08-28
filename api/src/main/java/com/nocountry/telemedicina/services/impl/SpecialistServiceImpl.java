package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Specialist;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.ISpecialistRepo;
import com.nocountry.telemedicina.services.ISpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class SpecialistServiceImpl extends CRUDServiceImpl<Specialist, UUID> implements ISpecialistService {

    @Autowired
    private ISpecialistRepo repo;

    @Override
    protected IGenericRepo<Specialist, UUID> getRepo() {
        return repo;
    }

    @Override
    public Page<Specialist> findSpecialistsByDistrict(String districtName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Specialist> list = getRepo().findAllByActiveTrue().stream()
                .filter(t -> t.getProfile().getDistrict()
                        .getDistrictName().toLowerCase().equals(districtName)).toList();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    @Override
    public Page<Specialist> findSpecialistByRangeOfPrices(Double startPrice, Double endPrice, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Specialist> list = getRepo().findAllByActiveTrue().stream()
                .filter(t -> t
                        .getBookingPrice() >= startPrice || t.getBookingPrice() <= endPrice).toList();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    @Override
    public Page<Specialist> findSpecialistBySpeciality(String specialtyName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Specialist> list = getRepo().findAllByActiveTrue().stream()
                .filter(t -> t.getSpecialty()
                        .getSpecialtyName().toLowerCase().equals(specialtyName)).toList();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    @Override
    public Page<Specialist> findSpecialistByReputation(Integer reputation, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Specialist> list = getRepo().findAllByActiveTrue().stream()
                .filter(t -> t.getReputation().equals(reputation)).toList();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    @Override
    public Page<Specialist> findSpecialistByName(String specialistName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Specialist> list = getRepo().findAllByActiveTrue().stream()
                .filter(t -> t.getProfile()
                        .getProfileName().toLowerCase().equals(specialistName)).toList();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    @Override
    public Page<Specialist> findSpecialistByAvailableShifts(LocalDate afterDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Specialist> list = getRepo().findAllByActiveTrue().stream()
                .filter(t -> t.getSchedules().stream().anyMatch(s ->
                        s.getSchedulesDay().isAfter(afterDate) ||
                                s.getSchedulesDay().isEqual(afterDate))).toList();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    @Override
    public Page<Specialist> findSpecialistByClinic(String clinicName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Specialist> list = getRepo().findAllByActiveTrue().stream()
                .filter(t -> t
                        .getClinic().getClinicName().toLowerCase().equals(clinicName)).toList();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    @Override
    public Page<Specialist> findSpecialistByClinicReputation(Integer reputation, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Specialist> list = getRepo().findAllByActiveTrue().stream()
                .filter(t -> t.getClinic().getReputation().equals(reputation)).toList();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }
}
