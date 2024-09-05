package com.nocountry.telemedicina.services;

import com.nocountry.telemedicina.models.Schedules;
import com.nocountry.telemedicina.security.oauth2.user.CurrentUser;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;

public interface ISchedulesService extends ICRUDService<Schedules, Long> {
    Schedules save(Schedules schedules, @CurrentUser UserPrincipal user);
}
