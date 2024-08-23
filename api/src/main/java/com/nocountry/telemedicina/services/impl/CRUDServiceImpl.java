package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Auditable;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.services.ICRUDService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public abstract class CRUDServiceImpl<T extends Auditable,ID> implements ICRUDService<T,ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Transactional
    @Override
    public T save(T t) {
        if(!t.getActive()){
            t.setActive(true);
            t.setCreatedAt(LocalDateTime.now());
        }
        // Function for set user who created
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
        T t = getRepo().findById(id).orElseThrow();
        if(t.getActive()) {
            t.setActive(false);
        }
    }
}
