package com.nocountry.telemedicina.repository;

import com.nocountry.telemedicina.models.Profile;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProfileRepo extends IGenericRepo<Profile, UUID> {
    @Query(value = "SELECT * FROM profiles p WHERE p.user_id=:userId", nativeQuery = true)
    Optional<Profile> findByUserId(@Param("userId") UUID userId);
}
