package com.nocountry.telemedicina.security.oauth2;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * La clase CustomAccessTokenResponseConverter convierte una respuesta de token
 * de acceso de tipo Map<String, Object> en un objeto OAuth2AccessTokenResponse.
 * Este proceso implica extraer y transformar varios parámetros de la respuesta
 * del token, como el token de acceso, el tipo de token, la expiración, los
 * scopes y los parámetros adicionales. Esto es útil para personalizar el
 * procesamiento de la respuesta del token en una integración OAuth2 en
 * aplicaciones Spring Security.
 */
@Slf4j
public class CustomAccessTokenResponseConverter implements Converter<Map<String, Object>, OAuth2AccessTokenResponse> {

    private static final Set<String> TOKEN_RESPONSE_PARAMETER_NAMES = Stream.of(
            OAuth2ParameterNames.ACCESS_TOKEN,
            OAuth2ParameterNames.TOKEN_TYPE,
            OAuth2ParameterNames.EXPIRES_IN,
            OAuth2ParameterNames.REFRESH_TOKEN,
            OAuth2ParameterNames.SCOPE).collect(Collectors.toSet());

    @Override
    public OAuth2AccessTokenResponse convert(Map<String, Object> tokenResponseParameters) {
        String accessToken = (String) tokenResponseParameters.get(OAuth2ParameterNames.ACCESS_TOKEN);

        OAuth2AccessToken.TokenType accessTokenType = OAuth2AccessToken.TokenType.BEARER;

        long expiresIn = 0;
        if (tokenResponseParameters.containsKey(OAuth2ParameterNames.EXPIRES_IN)) {
            try {
                expiresIn = Long.valueOf((Integer) tokenResponseParameters.get(OAuth2ParameterNames.EXPIRES_IN));
            } catch (NumberFormatException ex) {
                log.error("Number format exception during access token response conversion, ", ex);
            }
        }

        Set<String> scopes = Collections.emptySet();
        if (tokenResponseParameters.containsKey(OAuth2ParameterNames.SCOPE)) {
            String scope = (String) tokenResponseParameters.get(OAuth2ParameterNames.SCOPE);
            scopes = Arrays.stream(StringUtils.delimitedListToStringArray(scope, " ")).collect(Collectors.toSet());
        }

        Map<String, Object> additionalParameters = new LinkedHashMap<>();
        tokenResponseParameters.entrySet().stream()
                .filter(e -> !TOKEN_RESPONSE_PARAMETER_NAMES.contains(e.getKey()))
                .forEach(e -> additionalParameters.put(e.getKey(), e.getValue()));

        return OAuth2AccessTokenResponse.withToken(accessToken)
                .tokenType(accessTokenType)
                .expiresIn(expiresIn)
                .scopes(scopes)
                .additionalParameters(additionalParameters)
                .build();
    }
}