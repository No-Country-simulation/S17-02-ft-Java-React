package com.nocountry.telemedicina.repository;

import com.nocountry.telemedicina.models.Schedule;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IScheduleRepo extends IGenericRepo<Schedule, UUID> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE schedules SET  active = false WHERE schedule_config_id = :scheduleConfigId", nativeQuery = true)
    void DeleteScheduleByScheduleConfigId(@Param("scheduleConfigId") Long scheduleConfigId);

    @Query(value = "SELECT * FROM schedules s WHERE s.date =:date AND s.active = true AND s.specialist_id = :specialistId", nativeQuery = true)
    List<Schedule> findSchedulesByDate(@Param("date") String date, @Param("specialistId") UUID specialistId);

    @Query(value = "SELECT * FROM schedules s WHERE s.specialist_id = :specialistId AND s.active = true", nativeQuery = true)
    List<Schedule> findSchedulesBySpecialistId(@Param("specialistId") UUID specialistId);
}
