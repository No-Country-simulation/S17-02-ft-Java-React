package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.exception.CustomException;
import com.nocountry.telemedicina.exception.InternalServerException;
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
    @Transactional
    @Override
    public Profile save(Profile profile, UserPrincipal user) {
        User userNew = userRepo.findById(user.getId()).orElseThrow(() -> new NotFoundException(String.format("Perfil no encontrado con el id: %s",user.getId())));
        profile.setUser(userNew);
        return repo.save(profile);
    }

    @Transactional
    @Override
    public Profile updateById(UUID userId, Profile profile) {
       try {
           Profile profile1 =  repo.findByUserId(userId).orElseThrow(() -> new NotFoundException(String.format("Perfil no encontrado con el id: %s",userId)));
           profile.setProfileId(profile1.getProfileId());
           repo.save(profile);
           return profile;
       }catch (Exception ex) {
           // en caso de un error al persistir la data o al buscarla en la db enviar un custom exception
           throw new CustomException(500,ex.getMessage());
       }
    }

    @Override
    public Optional<Profile> findById(UserPrincipal user) {
        return repo.findByUserId(user.getId());
    }
}
