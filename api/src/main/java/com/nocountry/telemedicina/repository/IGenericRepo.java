package com.nocountry.telemedicina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.web.bind.annotation.PathVariable;

@NoRepositoryBean
public interface IGenericRepo<T,ID> extends JpaRepository<T,ID> {
}
