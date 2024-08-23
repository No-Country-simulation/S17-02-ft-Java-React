package com.nocountry.telemedicina.repository;

import com.nocountry.telemedicina.models.Auditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface IGenericRepo<T extends Auditable,ID> extends JpaRepository<T,ID> {

    List<T> findAllByActiveTrue();
}
