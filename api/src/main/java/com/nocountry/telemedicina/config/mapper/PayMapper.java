package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.request.PayRequestDTO;
import com.nocountry.telemedicina.dto.response.PayResponseDTO;
import com.nocountry.telemedicina.models.Pay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PayMapper {

    PayMapper INSTANCE = Mappers.getMapper(PayMapper.class);

    @Mapping(source = "booking.state",target = "payState")
    @Mapping(source = "booking.schedule.specialist.bookingPrice",target = "mountPay")
    PayResponseDTO toPayDTO(Pay pay);

    @Mapping(source = "booking",target = "booking")
    Pay toPay(PayRequestDTO dto);

}
