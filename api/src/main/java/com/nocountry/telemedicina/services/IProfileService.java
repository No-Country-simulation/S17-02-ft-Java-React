package com.nocountry.telemedicina.services;

import com.nocountry.telemedicina.models.Profile;
import com.nocountry.telemedicina.security.oauth2.user.CurrentUser;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;

import java.util.Optional;
import java.util.UUID;

public interface IProfileService extends ICRUDService<Profile, UUID> {

    Boolean updateAvatar(UUID userId, String result);

    Profile save(Profile profile, @CurrentUser UserPrincipal user);

    Optional<Profile> findById(@CurrentUser UserPrincipal user);
}
