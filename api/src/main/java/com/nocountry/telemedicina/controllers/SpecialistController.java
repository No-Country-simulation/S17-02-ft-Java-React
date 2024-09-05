package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.SpecialistMapper;
import com.nocountry.telemedicina.dto.request.SpecialistRequestDTO;
import com.nocountry.telemedicina.dto.response.SpecialistResponseDTO;
import com.nocountry.telemedicina.exception.NotAuthorizedException;
import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.Specialist;
import com.nocountry.telemedicina.security.oauth2.user.CurrentUser;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import com.nocountry.telemedicina.services.ISpecialistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * SpecialistController class handles all the operations related to specialist.
 */
@Tag(name = "API de Especialistas de la salud", description = "Se puede crear,buscar, eliminar y actualizar especialistas")
@RestController
@RequestMapping("/api/specialist")
public class SpecialistController {

    @Autowired
    private ISpecialistService service;

    @Autowired
    private SpecialistMapper mapper;

    /**
     * Finds a specialist by its ID.
     *
     * @param id the ID of the specialist to find
     * @return the found specialist
     */
    @Operation(
            summary = "Busca un Especialista por su ID",
            description = "Busca un Especialista.Se requiere el parametro ID del especialista",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = SpecialistResponseDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<SpecialistResponseDTO> findById(@PathVariable("id") @Parameter(name = "id", description = "ID del Especialista", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        Specialist obj = service.findById(id);
        if(obj == null){
            throw new NotAuthorizedException("ID NOT FOUND: " + id);
        }else {
            return new ResponseEntity<>(mapper.toSpecialistDTO(obj), HttpStatus.OK);
        }
    }

    /**
     * Creates a new specialist.
     *
     * @param dto the specialist data to create
     * @return the created specialist
     */
    @Operation(
            summary = "Crea un especialista",
            description = "Crea un Especialista.Se requiere enviar los parametros descritos a continuación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = SpecialistRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody SpecialistRequestDTO dto, @CurrentUser UserPrincipal user){
        Specialist obj = service.save(mapper.toSpecialist(dto),user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getSpecialistId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updates a specialist by its ID.
     *
     * @param dto the specialist data to update
     * @param id the ID of the specialist to update
     * @return the updated specialist
     */
    @Operation(
            summary = "Actualiza datos de un Especialista por ID",
            description = "Actualiza los datos de un Especialista.Se envia los atributos a actualizar del especialista y el ID del Especialista",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =SpecialistRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @PutMapping("/update/{id}")
    public ResponseEntity<Specialist> update(@RequestBody SpecialistRequestDTO dto,@PathVariable("id") @Parameter(name = "id", description = "ID del Especialista", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        Specialist obj = service.updateById(id,mapper.toSpecialist(dto));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * Lists all specialist in a paginated manner.
     *
     * @param page the page number
     * @param size the page size
     * @return the list of specialist
     */
    @Operation(
            summary = "Lista todos los Especialistas de forma paginada",
            description = "Lista todos los especialistas inscritos en la aplicación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =SpecialistResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<Page<SpecialistResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "reputation") String sortField, @RequestParam(defaultValue = "desc") String sortOrder){
        try {

            List<SpecialistResponseDTO> list = service.findAll(page,size,sortField,sortOrder).stream().map(p -> mapper.toSpecialistDTO(p)).collect(Collectors.toList());
            Page<SpecialistResponseDTO> pageResponse = new PageImpl<>(list);
            return new ResponseEntity<>(pageResponse, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException("Error al obtener los especialistas", e);
        }
    }

    /**
     * Deletes a specialist by its ID.
     *
     * @param id the ID of the specialist to delete
     * @return the deleted specialist
     */
    @Operation(
            summary = "Elimina una Especialista por ID",
            description = "Elimina una Especialista.Se requiere el parametro ID de la especialista",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =SpecialistResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Parameter(example = "") UUID id){
        Specialist obj = service.findById(id);
        if(obj == null){
            throw new NotFoundException("ID NOT FOUND: " + id);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Filter and order all specialist in a paginated manner.
     *
     * @param page the page number
     * @param size the page size
     * @return the list of specialist
     */
    @Operation(
            summary = "Filtra y ordena todos los Especialistas de forma paginada",
            description = "Filtra y ordena todos los especialistas inscritos en la aplicación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(array = @ArraySchema(schema = @Schema(implementation = SpecialistResponseDTO.class)),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/filtered")
    public ResponseEntity<List<SpecialistResponseDTO>> findAllByFilters (
            @RequestParam(required = false) String districtName,
            @RequestParam(required = false) String specialtyName,
            @RequestParam(required = false) String profileName,
            @RequestParam(required = false) Integer reputation,
            @RequestParam(required = false) String clinicName,
            @RequestParam(required = false) Integer clinicReputation,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "true") boolean isAscendant,
            @Parameter(
                    in = ParameterIn.QUERY,
                    description = "Campo por el cual ordenar",
                    schema = @Schema(type = "string",
                    allowableValues = {"reputation", "price", "specialty", "location","clinic"}),
                    example = "reputation"
            )
            @RequestParam(required = false, defaultValue = "reputation") String query ){
        try {
            List<SpecialistResponseDTO> list = service
                    .getFilteredSpecialists(
                            districtName, specialtyName, profileName,
                            reputation, clinicName, clinicReputation,
                            minPrice, maxPrice, page,size, isAscendant, query).stream()
                    .map(p -> mapper.toSpecialistDTO(p)).collect(Collectors.toList());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException("Error al obtener los especialistas", e);
        }
    }
}
