package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Profile;
import com.nocountry.telemedicina.models.Specialist;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.IProfileRepo;
import com.nocountry.telemedicina.repository.ISpecialistRepo;
import com.nocountry.telemedicina.repository.specification.SpecialistSpecification;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import com.nocountry.telemedicina.services.ISpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SpecialistServiceImpl extends CRUDServiceImpl<Specialist, UUID> implements ISpecialistService {

    @Autowired
    private ISpecialistRepo repo;

    @Autowired
    private IProfileRepo profileRepo;

    @Override
    protected IGenericRepo<Specialist, UUID> getRepo() {
        return repo;
    }

    @Override
    public Page<Specialist> getFilteredSpecialists(
            String districtName,
            String specialtyName,
            String profileName,
            Integer reputation,
            String clinicName,
            Integer clinicReputation,
            Double minPrice,
            Double maxPrice,
            int page, int size,
            String sortOrder,
            String sortField ) {
        Pageable pageable = PageRequest.of( page, size,
                getSort(sortField,sortOrder));
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

    @Override
    public Specialist save(Specialist specialist, UserPrincipal userPrincipal) {
        Profile profile = profileRepo.findByUserId(userPrincipal.getId()).orElseThrow();
        specialist.setProfile(profile);
        return repo.save(specialist);
    }

    private static String getQueryString(String query) {
        return switch (query) {
            case "price" -> "bookingPrice";
            case "specialty" -> "specialty.specialtyName";
            case "location" -> "profile.district.districtName";
            case "clinic" -> "clinic.clinicName";
            default -> "reputation";
        };
    }

    private Sort getSort(String sortField, String sortOrder) {
        Sort sort = Sort.by(getQueryString(sortField));
        if (sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return sort;
    }
}
