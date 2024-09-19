package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.exception.CustomException;
import com.nocountry.telemedicina.exception.InternalServerException;
import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.City;
import com.nocountry.telemedicina.models.Profile;
import com.nocountry.telemedicina.models.User;
import com.nocountry.telemedicina.repository.ICityRepo;
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

    @Autowired
    private ICityRepo cityRepo;

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
    public Profile updateById(UUID userId, Profile profileUpdate) {
       try {
           // buscar el usuario para luego agregarlo al profile. (es requerido)

           User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException(String.format("User not found with id: %s",userId)));
           // buscar el perfil a actualizar, en caso de no tener el perfil creado dara un error.
           Profile profile =  repo.findByUserId(userId).orElseThrow(() -> new NotFoundException(String.format("Perfil no encontrado con el id: %s",userId)));
           // asignar el profile id para poder actualizarlo correctamente (requerido)
           profileUpdate.setProfileId(profile.getProfileId());
           // asignar el user anteriormente buscado(requerido)
           profileUpdate.setUser(user);
           repo.save(profileUpdate);
           return profileUpdate;
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
