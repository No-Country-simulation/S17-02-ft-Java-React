package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Booking;
import com.nocountry.telemedicina.repository.IBookingRepo;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookingServiceImpl extends CRUDServiceImpl<Booking, UUID> implements IBookingService {

    @Autowired
    private IBookingRepo repo;

    @Override
    protected IGenericRepo<Booking, UUID> getRepo() {
        return repo;
    }
}
