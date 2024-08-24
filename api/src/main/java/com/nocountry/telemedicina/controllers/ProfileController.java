package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.ProfileMapper;
import com.nocountry.telemedicina.dto.response.ProfileResponseDTO;
import com.nocountry.telemedicina.exception.NotAuthorizedException;
import com.nocountry.telemedicina.models.Profile;
import com.nocountry.telemedicina.services.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private IProfileService service;

    @Autowired
    private ProfileMapper mapper;

    public ResponseEntity<ProfileResponseDTO>findById(@PathVariable("id") UUID id){
        Profile obj = service.findById(id);
        if(obj == null){
            throw new NotAuthorizedException("ID NOT FOUND: " + id);
        }else {
            return new ResponseEntity<>(mapper.toProfileDTO(obj), HttpStatus.OK);
        }
    }
}
