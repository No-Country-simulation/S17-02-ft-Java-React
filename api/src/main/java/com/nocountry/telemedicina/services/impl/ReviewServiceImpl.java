package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.models.Review;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.IReviewRepo;
import com.nocountry.telemedicina.services.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReviewServiceImpl extends CRUDServiceImpl<Review, UUID> implements IReviewService {

    @Autowired
    private IReviewRepo repo;

    @Override
    protected IGenericRepo<Review, UUID> getRepo() {
        return repo;
    }
}
