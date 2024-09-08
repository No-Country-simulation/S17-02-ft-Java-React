package com.nocountry.telemedicina.services;

import com.nocountry.telemedicina.models.ScheduleConfig;
import com.nocountry.telemedicina.security.oauth2.user.CurrentUser;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ISchedulesConfigService extends ICRUDService<ScheduleConfig, Long> {
    ScheduleConfig save(ScheduleConfig scheduleConfig, @CurrentUser UserPrincipal user);
    Page<ScheduleConfig> findAllByUserId(@CurrentUser UserPrincipal user, int page, int size, String sortField, String sortOrder);
    void createSchedules(ScheduleConfig scheduleConfig, UUID userId);
}
