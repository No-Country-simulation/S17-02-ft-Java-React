package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Auditable;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.services.ICRUDService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public abstract class CRUDServiceImpl<T extends Auditable,ID> implements ICRUDService<T,ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Transactional
    @Override
    public T save(T t) {
        if(!t.getActive()){
            t.setActive(true);
        }
        t.setCreatedAt(LocalDateTime.now());
        // Function for set user who created
        //t.setCreateBy();
        return getRepo().save(t);
    }

    @Override
    public T updateById(ID id, T t) {
        if(t.getActive()){
            t.setUpdatedAt(LocalDateTime.now());
            // Function for set user who updated
            //t.setUpdateBy();
            return getRepo().save(t);
        }
        return null;
    }

    @Override
    public T findById(ID id) {
        T t = getRepo().findById(id).orElseThrow();
        if(t.getActive()) {
            return t;
        }
        return null;
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
            //t.setDeleteBy();
        }
    }
}
