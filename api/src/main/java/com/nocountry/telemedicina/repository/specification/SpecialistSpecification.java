package com.nocountry.telemedicina.repository.specification;

import com.nocountry.telemedicina.models.Specialist;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class SpecialistSpecification {

    public static Specification<Specialist> hasDistrictName(String districtName){
        return ((root, query, criteriaBuilder) ->
                districtName != null ? criteriaBuilder
                        .like(root.get("profile.district.districtName"),"%"+districtName+"%") : null);
    }

    public static Specification<Specialist> hasSpecialtyName(String specialtyName) {
        return (root, query, criteriaBuilder) ->
                specialtyName != null ? criteriaBuilder
                        .equal(root.get("specialty.specialtyName"), specialtyName) : null;
    }

    public static Specification<Specialist> hasProfileName(String profileName) {
        return (root, query, criteriaBuilder) ->
                profileName != null ? criteriaBuilder
                        .equal(root.get("profile.profileName"), profileName) : null;
    }

    public static Specification<Specialist> hasAvailableShifts(LocalDate scheduleDay) {
        return (root, query, criteriaBuilder) ->
                scheduleDay != null ? criteriaBuilder
                        .greaterThanOrEqualTo(root.join("schedules")
                                .get("schedulesDay"), scheduleDay) : null;
    }

    public static Specification<Specialist> hasReputation(Integer reputation) {
        return (root, query, criteriaBuilder) ->
                reputation != null ? criteriaBuilder
                        .equal(root.get("reputation"), reputation) : null;
    }

    public static Specification<Specialist> hasClinicName(String clinicName) {
        return (root, query, criteriaBuilder) ->
                clinicName != null ? criteriaBuilder
                        .equal(root.get("clinic.clinicName"), clinicName) : null;
    }

    public static Specification<Specialist> hasClinicReputation(Integer clinicReputation) {
        return (root, query, criteriaBuilder) ->
                clinicReputation != null ? criteriaBuilder
                        .equal(root.get("clinic.reputation"), clinicReputation) : null;
    }

    public static Specification<Specialist> hasRangeOfPrices(Double minPrice, Double maxPrice){
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null && null == maxPrice) {
                return null;
            } else if (minPrice != null && maxPrice != null) {
                return criteriaBuilder.between(root.get("bookingPrice"), minPrice, maxPrice);
            } else if (minPrice != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("bookingPrice"), minPrice);
            } else {
                return criteriaBuilder.lessThanOrEqualTo(root.get("bookingPrice"), maxPrice);
            }
        };
    }
}