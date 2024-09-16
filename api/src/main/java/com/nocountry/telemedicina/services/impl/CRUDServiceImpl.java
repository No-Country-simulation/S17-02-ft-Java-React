package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Auditable;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.services.ICRUDService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public abstract class CRUDServiceImpl<T extends Auditable,ID> implements ICRUDService<T,ID> {

    protected abstract IGenericRepo<T , ID> getRepo();

    @Transactional
    @Override
    public T save(T t) {
        if(!t.getActive()) {
            t.setActive(true);
        }
        t.setCreatedAt(LocalDateTime.now());
        // Function for set user who created
        //t.setCreateBy();
        return getRepo().save(t);
    }

    @Transactional
    @Override
    public T updateById(ID id, T t) {
        T old_t = getRepo().findById(id).orElseThrow();
        old_t = t;
        if(t.getActive()){
            old_t.setUpdatedAt(LocalDateTime.now());
            // Function for set user who updated
            //old_t.setUpdateBy();
        }
        return getRepo().save(old_t);
    }

    @Override
    public T findById(ID id) {
        return getRepo().findById(id).orElseThrow();
    }

    @Override
    public Page<T> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, String sortField, @RequestParam(defaultValue = "desc") String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, getSort(sortField, sortOrder));
        return getRepo().findAll(pageable);
    }

    @Transactional
    @Override
    public void deleteById(ID id) {
        T t = getRepo().findById(id).orElseThrow();
        if(t.getActive()) {
            t.setActive(false);
        }
        getRepo().save(t);
    }

    private Sort getSort(String sortField, String sortOrder) {
        Sort sort = Sort.by(sortField);
        if (sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return sort;
    }
}
