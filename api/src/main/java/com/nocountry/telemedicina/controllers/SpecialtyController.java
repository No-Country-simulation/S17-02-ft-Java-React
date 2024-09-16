package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.SpecialtyMapper;
import com.nocountry.telemedicina.dto.request.SpecialtyRequestDTO;
import com.nocountry.telemedicina.dto.response.SpecialtyResponseDTO;
import com.nocountry.telemedicina.exception.NotAuthorizedException;
import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.Specialty;
import com.nocountry.telemedicina.services.ISpecialtyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpecialtyController class handles all the operations related to specialty.
 */
@Tag(name = "API de Especialidades de la salud", description = "Se puede crear,buscar, eliminar y actualizar especialidades")
@RestController
@RequestMapping("/api/specialty")
public class SpecialtyController {

    @Autowired
    private ISpecialtyService service;

    @Autowired
    private SpecialtyMapper mapper;

    /**
     * Finds a specialty by its ID.
     *
     * @param id the ID of the specialty to find
     * @return the found specialty
     */
    @Operation(
            summary = "Busca un Especialidad por su ID",
            description = "Busca un Especialidad.Se requiere el parametro ID del especialidad",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = SpecialtyResponseDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyResponseDTO> findById(@PathVariable("id") @Parameter(name = "id", description = "ID del Especialidad", example = "6097656c-e788-45cb-a41f-73d4e031ee60") Long id){
        Specialty obj = service.findById(id);
        if(obj == null){
            throw new NotAuthorizedException("ID NOT FOUND: " + id);
        }else {
            return new ResponseEntity<>(mapper.toSpecialtyDTO(obj), HttpStatus.OK);
        }
    }

    /**
     * Creates a new specialty.
     *
     * @param dto the specialty data to create
     * @return the created specialty
     */
    @Operation(
            summary = "Crea un especialidad",
            description = "Crea un Especialidad.Se requiere enviar los parametros descritos a continuación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = SpecialtyRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody SpecialtyRequestDTO dto){
        Specialty obj = service.save(mapper.toSpecialty(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getSpecialtyId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updates a specialty by its ID.
     *
     * @param dto the specialty data to update
     * @param id the ID of the specialty to update
     * @return the updated specialty
     */
    @Operation(
            summary = "Actualiza datos de un Especialidad por ID",
            description = "Actualiza los datos de un Especialidad.Se envia los atributos a actualizar del especialidad y el ID del Especialidad",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =SpecialtyRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @PutMapping("/update/{id}")
    public ResponseEntity<Specialty> update(@RequestBody SpecialtyRequestDTO dto,@PathVariable("id") @Parameter(name = "id", description = "ID del Especialidad", example = "6097656c-e788-45cb-a41f-73d4e031ee60") Long id){
        Specialty obj = service.updateById(id,mapper.toSpecialty(dto));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * Lists all specialty in a paginated manner.
     *
     * @param page the page number
     * @param size the page size
     * @return the list of specialty
     */
    @Operation(
            summary = "Lista todos los Especialidads de forma paginada",
            description = "Lista todos los especialidads inscritos en la aplicación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =SpecialtyResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<Page<SpecialtyResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size, @RequestParam(defaultValue = "specialtyName") String sortField, @RequestParam(defaultValue = "desc") String sortOrder){
        try {

            List<SpecialtyResponseDTO> list = service.findAll(page,size,sortField,sortOrder).stream().map(p -> mapper.toSpecialtyDTO(p)).collect(Collectors.toList());
            Page<SpecialtyResponseDTO> pageResponse = new PageImpl<>(list);
            return new ResponseEntity<>(pageResponse, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException("Error al obtener los especialidads", e);
        }
    }

    /**
     * Deletes a specialty by its ID.
     *
     * @param id the ID of the specialty to delete
     * @return the deleted specialty
     */
    @Operation(
            summary = "Elimina una Especialidad por ID",
            description = "Elimina una Especialidad.Se requiere el parametro ID de la especialidad",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =SpecialtyResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Parameter(example = "") Long id){
        Specialty obj = service.findById(id);
        if(obj == null){
            throw new NotFoundException("ID NOT FOUND: " + id);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
