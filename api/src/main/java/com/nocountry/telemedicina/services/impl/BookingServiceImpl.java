package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Booking;
import com.nocountry.telemedicina.repository.IBookingRepo;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.projection.IBookingProjection;
import com.nocountry.telemedicina.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Service
public class BookingServiceImpl extends CRUDServiceImpl<Booking, UUID> implements IBookingService {

    @Autowired
    private IBookingRepo repo;

    @Override
    protected IGenericRepo<Booking, UUID> getRepo() {
        return repo;
    }

    @Override
    public Page<IBookingProjection> findAllByUserId(UUID id, int page, int size, String sortField, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, getSort(sortField, sortOrder));
        return repo.findAllByUserId(id, pageable);
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