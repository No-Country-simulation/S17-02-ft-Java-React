package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.cloudinary.CloudinaryService;
import com.nocountry.telemedicina.config.mapper.ProfileMapper;
import com.nocountry.telemedicina.config.mapper.UserMapper;
import com.nocountry.telemedicina.dto.request.ProfileRequestDTO;
import com.nocountry.telemedicina.dto.response.ProfileResponseDTO;
import com.nocountry.telemedicina.exception.NotAuthorizedException;
import com.nocountry.telemedicina.models.Profile;
import com.nocountry.telemedicina.models.User;
import com.nocountry.telemedicina.security.oauth2.user.CurrentUser;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import com.nocountry.telemedicina.services.IProfileService;
import com.nocountry.telemedicina.services.IUserService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

/**
 * The type Profile controller.
 */
@Tag(name = "API de Perfiles", description = "Se puede crear,buscar perfil y actualizar perfil por idProfile")
@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private IProfileService service;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ProfileMapper mapper;


    /**
     * Find by id response entity.
     *
     * @param user the user
     * @return the response entity
     */
    @Operation(summary = "Busca un Perfil por su ID", description = "Busca un Perfil.Se requiere el parametro ID del perfil", tags = {})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/my-profile")
    public ResponseEntity<ProfileResponseDTO> findById(@CurrentUser UserPrincipal user) {
        Profile obj = service.findById(user).orElseThrow();
        return new ResponseEntity<>(mapper.toProfileDTO(obj), HttpStatus.OK);
    }

    /**
     * Creates a new profile.
     *
     * @param dto  the profile data to create
     * @param user the user
     * @return the created profile
     */
    @Operation(summary = "Crea un perfil", description = "Crea un Perfil.Se requiere enviar los parametros descritos a continuación", tags = {})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileRequestDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Profile> save(@RequestBody ProfileRequestDTO dto,@CurrentUser UserPrincipal user) {
        Profile obj = service.save(mapper.toProfile(dto),user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getProfileId())
                .toUri();
        return ResponseEntity.status(201).body(obj);
    }

    /**
     * Updates a profile by its ID.
     *
     * @param dto the profile data to update
     * @param id  the ID of the profile to update
     * @return the updated profile
     */
    @Operation(summary = "Actualiza datos de un Perfil por ID", description = "Actualiza los datos de un Perfil.Se envia los atributos a actualizar del perfil y el ID del Perfil", tags = {})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileRequestDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

//    @PutMapping("/update/{id}")
//    public ResponseEntity<Profile> update(@RequestBody ProfileRequestDTO dto,
//            @PathVariable("id") @Parameter(name = "id", description = "ID del Perfil", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id) {
//        Profile obj = service.updateById(id, mapper.toProfile(dto));
//        return new ResponseEntity<>(obj, HttpStatus.OK);
//    }
    @PutMapping("/update/")
    public ResponseEntity<Profile> update(@RequestBody ProfileRequestDTO dto,@CurrentUser UserPrincipal user ) {
        Profile obj = service.updateById(user.getId(), mapper.toProfile(dto));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * Update avatar response entity.
     *
     * @param file          the file
     * @param userPrincipal the user principal
     * @return the response entity
     */
    @PostMapping("/update-avatar")
    public ResponseEntity<?> updateAvatar(@RequestParam("file") MultipartFile file,
            @CurrentUser UserPrincipal userPrincipal) {
        String result = cloudinaryService.uploadAvatar(file, userPrincipal.getUsername());
        Boolean response = service.updateAvatar(userPrincipal.getId(), result);
        if (response) {
            return ResponseEntity.status(200).body(result);
        } else {
            return ResponseEntity.status(400).body("Hubo un error al cargar el avatar");
        }
    }

    /**
     * Create avatar url response entity.
     *
     * @param file          the file
     * @param userPrincipal the user principal
     * @return the response entity
     */
    @PostMapping("/create-avatar-url")
    public ResponseEntity<?> createAvatarUrl(@RequestParam("file") MultipartFile file,
            @CurrentUser UserPrincipal userPrincipal) {
        String result = cloudinaryService.uploadAvatar(file, userPrincipal.getUsername());
        return ResponseEntity.status(201).body(result);
    }
}