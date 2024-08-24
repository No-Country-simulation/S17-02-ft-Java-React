package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.ProfileMapper;
import com.nocountry.telemedicina.dto.request.ProfileRequestDTO;
import com.nocountry.telemedicina.dto.response.ProfileResponseDTO;
import com.nocountry.telemedicina.exception.NotAuthorizedException;
import com.nocountry.telemedicina.models.Profile;
import com.nocountry.telemedicina.services.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private IProfileService service;

    @Autowired
    private ProfileMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO>findById(@PathVariable("id") UUID id){
        Profile obj = service.findById(id);
        if(obj == null){
            throw new NotAuthorizedException("ID NOT FOUND: " + id);
        }else {
            return new ResponseEntity<>(mapper.toProfileDTO(obj), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ProfileRequestDTO dto){
        Profile obj = service.save(mapper.toProfile(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getProfileId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Profile> update(@RequestBody ProfileRequestDTO dto,@PathVariable("id") UUID id){
        Profile obj = service.updateById(id,mapper.toProfile(dto));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
