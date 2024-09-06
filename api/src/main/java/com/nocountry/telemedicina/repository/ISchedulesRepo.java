package com.nocountry.telemedicina.repository;

import com.nocountry.telemedicina.models.Schedules;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;


public interface ISchedulesRepo extends IGenericRepo<Schedules, Long> {
    @Query(value = "SELECT sc.active, sc.schedules_day, sc.schedules_duration, sc.schedules_end, sc.schedules_repeat, sc.schedules_rest, sc.schedules_start, sc.created_at, sc.deleted_at, sc.schedules_id, sc.updated_at, sc.create_by, sc.delete_by, sc.specialist_id, sc.update_by\n" +
            "\tFROM schedules sc\n" +
            "\tINNER JOIN specialists s\n" +
            "\tON s.specialist_id=sc.specialist_id\n" +
            "\tINNER JOIN profiles p\n" +
            "\tON p.profile_id=s.profile_id\n" +
            "\tINNER JOIN users u\n" +
            "\tON u.user_id=p.user_id\n" +
            "\tWHERE u.user_id=:userId",nativeQuery = true)
    Page<Schedules> findAllByUserId(UUID userId, Pageable pageable);
}
