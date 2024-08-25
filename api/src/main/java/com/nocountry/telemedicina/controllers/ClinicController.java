package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.ClinicMapper;
import com.nocountry.telemedicina.dto.request.ClinicRequestDTO;
import com.nocountry.telemedicina.dto.response.ClinicResponseDTO;
import com.nocountry.telemedicina.exception.NotAuthorizedException;
import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.Clinic;
import com.nocountry.telemedicina.services.IClinicService;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * ClinicController class handles all the operations related to clinics.
 */
@Tag(name = "API de Clinicas", description = "Se puede crear,buscar clinica y actualizar clinica por idClinic")
@RestController
@RequestMapping("/api/clinics")
public class ClinicController {

    @Autowired
    private IClinicService service;

    @Autowired
    private ClinicMapper mapper;

    /**
     * Finds a clinic by its ID.
     *
     * @param id the ID of the clinic to find
     * @return the found clinic
     */
    @Operation(
            summary = "Busca un Clinica por su ID",
            description = "Busca un Clinica.Se requiere el parametro ID del clinica",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = ClinicResponseDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<ClinicResponseDTO> findById(@PathVariable("id") @Parameter(name = "id", description = "ID del Clinica", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        Clinic obj = service.findById(id);
        if(obj == null){
            throw new NotAuthorizedException("ID NOT FOUND: " + id);
        }else {
            return new ResponseEntity<>(mapper.toClinicDTO(obj), HttpStatus.OK);
        }
    }

    /**
     * Creates a new clinic.
     *
     * @param dto the clinic data to create
     * @return the created clinic
     */
    @Operation(
            summary = "Crea un clinica",
            description = "Crea un Clinica.Se requiere enviar los parametros descritos a continuación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = ClinicRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ClinicRequestDTO dto){
        Clinic obj = service.save(mapper.toClinic(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getClinicId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updates a clinic by its ID.
     *
     * @param dto the clinic data to update
     * @param id the ID of the clinic to update
     * @return the updated clinic
     */
    @Operation(
            summary = "Actualiza datos de un Clinica por ID",
            description = "Actualiza los datos de un Clinica.Se envia los atributos a actualizar del clinica y el ID del Clinica",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ClinicRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @PutMapping("/update/{id}")
    public ResponseEntity<Clinic> update(@RequestBody ClinicRequestDTO dto,@PathVariable("id") @Parameter(name = "id", description = "ID del Clinica", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        Clinic obj = service.updateById(id,mapper.toClinic(dto));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * Lists all clinics in a paginated manner.
     *
     * @param page the page number
     * @param size the page size
     * @return the list of clinics
     */
    @Operation(
            summary = "Lista todos las Clinicas de forma paginada",
            description = "Lista todos las clinicas inscritos en la aplicación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ClinicResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<ClinicResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        try {

            List<ClinicResponseDTO> list = service.findAll(page,size).stream().map(p -> mapper.toClinicDTO(p)).collect(Collectors.toList());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException("Error al obtener clinicas", e);
        }
    }

    /**
     * Deletes a clinic by its ID.
     *
     * @param id the ID of the clinic to delete
     * @return the deleted clinic
     */
    @Operation(
            summary = "Elimina una Clinica por ID",
            description = "Elimina una Clinica.Se requiere el parametro ID de la clinica",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ClinicResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Parameter(example = "") UUID id){
        Clinic obj = service.findById(id);
        if(obj == null){
            throw new NotFoundException("ID NOT FOUND: " + id);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}