package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.Profile;
import com.nocountry.telemedicina.models.User;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.IProfileRepo;
import com.nocountry.telemedicina.repository.IUserRepo;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import com.nocountry.telemedicina.services.IProfileService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileServiceImpl extends CRUDServiceImpl<Profile, UUID> implements IProfileService {
    @Autowired
    private IProfileRepo repo;

    @Autowired
    private IUserRepo userRepo;

    @Override
    protected IGenericRepo<Profile, UUID> getRepo() {
        return repo;
    }



    @Override
    @Transactional
    public Boolean updateAvatar(UUID userId, String result) {
        Profile profile = repo.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Profile not found with id: " + userId));
        profile.setAvatarUrl(result);
        repo.save(profile);
        return true;
    }

    @Override
    public Profile save(Profile profile, UserPrincipal user) {
        User userNew = userRepo.findById(user.getId()).orElseThrow();
        profile.setUser(userNew);
        return repo.save(profile);
    }

    @Override
    public Optional<Profile> findById(UserPrincipal user) {
        return repo.findByUserId(user.getId());
    }
}
