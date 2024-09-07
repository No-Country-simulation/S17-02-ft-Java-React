package com.nocountry.telemedicina.repository;

import com.nocountry.telemedicina.models.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IProfileRepo extends IGenericRepo<Profile, UUID> {
    @Query(value = "SELECT * FROM profiles p WHERE p.user_id=:userId", nativeQuery = true)
    Optional<Profile> findByUserId(@Param("userId") UUID userId);

}
