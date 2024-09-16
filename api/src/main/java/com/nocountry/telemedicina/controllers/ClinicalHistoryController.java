package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.ClinicalHistoryMapper;
import com.nocountry.telemedicina.dto.request.ClinicalHistoryRequestDTO;
import com.nocountry.telemedicina.dto.response.ClinicalHistoryResponseDTO;
import com.nocountry.telemedicina.exception.NotAuthorizedException;
import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.ClinicalHistory;
import com.nocountry.telemedicina.services.IClinicalHistoryService;
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

@Tag(name = "Api de Historias Clinicas", description = "Se puede crear,buscar, listar y actualizar historias clinicas")
@RestController
@RequestMapping("/api/clinicalHistory-histories")
public class ClinicalHistoryController {
    @Autowired
    private IClinicalHistoryService service;

    @Autowired
    private ClinicalHistoryMapper mapper;

    /**
     * Finds a clinicalHistory by its ID.
     *
     * @param id the ID of the clinicalHistory to find
     * @return the found clinicalHistory
     */
    @Operation(
            summary = "Busca un Historial Clinico por su ID",
            description = "Busca un Historial Clinico.Se requiere el parametro ID del historial clinico",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = ClinicalHistoryResponseDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<ClinicalHistoryResponseDTO> findById(@PathVariable("id") @Parameter(name = "id", description = "ID del Historial Clinico", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        ClinicalHistory obj = service.findById(id);
        if(obj == null){
            throw new NotAuthorizedException("ID NOT FOUND: " + id);
        }else {
            return new ResponseEntity<>(mapper.toClinicalHistoryDTO(obj), HttpStatus.OK);
        }
    }

    /**
     * Creates a new clinicalHistory.
     *
     * @param dto the clinicalHistory data to create
     * @return the created clinicalHistory
     */
    @Operation(
            summary = "Crea un historial clinico",
            description = "Crea un Historial Clinico.Se requiere enviar los parametros descritos a continuación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = ClinicalHistoryRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ClinicalHistoryRequestDTO dto){
        ClinicalHistory obj = service.save(mapper.toClinicalHistory(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getClinicalHistoryId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updates a clinicalHistory by its ID.
     *
     * @param dto the clinicalHistory data to update
     * @param id the ID of the clinicalHistory to update
     * @return the updated clinicalHistory
     */
    @Operation(
            summary = "Actualiza datos de un Historial Clinico por ID",
            description = "Actualiza los datos de un Historial Clinico.Se envia los atributos a actualizar del historial clinico y el ID del Historial Clinico",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ClinicalHistoryRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @PutMapping("/update/{id}")
    public ResponseEntity<ClinicalHistory> update(@RequestBody ClinicalHistoryRequestDTO dto,@PathVariable("id") @Parameter(name = "id", description = "ID del Historial Clinico", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        ClinicalHistory obj = service.updateById(id,mapper.toClinicalHistory(dto));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * Lists all clinicalHistorys in a paginated manner.
     *
     * @param page the page number
     * @param size the page size
     * @return the list of clinicalHistorys
     */
    @Operation(
            summary = "Lista todos las Historiales Clinicos de forma paginada",
            description = "Lista todos las historiales clinicos inscritos en la aplicación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ClinicalHistoryResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<ClinicalHistoryResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "historyCode") String sortField,@RequestParam(defaultValue = "desc") String sortOrder){
        try {

            List<ClinicalHistoryResponseDTO> list = service.findAll(page,size,sortField,sortOrder).stream().map(p -> mapper.toClinicalHistoryDTO(p)).collect(Collectors.toList());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException("Error al obtener historiales clinicos", e);
        }
    }

    /**
     * Deletes a clinicalHistory by its ID.
     *
     * @param id the ID of the clinicalHistory to delete
     * @return the deleted clinicalHistory
     */
    @Operation(
            summary = "Elimina una Historial Clinico por ID",
            description = "Elimina una Historial Clinico.Se requiere el parametro ID de la historial clinico",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ClinicalHistoryResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Parameter(example = "") UUID id){
        ClinicalHistory obj = service.findById(id);
        if(obj == null){
            throw new NotFoundException("ID NOT FOUND: " + id);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
