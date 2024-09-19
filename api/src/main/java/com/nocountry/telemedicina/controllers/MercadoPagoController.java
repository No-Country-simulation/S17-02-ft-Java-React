package com.nocountry.telemedicina.controllers;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nocountry.telemedicina.dto.request.ItemPreferenceDTO;
import com.nocountry.telemedicina.dto.request.PaymentInfoDTO;
import com.nocountry.telemedicina.dto.request.WebHookDTO;
import com.nocountry.telemedicina.services.impl.MercadoPagoServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/mercado-pago")
@Slf4j
public class MercadoPagoController {
    @Value("${mercadopago.token.pro}")
    String bearerTokenPro;
    MercadoPagoServiceImpl mercadoPagoServiceImpl;

    public MercadoPagoController(MercadoPagoServiceImpl mercadoPagoServiceImpl) {
        this.mercadoPagoServiceImpl = mercadoPagoServiceImpl;
    }

    @PostMapping("/generate-preference")
    public String postMethodName(@Valid @RequestBody ItemPreferenceDTO item) {
        return mercadoPagoServiceImpl.generatePreferenceId(item);
    }

    @PostMapping("/webhook")
    public ResponseEntity<?> webhook(@RequestBody WebHookDTO webHookDTO) {
        String paymentId = webHookDTO.getData().get("id");
        String type = webHookDTO.getType();
        String url = String.format("https://api.mercadopago.com/v1/payments/%s", paymentId);
        if (type.equals("payment")) {
            PaymentInfoDTO paymentInfoDTO = mercadoPagoServiceImpl.getInfoMp(url);
            log.info(paymentInfoDTO.toString());
            return ResponseEntity.ok(paymentInfoDTO);
        }
        return ResponseEntity.ok("que miras maquina");
    }
}
