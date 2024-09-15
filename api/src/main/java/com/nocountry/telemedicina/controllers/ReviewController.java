package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.ReviewMapper;
import com.nocountry.telemedicina.dto.request.ReviewRequestDTO;
import com.nocountry.telemedicina.dto.response.ReviewResponseDTO;
import com.nocountry.telemedicina.exception.NotAuthorizedException;
import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.Review;
import com.nocountry.telemedicina.services.IReviewService;
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
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * ProfileController class handles all the operations related to profiles.
 */
@Tag(name = "API de Reviews", description = "Se puede crear,buscar actualizar y eliminar reviews")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private IReviewService service;

    @Autowired
    private ReviewMapper mapper;

    /**
     * Finds a review by its ID.
     *
     * @param id the ID of the review to find
     * @return the found review
     */
    @Operation(
            summary = "Busca un Review por su ID",
            description = "Busca un Review.Se requiere el parametro ID del Review",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ReviewResponseDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> findById(@PathVariable("id") @Parameter(name = "id", description = "ID del Review", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        Review obj = service.findById(id);
        if(obj == null){
            throw new NotAuthorizedException("ID NOT FOUND: " + id);
        }else {
            return new ResponseEntity<>(mapper.toReviewDTO(obj), HttpStatus.OK);
        }
    }

    /**
     * Creates a new review.
     *
     * @param dto the review data to create
     * @return the created review
     */
    @Operation(
            summary = "Crea un review",
            description = "Crea un Review.Se requiere enviar los parametros descritos a continuación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = ReviewResponseDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ReviewRequestDTO dto){
        Review obj = service.save(mapper.toReview(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getReviewId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updates a review by its ID.
     *
     * @param dto the review data to update
     * @param id the ID of the review to update
     * @return the updated review
     */
    @Operation(
            summary = "Actualiza datos de un Review por ID",
            description = "Actualiza los datos de un Review. Se envia los atributos a actualizar del review y el ID del review",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = ReviewResponseDTO.class),mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) } ),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @PutMapping("/update/{id}")
    public ResponseEntity<Review> update(@RequestBody ReviewRequestDTO dto, @PathVariable("id") @Parameter(name = "id", description = "ID del Review", example = "6097656c-e788-45cb-a41f-73d4e031ee60") UUID id){
        Review obj = service.updateById(id,mapper.toReview(dto));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * Lists all review in a paginated manner.
     *
     * @param page the page number
     * @param size the page size
     * @return the list of review
     */
    @Operation(
            summary = "Lista todos los Reviews de forma paginada",
            description = "Lista todos los reviews inscritos en la aplicación",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation = ReviewResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<Page<ReviewResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "reviewId") String sortField, @RequestParam(defaultValue = "desc") String sortOrder){
        try {

            List<ReviewResponseDTO> list = service.findAll(page,size,sortField,sortOrder).stream().map(p -> mapper.toReviewDTO(p)).collect(Collectors.toList());
            Page<ReviewResponseDTO> listResponse = new PageImpl<>(list);
            return new ResponseEntity<>(listResponse, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException("Error al obtener los especialistas", e);
        }
    }

    /**
     * Deletes a review by its ID.
     *
     * @param id the ID of the review to delete
     * @return the deleted review
     */
    @Operation(
            summary = "Elimina una Review por ID",
            description = "Elimina una Review.Se requiere el parametro ID del review",
            tags = { })
    @ApiResponses({
            @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ReviewResponseDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Parameter(example = "") UUID id){
        Review obj = service.findById(id);
        if(obj == null){
            throw new NotFoundException("ID NOT FOUND: " + id);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
