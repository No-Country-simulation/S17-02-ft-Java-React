package com.nocountry.telemedicina.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nocountry.telemedicina.dto.request.ItemPreferenceDTO;
import com.nocountry.telemedicina.dto.request.WebHookDTO;
import com.nocountry.telemedicina.services.impl.MercadoPagoServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
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
    public String postMethodName(@RequestBody ItemPreferenceDTO item) {
        List<ItemPreferenceDTO> items = Arrays.asList(item);
        return mercadoPagoServiceImpl.generatePreferenceId(items);
    }

    @PostMapping("/webhook")
    public ResponseEntity<?> webhook(@RequestBody WebHookDTO webHookDTO) {
        String paymentId = webHookDTO.getData().get("id");
        String type = webHookDTO.getType();
        String url = String.format("https://api.mercadopago.com/v1/payments/%s", paymentId);
        if (type.equals("payment")) {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(bearerTokenPro);
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url,
                    HttpMethod.GET, requestEntity,
                    (Class<Map<String, Object>>) (Class<?>) Map.class);

            log.info(response.toString());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok("que miras maquina");
    }

    // @PostMapping("/webhook")
    // public ResponseEntity<?> webhook(
    // @RequestBody Map<String, Object> request) {

    // log.info(request.toString());

    // return ResponseEntity.ok("hola");
    // }
}
