package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Specialist;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.ISpecialistRepo;
import com.nocountry.telemedicina.repository.specification.SpecialistSpecification;
import com.nocountry.telemedicina.services.ISpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
    public Page<Specialist> getFilteredSpecialists(String districtName,
                                                   String specialtyName,
                                                   String profileName,
                                                   Integer reputation,
                                                   String clinicName,
                                                   Integer clinicReputation,
                                                   Double minPrice,
                                                   Double maxPrice,
                                                   int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Specialist> spec = Specification
                .where(SpecialistSpecification.hasDistrictName(districtName))
                .and(SpecialistSpecification.hasSpecialtyName(specialtyName))
                .and(SpecialistSpecification.hasProfileName(profileName))
                .and(SpecialistSpecification.hasReputation(reputation))
                .and(SpecialistSpecification.hasClinicName(clinicName))
                .and(SpecialistSpecification.hasClinicReputation(clinicReputation))
                .and(SpecialistSpecification.hasRangeOfPrices(minPrice, maxPrice));
        return repo.findAll(spec, pageable);
    }
}
