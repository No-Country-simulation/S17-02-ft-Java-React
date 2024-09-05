package com.nocountry.telemedicina.repository;

import com.nocountry.telemedicina.models.User;
<<<<<<< HEAD
=======
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
>>>>>>> 5e5f8487634c5773f91a66eeb5e90c92024b3338
import java.util.UUID;

public interface IUserRepo extends IGenericRepo<User, UUID> {
    @Query(value = "SELECT * FROM users u WHERE u.username=:username", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);

    @Modifying
    @Query(value = "INSERT INTO user_role (role_id, user_id) VALUES (:roleId, :userId)", nativeQuery = true)
    void assignRoleToUser(@Param("userId") UUID userId, @Param("roleId") Long roleId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET create_by = :id WHERE user_id = :id", nativeQuery = true)
    int updateCreateByWithUserId(UUID id);
}