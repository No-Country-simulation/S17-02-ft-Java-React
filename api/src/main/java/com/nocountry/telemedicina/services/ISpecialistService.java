package com.nocountry.telemedicina.services;

import com.nocountry.telemedicina.models.Specialist;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ISpecialistService extends ICRUDService<Specialist, UUID> {

  Page<Specialist> getFilteredSpecialists(
            String districtName,
            String specialtyName,
            String profileName,
            Integer reputation,
            String clinicName,
            Integer clinicReputation,
            Double minPrice,
            Double maxPrice,
            int page, int size,
            boolean isAscendant,
            String query);
}
