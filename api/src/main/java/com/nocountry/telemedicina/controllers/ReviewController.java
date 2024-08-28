package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.ReviewMapper;
import com.nocountry.telemedicina.dto.response.ReviewResponseDTO;
import com.nocountry.telemedicina.exception.NotAuthorizedException;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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
}
