package com.nocountry.telemedicina.repository;

import com.nocountry.telemedicina.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IRoleRepo extends IGenericRepo<Role, Long> {
}
