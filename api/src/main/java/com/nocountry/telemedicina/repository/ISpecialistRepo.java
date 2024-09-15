package com.nocountry.telemedicina.repository;

import com.nocountry.telemedicina.models.Specialist;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ISpecialistRepo extends IGenericRepo<Specialist,UUID>, JpaSpecificationExecutor<Specialist> {
    @Query(value = "SELECT s.active, s.booking_price, s.reputation, s.created_at, s.deleted_at, s.specialty_id, s.updated_at, s.create_by, s.delete_by, s.profile_id, s.specialist_id, s.update_by, s.specialist_code\n" +
            "\tFROM specialists s\n" +
            "\tINNER JOIN profiles p\n" +
            "\tON p.profile_id=s.profile_id\n" +
            "\tINNER JOIN users u\n" +
            "\tON u.user_id=p.user_id\n" +
            "\tWHERE u.user_id=:userId",nativeQuery = true)
    Specialist findByUserId(UUID userId);
}
