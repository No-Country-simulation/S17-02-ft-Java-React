package com.nocountry.telemedicina.services;

import com.nocountry.telemedicina.models.Specialist;
import com.nocountry.telemedicina.security.oauth2.user.CurrentUser;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
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

  Specialist save(Specialist specialist,@CurrentUser UserPrincipal userPrincipal);
}
