package com.nocountry.telemedicina.repository;

import com.nocountry.telemedicina.models.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

<<<<<<< HEAD
public interface IRoleRepo extends IGenericRepo<Role, Long> {
=======
import java.util.UUID;

public interface IRoleRepo extends IGenericRepo<Role, UUID> {
    @Query(value = "SELECT * FROM roles r WHERE r.name_role=:roleName", nativeQuery = true)
    Role findRoleByroleName(@Param("roleName") String roleName);
>>>>>>> 5e5f8487634c5773f91a66eeb5e90c92024b3338
}
