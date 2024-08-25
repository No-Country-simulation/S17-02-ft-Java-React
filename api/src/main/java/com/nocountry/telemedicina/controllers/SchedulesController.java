package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.SchedulesMapper;
import com.nocountry.telemedicina.dto.request.SchedulesRequestDTO;
import com.nocountry.telemedicina.dto.response.SchedulesResponseDTO;
import com.nocountry.telemedicina.exception.NotAuthorizedException;
import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.Schedules;
import com.nocountry.telemedicina.services.ISchedulesService;
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
 * SchedulesController class handles all the operations related to schedules.
 */
@Tag(name = "API de Turnos de Atención", description = "Se puede crear,buscar,eliminar y actualizar turnos de atención")
@RestController
@RequestMapping("/api/schedules")
public class SchedulesController {

    @Autowired
    private ISchedulesService service;

    @Autowired
    private SchedulesMapper mapper;

    /**
     * Finds a schedules by its ID.
     *
     * @param id the ID of the schedules to find
     * @return the found schedules
     */
    @Operation(
            summary = "Busca un Turno por su ID",
            description = "Busca un Turno.Se requiere el parametro ID del turno",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = SchedulesResponseDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<SchedulesResponseDTO> findById(@PathVariable("id") @Parameter(name = "id", description = "ID del Turno", example = "6097656c-e788-45cb-a41f-73d4e031ee60") Long id){
        Schedules obj = service.findById(id);
        if(obj == null){
            throw new NotAuthorizedException("ID NOT FOUND: " + id);
        }else {
            return new ResponseEntity<>(mapper.toSchedulesDTO(obj), HttpStatus.OK);
        }
    }

    /**
     * Creates a new schedules.
     *
     * @param dto the schedules data to create
     * @return the created schedules
     */
    @Operation(
            summary = "Crea un turno",
            description = "Crea un Turno.Se requiere enviar los parametros descritos a continuación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = SchedulesRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody SchedulesRequestDTO dto){
        Schedules obj = service.save(mapper.toSchedules(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getSchedulesId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updates a schedules by its ID.
     *
     * @param dto the schedules data to update
     * @param id the ID of the schedules to update
     * @return the updated schedules
     */
    @Operation(
            summary = "Actualiza datos de un Turno por ID",
            description = "Actualiza los datos de un Turno.Se envia los atributos a actualizar del turno y el ID del Turno",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =SchedulesRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @PutMapping("/update/{id}")
    public ResponseEntity<Schedules> update(@RequestBody SchedulesRequestDTO dto,@PathVariable("id") @Parameter(name = "id", description = "ID del Turno", example = "6097656c-e788-45cb-a41f-73d4e031ee60") Long id){
        Schedules obj = service.updateById(id,mapper.toSchedules(dto));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * Lists all schedules in a paginated manner.
     *
     * @param page the page number
     * @param size the page size
     * @return the list of schedules
     */
    @Operation(
            summary = "Lista todos los Turnos de forma paginada",
            description = "Lista todos los turnos inscritos en la aplicación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =SchedulesResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<SchedulesResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        try {

            List<SchedulesResponseDTO> list = service.findAll(page,size).stream().map(p -> mapper.toSchedulesDTO(p)).collect(Collectors.toList());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException("Error al obtener los turnos", e);
        }
    }

    /**
     * Deletes a schedules by its ID.
     *
     * @param id the ID of the schedules to delete
     * @return the deleted schedules
     */
    @Operation(
            summary = "Elimina una Turno por ID",
            description = "Elimina una Turno.Se requiere el parametro ID de la turno",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =SchedulesResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Parameter(example = "") Long id){
        Schedules obj = service.findById(id);
        if(obj == null){
            throw new NotFoundException("ID NOT FOUND: " + id);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
