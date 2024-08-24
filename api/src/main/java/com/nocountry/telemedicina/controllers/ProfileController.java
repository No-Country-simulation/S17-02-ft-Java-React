package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.ProfileMapper;
import com.nocountry.telemedicina.dto.request.ProfileRequestDTO;
import com.nocountry.telemedicina.dto.response.ProfileResponseDTO;
import com.nocountry.telemedicina.exception.NotAuthorizedException;
import com.nocountry.telemedicina.models.Profile;
import com.nocountry.telemedicina.services.IProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

/**
 * ProfileController class handles all the operations related to profiles.
 */
@Tag(name = "API de Perfiles", description = "Se puede crear,buscar perfil y actualizar perfil por idProfile")
@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private IProfileService service;

    @Autowired
    private ProfileMapper mapper;

    /**
     * Finds a profile by its ID.
     *
     * @param id the ID of the profile to find
     * @return the found profile
     */
    @Operation(
            summary = "Busca un Perfil por su ID",
            description = "Busca un Perfil.Se requiere el parametro ID del perfil",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ProfileResponseDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> findById(@PathVariable("id") @Parameter(name = "id", description = "ID del Perfil", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        Profile obj = service.findById(id);
        if(obj == null){
            throw new NotAuthorizedException("ID NOT FOUND: " + id);
        }else {
            return new ResponseEntity<>(mapper.toProfileDTO(obj), HttpStatus.OK);
        }
    }

    /**
     * Creates a new profile.
     *
     * @param dto the profile data to create
     * @return the created profile
     */
    @Operation(
            summary = "Crea un perfil",
            description = "Crea un Perfil.Se requiere enviar los parametros descritos a continuación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ProfileRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ProfileRequestDTO dto){
        Profile obj = service.save(mapper.toProfile(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getProfileId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updates a profile by its ID.
     *
     * @param dto the profile data to update
     * @param id the ID of the profile to update
     * @return the updated profile
     */
    @Operation(
            summary = "Actualiza datos de un Perfil por ID",
            description = "Actualiza los datos de un Perfil.Se envia los atributos a actualizar del perfil y el ID del Perfil",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ProfileRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @PutMapping("/update/{id}")
    public ResponseEntity<Profile> update(@RequestBody ProfileRequestDTO dto,@PathVariable("id") @Parameter(name = "id", description = "ID del Perfil", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        Profile obj = service.updateById(id,mapper.toProfile(dto));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}