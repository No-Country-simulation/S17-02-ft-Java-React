package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.BookingMapper;
import com.nocountry.telemedicina.dto.request.BookingRequestDTO;
import com.nocountry.telemedicina.dto.response.BookingResponseDTO;
import com.nocountry.telemedicina.exception.NotAuthorizedException;
import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.Booking;
import com.nocountry.telemedicina.services.IBookingService;
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

@Tag(name = "Api de Reservas", description = "Se puede crear,buscar, listar y actualizar reservas")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private IBookingService service;

    @Autowired
    private BookingMapper mapper;

    /**
     * Finds a booking by its ID.
     *
     * @param id the ID of the booking to find
     * @return the found booking
     */
    @Operation(
            summary = "Busca un Reserva por su ID",
            description = "Busca un Reserva.Se requiere el parametro ID del reserva",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = BookingResponseDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> findById(@PathVariable("id") @Parameter(name = "id", description = "ID del Reserva", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        Booking obj = service.findById(id);
        if(obj == null){
            throw new NotAuthorizedException("ID NOT FOUND: " + id);
        }else {
            return new ResponseEntity<>(mapper.toBookingDTO(obj), HttpStatus.OK);
        }
    }

    /**
     * Creates a new booking.
     *
     * @param dto the booking data to create
     * @return the created booking
     */
    @Operation(
            summary = "Crea un reserva",
            description = "Crea un Reserva.Se requiere enviar los parametros descritos a continuación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = BookingRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody BookingRequestDTO dto){
        Booking obj = service.save(mapper.toBooking(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getBookingId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updates a booking by its ID.
     *
     * @param dto the booking data to update
     * @param id the ID of the booking to update
     * @return the updated booking
     */
    @Operation(
            summary = "Actualiza datos de un Reserva por ID",
            description = "Actualiza los datos de un Reserva.Se envia los atributos a actualizar del reserva y el ID del Reserva",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =BookingRequestDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @PutMapping("/update/{id}")
    public ResponseEntity<Booking> update(@RequestBody BookingRequestDTO dto,@PathVariable("id") @Parameter(name = "id", description = "ID del Reserva", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        Booking obj = service.updateById(id,mapper.toBooking(dto));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * Lists all bookings in a paginated manner.
     *
     * @param page the page number
     * @param size the page size
     * @return the list of bookings
     */
    @Operation(
            summary = "Lista todos las Reservas de forma paginada",
            description = "Lista todos las reservas inscritos en la aplicación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =BookingResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        try {

            List<BookingResponseDTO> list = service.findAll(page,size).stream().map(p -> mapper.toBookingDTO(p)).collect(Collectors.toList());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException("Error al obtener reservas", e);
        }
    }

    /**
     * Deletes a booking by its ID.
     *
     * @param id the ID of the booking to delete
     * @return the deleted booking
     */
    @Operation(
            summary = "Elimina una Reserva por ID",
            description = "Elimina una Reserva.Se requiere el parametro ID de la reserva",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =BookingResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Parameter(example = "") UUID id){
        Booking obj = service.findById(id);
        if(obj == null){
            throw new NotFoundException("ID NOT FOUND: " + id);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}