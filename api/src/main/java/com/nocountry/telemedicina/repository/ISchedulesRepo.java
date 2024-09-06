package com.nocountry.telemedicina.repository;

import com.nocountry.telemedicina.models.Schedules;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;


public interface ISchedulesRepo extends IGenericRepo<Schedules, Long> {
    @Query(value = "SELECT *\n" +
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
