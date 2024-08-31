package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.PayMapper;
import com.nocountry.telemedicina.dto.request.PayRequestDTO;
import com.nocountry.telemedicina.dto.response.PayResponseDTO;
import com.nocountry.telemedicina.exception.NotAuthorizedException;
import com.nocountry.telemedicina.models.Pay;
import com.nocountry.telemedicina.services.IPayService;
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

@Tag(name = "API de Pagos", description = "Se puede crear,buscar,listar y actualizar pagos")
@RestController
@RequestMapping("/api/payments")
public class PayController {
    @Autowired
    private IPayService service;

    @Autowired
    private PayMapper mapper;

    /**
     * Finds a pay by its ID.
     *
     * @param id the ID of the pay to find
     * @return the found pay
     */
    @Operation(
            summary = "Busca un Pago por su ID",
            description = "Busca un Pago.Se requiere el parametro ID del pago",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = PayResponseDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<PayResponseDTO> findById(@PathVariable("id") @Parameter(name = "id", description = "ID del Pago", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        Pay obj = service.findById(id);
        if(obj == null){
            throw new NotAuthorizedException("ID NOT FOUND: " + id);
        }else {
            return new ResponseEntity<>(mapper.toPayDTO(obj), HttpStatus.OK);
        }
    }

    /**
     * Creates a new pay.
     *
     * @param dto the pay data to create
     * @return the created pay
     */
    @Operation(
            summary = "Crea un pago",
            description = "Crea un Pago.Se requiere enviar los parametros descritos a continuación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = PayRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody PayRequestDTO dto){
        Pay obj = service.save(mapper.toPay(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getPayId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updates a pay by its ID.
     *
     * @param dto the pay data to update
     * @param id the ID of the pay to update
     * @return the updated pay
     */
    @Operation(
            summary = "Actualiza datos de un Pago por ID",
            description = "Actualiza los datos de un Pago.Se envia los atributos a actualizar del pago y el ID del Pago",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =PayRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @PutMapping("/update/{id}")
    public ResponseEntity<Pay> update(@RequestBody PayRequestDTO dto,@PathVariable("id") @Parameter(name = "id", description = "ID del Pago", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        Pay obj = service.updateById(id,mapper.toPay(dto));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * Lists all pays in a paginated manner.
     *
     * @param page the page number
     * @param size the page size
     * @return the list of pays
     */
    @Operation(
            summary = "Lista todos los Pagos de forma paginada",
            description = "Lista todos los pagos inscritos en la aplicación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =PayResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<PayResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        try {

            List<PayResponseDTO> list = service.findAll(page,size).stream().map(p -> mapper.toPayDTO(p)).collect(Collectors.toList());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException("Error al obtener los pagos", e);
        }
    }
}
