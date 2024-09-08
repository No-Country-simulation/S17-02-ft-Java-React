package com.nocountry.telemedicina.repository;

import com.nocountry.telemedicina.models.Schedule;

import jakarta.transaction.Transactional;

import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IScheduleRepo extends IGenericRepo<Schedule, UUID> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE schedules SET  active = false WHERE schedule_config_id = :scheduleConfigId", nativeQuery = true)
    void DeleteScheduleByScheduleConfigId(@Param("scheduleConfigId") Long scheduleConfigId);
}
