package com.nocountry.telemedicina.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import com.nocountry.telemedicina.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import com.nocountry.telemedicina.dto.request.CardPaymentDTO;
import com.nocountry.telemedicina.dto.request.ItemPreferenceDTO;
import com.nocountry.telemedicina.dto.request.PaymentInfoDTO;

@Service
@Slf4j
public class MercadoPagoServiceImpl {

        @Value("${mercadopago.token.pro}")
        private String mercadoPagoTokenPro;
        @Value("${mercadopago.token.brick}")
        private String mercadoPagoTokenBrick;

        public Payment cardPayment(CardPaymentDTO request) throws MPException {
                try {
                        MercadoPagoConfig.setAccessToken(mercadoPagoTokenBrick);
                        PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
                                        .transactionAmount(request.getTransactionAmount())
                                        .token(request.getToken())
                                        .installments(request.getInstallments())
                                        .paymentMethodId(request.getPaymentMethodId())
                                        .payer(
                                                        PaymentPayerRequest.builder()
                                                                        .email(request.getPayer().getEmail())
                                                                        .identification(
                                                                                        IdentificationRequest.builder()
                                                                                                        .type(request.getPayer()
                                                                                                                        .getIdentification()
                                                                                                                        .getType())
                                                                                                        .number(request.getPayer()
                                                                                                                        .getIdentification()
                                                                                                                        .getNumber())
                                                                                                        .build())
                                                                        .build())
                                        .build();

                        PaymentClient client = new PaymentClient();

                        Payment pago = client.create(paymentCreateRequest);
                        return pago;
                } catch (MPException | MPApiException e) {
                        throw new MPException(e.getMessage());
                }
        }

        public String generatePreferenceId(List<ItemPreferenceDTO> productos) {
                try {
                        MercadoPagoConfig.setAccessToken(mercadoPagoTokenPro);

                        List<PreferenceItemRequest> items = productos.stream()
                                        .map(producto -> PreferenceItemRequest.builder()
                                                        .id(producto.getId().toString())
                                                        .title(producto.getTitle())
                                                        .description(producto.getDescription())
                                                        .categoryId(producto.getCategoryId())
                                                        .quantity(1)
                                                        .currencyId("ARS") // "ARS, PE"
                                                        .unitPrice(producto.getUnitPrice())
                                                        .build())
                                        .collect(Collectors.toList());

                        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                                        .success("http://localhost:5173/")
                                        .pending("http://localhost:5173/")
                                        .failure("http://localhost:5173/")
                                        .build();

                        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                                        .items(items)
                                        .additionalInfo("HeyDoc")
                                        .notificationUrl(
                                                        "https://0d36-179-38-157-244.ngrok-free.app/api/mercadoPago/notify")
                                        .backUrls(backUrls)
                                        .autoReturn("approved")
                                        .statementDescriptor("HeyDoc")
                                        .expires(true)
                                        .marketplace("HeyDoc")
                                        .build();

                        PreferenceClient client = new PreferenceClient();
                        Preference preference = client.create(preferenceRequest);
                        return preference.getId();
                } catch (MPException | MPApiException error) {
                        throw new BadRequestException(error.getMessage());
                }
        }

        public PaymentInfoDTO getInfoMp(String url) {
                try {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setBearerAuth(mercadoPagoTokenPro);
                        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
                        RestTemplate restTemplate = new RestTemplate();
                        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url,
                                        HttpMethod.GET, requestEntity,
                                        (Class<Map<String, Object>>) (Class<?>) Map.class);

                        ObjectMapper objectMapper = new ObjectMapper();
                        PaymentInfoDTO paymentResponse = objectMapper.convertValue(response.getBody(),
                                        PaymentInfoDTO.class);
                        log.info(paymentResponse.toString());
                        return paymentResponse;
                } catch (Exception e) {
                        throw new BadRequestException(e.getMessage());
                }
        }
}
