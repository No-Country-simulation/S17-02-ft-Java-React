package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.DepartmentMapper;
import com.nocountry.telemedicina.dto.response.DepartmentResponseDTO;
import com.nocountry.telemedicina.services.IDepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DepartmentController class handles all the operations related to departments.
 */
@Tag(name = "API de Departamentos", description = "Se puede listar departamentos")
@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService service;

    @Autowired
    private DepartmentMapper mapper;

    /**
     * Lists all departments.
     *
     * @return the list of departments
     */
    @Operation(
            summary = "Lista todos los Departamentos",
            description = "Lista todos los departamentos inscritos en la aplicación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = DepartmentResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> findAll(){
        try {
            List<DepartmentResponseDTO> list = service.findAll().stream().map(p -> mapper.toDepartmentDTO(p)).collect(Collectors.toList());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException("Error al obtener los departamentos", e);
        }
    }
}
