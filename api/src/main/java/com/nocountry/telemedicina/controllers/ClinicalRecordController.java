package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.ClinicalRecordsMapper;
import com.nocountry.telemedicina.dto.request.ClinicalRecordRequestDTO;
import com.nocountry.telemedicina.dto.response.ClinicalRecordResponseDTO;
import com.nocountry.telemedicina.exception.NotAuthorizedException;
import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.ClinicalRecord;
import com.nocountry.telemedicina.services.IClinicalRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clinical-records")
public class ClinicalRecordController {
    @Autowired
    private IClinicalRecordService service;

    @Autowired
    private ClinicalRecordsMapper mapper;

    /**
     * Finds a clinicalRecord by its ID.
     *
     * @param id the ID of the clinicalRecord to find
     * @return the found clinicalRecord
     */
    @Operation(
            summary = "Busca un Registro Clinico por su ID",
            description = "Busca un Registro Clinico.Se requiere el parametro ID del registro clinico",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = ClinicalRecordResponseDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<ClinicalRecordResponseDTO> findById(@PathVariable("id") @Parameter(name = "id", description = "ID del Registro Clinico", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        ClinicalRecord obj = service.findById(id);
        if(obj == null){
            throw new NotAuthorizedException("ID NOT FOUND: " + id);
        }else {
            return new ResponseEntity<>(mapper.toClinicalRecordDTO(obj), HttpStatus.OK);
        }
    }

    /**
     * Creates a new clinicalRecord.
     *
     * @param dto the clinicalRecord data to create
     * @return the created clinicalRecord
     */
    @Operation(
            summary = "Crea un registro clinico",
            description = "Crea un Registro Clinico.Se requiere enviar los parametros descritos a continuación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = ClinicalRecordRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ClinicalRecordRequestDTO dto){
        ClinicalRecord obj = service.save(mapper.toClinicalRecord(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getClinicalRecordId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updates a clinicalRecord by its ID.
     *
     * @param dto the clinicalRecord data to update
     * @param id the ID of the clinicalRecord to update
     * @return the updated clinicalRecord
     */
    @Operation(
            summary = "Actualiza datos de un Registro Clinico por ID",
            description = "Actualiza los datos de un Registro Clinico.Se envia los atributos a actualizar del registro clinico y el ID del Registro Clinico",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ClinicalRecordRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @PutMapping("/update/{id}")
    public ResponseEntity<ClinicalRecord> update(@RequestBody ClinicalRecordRequestDTO dto,@PathVariable("id") @Parameter(name = "id", description = "ID del Registro Clinico", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        ClinicalRecord obj = service.updateById(id,mapper.toClinicalRecord(dto));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * Lists all clinicalRecords in a paginated manner.
     *
     * @param page the page number
     * @param size the page size
     * @return the list of clinicalRecords
     */
    @Operation(
            summary = "Lista todos los Registros Clinicos de forma paginada",
            description = "Lista todos los registros clinicos inscritos en la aplicación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ClinicalRecordResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<ClinicalRecordResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        try {

            List<ClinicalRecordResponseDTO> list = service.findAll(page,size).stream().map(p -> mapper.toClinicalRecordDTO(p)).collect(Collectors.toList());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException("Error al obtener los registros clinicos", e);
        }
    }

    /**
     * Deletes a clinicalRecord by its ID.
     *
     * @param id the ID of the clinicalRecord to delete
     * @return the deleted clinicalRecord
     */
    @Operation(
            summary = "Elimina una Registro Clinico por ID",
            description = "Elimina una Registro Clinico.Se requiere el parametro ID de la registro clinico",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ClinicalRecordResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Parameter(example = "") UUID id){
        ClinicalRecord obj = service.findById(id);
        if(obj == null){
            throw new NotFoundException("ID NOT FOUND: " + id);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
