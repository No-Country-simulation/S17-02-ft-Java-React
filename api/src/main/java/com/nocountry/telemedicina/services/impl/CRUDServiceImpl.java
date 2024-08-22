package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Auditable;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.services.ICRUDService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
public abstract class CRUDServiceImpl<T extends Auditable,ID> implements ICRUDService<T,ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Transactional
    @Override
    public T save(T t) {
        t.setActive(true);
        t.setCreatedAt(LocalDateTime.now());
        //t.setCreateBy();
        return getRepo().save(t);
    }

    @Override
    public T findById(ID id) {
        return getRepo().findById(id).orElseThrow();
    }

    @Override
    public Page<T> findAll(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        return getRepo().findAll(pageable);
    }

    @Transactional
    @Override
    public void deleteById(ID id) {
        //t.setActive()
    }
}
