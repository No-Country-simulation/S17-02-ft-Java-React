package com.nocountry.telemedicina.services;

import com.nocountry.telemedicina.models.Schedules;
import com.nocountry.telemedicina.security.oauth2.user.CurrentUser;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISchedulesService extends ICRUDService<Schedules, Long> {
    Schedules save(Schedules schedules, @CurrentUser UserPrincipal user);
    Page<Schedules> findAllByUserId(@CurrentUser UserPrincipal user,int page,int size,String sortField,String sortOrder);
    //List<Schedules> saveAll(Schedules schedules);
}
