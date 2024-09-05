package com.nocountry.telemedicina.repository;

import com.nocountry.telemedicina.models.Specialist;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ISpecialistRepo extends IGenericRepo<Specialist,UUID>, JpaSpecificationExecutor<Specialist> {
    
}
