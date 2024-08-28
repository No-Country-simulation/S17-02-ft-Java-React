package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.request.ReviewRequestDTO;
import com.nocountry.telemedicina.dto.response.ReviewResponseDTO;
import com.nocountry.telemedicina.models.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(source = "user.userid", target = "userId")
    @Mapping(source = "user.username", target = "userName")
    @Mapping(source = "specialist.specialistId", target = "specialistId")
    @Mapping(source = "specialist.profile.profileName", target = "specialistName")
    ReviewResponseDTO toReviewDTO(Review review);

    @Mapping(source = "user",target = "user")
    @Mapping(source = "specialist",target = "specialist")
    Review toReview(ReviewRequestDTO reviewDTO);
}
