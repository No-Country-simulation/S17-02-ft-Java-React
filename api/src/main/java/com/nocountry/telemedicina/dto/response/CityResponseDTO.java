package com.nocountry.telemedicina.dto.response;

import java.util.List;

public record CityResponseDTO(Long cityId, String cityName, List<DistrictResponseDTO> districts) {
}
