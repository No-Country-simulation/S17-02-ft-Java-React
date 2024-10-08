package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.SchedulesConfigMapper;
import com.nocountry.telemedicina.dto.request.SchedulesConfigRequestDTO;
import com.nocountry.telemedicina.dto.response.ScheduleResponseDTO;
import com.nocountry.telemedicina.dto.response.SchedulesConfigResponseDTO;
import com.nocountry.telemedicina.exception.NotAuthorizedException;
import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.ScheduleConfig;
import com.nocountry.telemedicina.security.oauth2.user.CurrentUser;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import com.nocountry.telemedicina.services.ISchedulesConfigService;
import com.nocountry.telemedicina.services.ISchedulesConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SchedulesController class handles all the operations related to schedules.
 */
@Tag(name = "API de Configuracion de horarios", description = "Se puede crear, buscar o eliminar las configuraciones")
@RestController
@RequestMapping("/api/schedules")
public class ScheduleConfigController {

        @Autowired
        private ISchedulesConfigService service;

        @Autowired
        private SchedulesConfigMapper mapper;

        /**
         * Finds a schedules by its ID.
         *
         * @param id the ID of the schedules to find
         * @return the found schedules
         */
        @Operation(summary = "Busca un Turno por su ID", description = "Busca un Turno.Se requiere el parametro ID del turno", tags = {})
        @ApiResponses({
                        @ApiResponse(responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = SchedulesConfigResponseDTO.class), mediaType = "application/json") }),
                        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
                        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
        @GetMapping("/{id}")
        public ResponseEntity<SchedulesConfigResponseDTO> findById(
                        @PathVariable("id") @Parameter(name = "id", description = "ID del Turno", example = "6097656c-e788-45cb-a41f-73d4e031ee60") Long id) {
                ScheduleConfig obj = service.findById(id);
                if (obj == null) {
                        throw new NotAuthorizedException("ID NOT FOUND: " + id);
                } else {
                        return new ResponseEntity<>(mapper.toSchedulesDTO(obj), HttpStatus.OK);
                }
        }

        /**
         * Creates a new schedules.
         *
         * @param dto the schedules data to create
         * @return the created schedules
         */
        @Operation(summary = "Crea un turno", description = "Crea un Turno.Se requiere enviar los parametros descritos a continuación", tags = {})
        @ApiResponses({
                        @ApiResponse(responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = SchedulesConfigRequestDTO.class), mediaType = "application/json") }),
                        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
                        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
        @PostMapping("/create")
        public ResponseEntity<SchedulesConfigResponseDTO> save(@Valid @RequestBody SchedulesConfigRequestDTO dto,
                                                               @CurrentUser UserPrincipal user) {
                ScheduleConfig mapperScheduleConfig = mapper.toSchedules(dto);
                ScheduleConfig obj = service.save(mapperScheduleConfig, user);
                return ResponseEntity.status(201).body(mapper.toSchedulesDTO(obj));
        }

        /**
         * Updates a schedules by its ID.
         *
         * @param dto the schedules data to update
         * @param id  the ID of the schedules to update
         * @return the updated schedules
         */
        @Operation(summary = "Actualiza datos de un Turno por ID", description = "Actualiza los datos de un Turno.Se envia los atributos a actualizar del turno y el ID del Turno", tags = {})
        @ApiResponses({
                        @ApiResponse(responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = SchedulesConfigRequestDTO.class), mediaType = "application/json") }),
                        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
                        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

        @PutMapping("/update/{id}")
        public ResponseEntity<ScheduleConfig> update(@RequestBody SchedulesConfigRequestDTO dto,
                        @PathVariable("id") @Parameter(name = "id", description = "ID del Turno", example = "6097656c-e788-45cb-a41f-73d4e031ee60") Long id) {
                ScheduleConfig obj = service.updateById(id, mapper.toSchedules(dto));
                return new ResponseEntity<>(obj, HttpStatus.OK);
        }

        /**
         * Lists all schedules in a paginated manner.
         *
         * @param page the page number
         * @param size the page size
         * @return the list of schedules
         */
        @Operation(summary = "Lista todos los Turnos de forma paginada", description = "Lista todos los turnos inscritos en la aplicación", tags = {})
        @ApiResponses({
                        @ApiResponse(responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = SchedulesConfigResponseDTO.class), mediaType = "application/json") }),
                        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
                        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
        @GetMapping("/list")
        public ResponseEntity<Page<SchedulesConfigResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam(defaultValue = "schedulesDay") String sortField,
                        @RequestParam(defaultValue = "desc") String sortOrder) {
                try {

                        List<SchedulesConfigResponseDTO> list = service.findAll(page, size, sortField, sortOrder)
                                        .stream()
                                        .map(p -> mapper.toSchedulesDTO(p)).collect(Collectors.toList());
                        Page<SchedulesConfigResponseDTO> listResponse = new PageImpl<>(list);
                        return new ResponseEntity<>(listResponse, HttpStatus.OK);
                } catch (Exception e) {
                        throw new RuntimeException("Error al obtener los turnos", e);
                }
        }

        /**
         * Deletes a schedules by its ID.
         *
         * @param id the ID of the schedules to delete
         * @return the deleted schedules
         */
        @Operation(summary = "Elimina una Turno por ID", description = "Elimina una Turno.Se requiere el parametro ID de la turno", tags = {})
        @ApiResponses({
                        @ApiResponse(responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = SchedulesConfigResponseDTO.class), mediaType = "application/json") }),
                        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
                        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable("id") @Parameter(example = "") Long id) {
                ScheduleConfig obj = service.findById(id);
                if (obj == null) {
                        throw new NotFoundException("ID NOT FOUND: " + id);
                }
                service.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @Operation(summary = "Lista todos los Turnos de un especialista de forma paginada", description = "Lista todos los turnos inscritos de un especialista en la aplicación", tags = {})
        @ApiResponses({
                        @ApiResponse(responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = SchedulesConfigResponseDTO.class), mediaType = "application/json") }),
                        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
                        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
        @GetMapping("/user")
        public ResponseEntity<Page<SchedulesConfigResponseDTO>> findAllByUser(@CurrentUser UserPrincipal user,
                        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                        @RequestParam(defaultValue = "schedules_day") String sortField,
                        @RequestParam(defaultValue = "desc") String sortOrder) {
                try {

                        List<SchedulesConfigResponseDTO> list = service
                                        .findAllByUserId(user, page, size, sortField, sortOrder).stream()
                                        .map(p -> mapper.toSchedulesDTO(p)).collect(Collectors.toList());
                        Page<SchedulesConfigResponseDTO> listResponse = new PageImpl<>(list);
                        return new ResponseEntity<>(listResponse, HttpStatus.OK);
                } catch (Exception e) {
                        throw new RuntimeException("Error al obtener los turnos", e);
                }
        }
}
