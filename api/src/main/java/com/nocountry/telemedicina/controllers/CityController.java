package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.CityMapper;
import com.nocountry.telemedicina.dto.response.CityResponseDTO;
import com.nocountry.telemedicina.services.ICityService;
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

import java.util.List;
import java.util.stream.Collectors;

/**
 * CityController class handles all the operations related to cities.
 */
@Tag(name = "API de Ciudades", description = "Se puede listar ciudades")
@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    private ICityService service;

    @Autowired
    private CityMapper mapper;

    /**
     * Lists all cities.
     *
     * @return the list of cities
     */
    @Operation(
            summary = "Lista todos las Ciudades",
            description = "Lista todos las ciudades inscritas en la aplicación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = CityResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<CityResponseDTO>> findAll(){
        try {

            List<CityResponseDTO> list = service.findAll().stream().map(p -> mapper.toDepartmentDTO(p)).collect(Collectors.toList());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException("Error al obtener las ciudades", e);
        }
    }

    /**
     * Lists all cities by Department name.
     *
     * @return the list of cities by department name
     */
    @Operation(
            summary = "Lista todos las Ciudades por Departamentp",
            description = "Lista todos las ciudades inscritas por departamento en la aplicación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = CityResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/department")
    public ResponseEntity<List<CityResponseDTO>> findAllByDepartment(@RequestBody String department){
        try {
            List<CityResponseDTO> list = service.findAll().stream()
                    .filter(p -> p.getDepartment().getDepartmentName().equals(department))
                    .map(p -> mapper.toDepartmentDTO(p)).collect(Collectors.toList());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException("Error al obtener las ciudades", e);
        }
    }
}
