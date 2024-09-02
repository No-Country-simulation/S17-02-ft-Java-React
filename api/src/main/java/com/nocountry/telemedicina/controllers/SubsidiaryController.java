package com.nocountry.telemedicina.controllers;


import com.nocountry.telemedicina.config.mapper.SubsidiaryMapper;
import com.nocountry.telemedicina.dto.request.SubsidiaryRequestDTO;
import com.nocountry.telemedicina.dto.response.SubsidiaryResponseDTO;
import com.nocountry.telemedicina.exception.NotAuthorizedException;
import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.Subsidiary;
import com.nocountry.telemedicina.services.ISubsidiaryService;
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
 * SubsidiaryController class handles all the operations related to subsidiary.
 */
@Tag(name = "API de Sedes de las clinicas", description = "Se puede crear,buscar, eliminar y actualizar sedes")
@RestController
@RequestMapping("/api/subsidiary")
public class SubsidiaryController {

    @Autowired
    private ISubsidiaryService service;

    @Autowired
    private SubsidiaryMapper mapper;

    /**
     * Finds a subsidiary by its ID.
     *
     * @param id the ID of the subsidiary to find
     * @return the found subsidiary
     */
    @Operation(
            summary = "Busca una Sede por su ID",
            description = "Busca una Sede.Se requiere el parametro ID de la sede",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = SubsidiaryResponseDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<SubsidiaryResponseDTO> findById(@PathVariable("id") @Parameter(name = "id", description = "ID de la Sede", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        Subsidiary obj = service.findById(id);
        if(obj == null){
            throw new NotAuthorizedException("ID NOT FOUND: " + id);
        }else {
            return new ResponseEntity<>(mapper.toSubsidiaryDTO(obj), HttpStatus.OK);
        }
    }

    /**
     * Creates a new subsidiary.
     *
     * @param dto the subsidiary data to create
     * @return the created subsidiary
     */
    @Operation(
            summary = "Crea una sede",
            description = "Crea una Sede.Se requiere enviar los parametros descritos a continuación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = SubsidiaryRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody SubsidiaryRequestDTO dto){
        Subsidiary obj = service.save(mapper.toSubsidiary(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getSubsidiaryId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updates a subsidiary by its ID.
     *
     * @param dto the subsidiary data to update
     * @param id the ID of the subsidiary to update
     * @return the updated subsidiary
     */
    @Operation(
            summary = "Actualiza datos de una Sede por ID",
            description = "Actualiza los datos de una Sede.Se envia los atributos a actualizar de la sede y el ID de la Sede",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =SubsidiaryRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @PutMapping("/update/{id}")
    public ResponseEntity<Subsidiary> update(@RequestBody SubsidiaryRequestDTO dto,@PathVariable("id") @Parameter(name = "id", description = "ID de la Sede", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        Subsidiary obj = service.updateById(id,mapper.toSubsidiary(dto));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * Lists all subsidiary in a paginated manner.
     *
     * @param page the page number
     * @param size the page size
     * @return the list of subsidiary
     */
    @Operation(
            summary = "Lista todas las Sedes de forma paginada",
            description = "Lista todas las sedes inscritas en la aplicación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =SubsidiaryResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<SubsidiaryResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        try {

            List<SubsidiaryResponseDTO> list = service.findAll(page,size).stream().map(p -> mapper.toSubsidiaryDTO(p)).collect(Collectors.toList());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException("Error al obtener las sedes", e);
        }
    }

    /**
     * Deletes a subsidiary by its ID.
     *
     * @param id the ID of the subsidiary to delete
     * @return the deleted subsidiary
     */
    @Operation(
            summary = "Elimina una Sede por ID",
            description = "Elimina una Sede.Se requiere el parametro ID de la sede",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =SubsidiaryResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Parameter(example = "") UUID id){
        Subsidiary obj = service.findById(id);
        if(obj == null){
            throw new NotFoundException("ID NOT FOUND: " + id);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
