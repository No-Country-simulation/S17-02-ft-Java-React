package com.nocountry.telemedicina.services;

import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ICRUDService<T,ID> {
    T save(T t);
    T findById(ID id);
    T updateById(ID id, T t);
    void deleteById(ID id);
    Page<T> findAll(int page, int size, String sortField, String sortOrder);

}
