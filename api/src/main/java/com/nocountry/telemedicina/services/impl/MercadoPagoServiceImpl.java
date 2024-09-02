package com.nocountry.telemedicina.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MercadoPagoServiceImpl {

    @Value("${mercadopago.access_token}")
    private String mercadoPagoToken;
}
