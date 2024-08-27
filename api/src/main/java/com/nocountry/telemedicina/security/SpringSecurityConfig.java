package com.nocountry.telemedicina.security;

import com.nocountry.telemedicina.security.filter.JwtValidationFilter;
import com.nocountry.telemedicina.security.oauth2.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
// @EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SpringSecurityConfig {
        private final static String OAUTH2_BASE_URI = "/api/auth/oauth2/authorize";
        private final static String OAUTH2_REDIRECTION_ENDPOINT = "/oauth2/callback/*";
        private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
        private final CustomOAuth2UserService customOAuth2UserService;
        private final ClientRegistrationRepository clientRegistrationRepository;
        private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
        private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

        @Autowired
        private final AuthenticationProvider authenticationProvider;
        @Autowired
        private final JwtValidationFilter jwtValidationFilter;

        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .cors(corsConfig -> corsConfig.configurationSource(corsConfigurationSource()))
                                .csrf(crsf -> crsf.disable())
                                .authorizeHttpRequests(authConfig -> {
                                        authConfig.requestMatchers(HttpMethod.GET,
                                                        "/api/health").permitAll();
                                        authConfig.requestMatchers(HttpMethod.POST,
                                                        "/api/users").permitAll();
                                        authConfig.requestMatchers(HttpMethod.POST,
                                                        "/api/profiles").permitAll();
                                        authConfig.requestMatchers(HttpMethod.GET,
                                                        "/v3/api-docs/**").permitAll();
                                        authConfig.requestMatchers(HttpMethod.GET,
                                                        "/swagger-ui/**").permitAll();
                                        authConfig.requestMatchers(HttpMethod.GET,
                                                        "/api/users").permitAll();
                                        authConfig.requestMatchers(HttpMethod.POST,
                                                        "/api/auth/register").permitAll();
                                        authConfig.requestMatchers(HttpMethod.POST,
                                                        "/api/auth/login").permitAll();
                                        authConfig.requestMatchers(HttpMethod.POST,
                                                        "/api/auth/register").permitAll();
                                        authConfig.requestMatchers(HttpMethod.GET,
                                                        "/api/auth/check-login").permitAll();
                                        authConfig.anyRequest().denyAll();
                                })
                                .oauth2Login(oauth2 -> oauth2
                                                .authorizationEndpoint(
                                                                authorizationEndpointConfig -> authorizationEndpointConfig
                                                                                .baseUri(OAUTH2_BASE_URI)
                                                                                .authorizationRequestRepository(
                                                                                                httpCookieOAuth2AuthorizationRequestRepository)
                                                                                .authorizationRequestResolver(
                                                                                                new CustomAuthorizationRequestResolver(
                                                                                                                clientRegistrationRepository,
                                                                                                                OAUTH2_BASE_URI)))
                                                .redirectionEndpoint(
                                                                redirectionEndpointConfig -> redirectionEndpointConfig
                                                                                .baseUri(OAUTH2_REDIRECTION_ENDPOINT))
                                                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                                                .userService(customOAuth2UserService))
                                                .tokenEndpoint(tokenEndpointConfig -> tokenEndpointConfig
                                                                .accessTokenResponseClient(
                                                                                authorizationCodeTokenResponseClient()))
                                                .successHandler(oAuth2AuthenticationSuccessHandler)
                                                .failureHandler(oAuth2AuthenticationFailureHandler))
                                .exceptionHandling(exceptionHandling -> exceptionHandling
                                                .accessDeniedHandler(accessDeniedHandler())
                                                .authenticationEntryPoint(authenticationEntryPoint()))
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtValidationFilter,
                                                UsernamePasswordAuthenticationFilter.class);
                return http.build();

        }

        private OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> authorizationCodeTokenResponseClient() {
                OAuth2AccessTokenResponseHttpMessageConverter tokenResponseHttpMessageConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
                tokenResponseHttpMessageConverter
                                .setAccessTokenResponseConverter(new CustomAccessTokenResponseConverter());

                RestTemplate restTemplate = new RestTemplate(
                                Arrays.asList(new FormHttpMessageConverter(), tokenResponseHttpMessageConverter));
                restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());

                DefaultAuthorizationCodeTokenResponseClient tokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
                tokenResponseClient.setRestOperations(restTemplate);

                return tokenResponseClient;
        }

        @Bean
        public AccessDeniedHandler accessDeniedHandler() {
                return (request, response, accessDeniedException) -> {
                        response.setStatus(HttpStatus.FORBIDDEN.value());
                        response.getWriter().write("403 Forbidden: Acceso denegado.");
                };
        }

        @Bean
        public AuthenticationEntryPoint authenticationEntryPoint() {
                return new Http403ForbiddenEntryPoint();
        }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowCredentials(true);
                configuration.setAllowedOrigins(List.of("http://localhost:5173", "*"));
                configuration.setAllowedMethods(
                                Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"));
                configuration.setAllowedHeaders(List.of("*"));
                configuration.setMaxAge(3600L);

                configuration.setExposedHeaders(List.of("X-CSRF-TOKEN"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }

}