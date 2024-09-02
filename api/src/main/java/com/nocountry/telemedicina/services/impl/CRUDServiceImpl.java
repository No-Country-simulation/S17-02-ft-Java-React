package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Auditable;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.services.ICRUDService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

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
    public Page<T> findAll(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<T> list = getRepo().findAllByActiveTrue();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
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
}
