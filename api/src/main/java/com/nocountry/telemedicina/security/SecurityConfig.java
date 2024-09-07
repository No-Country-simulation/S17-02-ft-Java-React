package com.nocountry.telemedicina.security;

import com.nocountry.telemedicina.security.filter.JwtAuthenticationFilter;
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

/**
 * The type Security config.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
        private final static String OAUTH2_BASE_URI = "/api/auth/oauth2/authorize";
        private final static String OAUTH2_REDIRECTION_ENDPOINT = "/oauth2/callback/*";
        private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
        private final CustomOAuth2UserService customOAuth2UserService;
        private final ClientRegistrationRepository clientRegistrationRepository;
        private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
        private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
        @Autowired
        private JwtAuthenticationFilter jwtAuthenticationFilter;
        @Autowired
        private AuthenticationProvider authenticationProvider;

        private static final String[] AUTH_ENDPOINTS_PUBLIC = {
                        "/api/auth/login",
                        "/api/auth/greet",
                        "/api/auth/register",
                        "/api/auth/check-login",
                        "/api/auth/login/oauth",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/api/mercado-pago/webhook",
                        "/api/mercado-pago/generate-preference"
        };

        private static final String[] OTHERS_ENDPOINTS_PUBLIC = {
                        "/api/specialist",
                        "/api/schedules",
                        "/api/clinics",
                        "/api/specialty"
        };

        /**
         * Security filter chain security filter chain.
         *
         * @param http the http
         * @return the security filter chain
         * @throws Exception the exception
         */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                                .csrf(crsf -> crsf.disable())
                                .authorizeHttpRequests(authConfig -> {
                                        authConfig.requestMatchers(AUTH_ENDPOINTS_PUBLIC).permitAll();
                                        authConfig.requestMatchers(HttpMethod.GET, OTHERS_ENDPOINTS_PUBLIC).permitAll();
                                        authConfig.requestMatchers("/private").hasRole("USER");
                                        authConfig.requestMatchers("/api/profiles").hasAnyRole("USER", "ADMIN",
                                                        "SPECIALIST");
                                        authConfig.requestMatchers("/api/profiles/**").hasAnyRole("USER", "ADMIN",
                                                        "SPECIALIST");
                                        authConfig.requestMatchers("/api/specialist/**").hasAnyRole( "ADMIN",
                                                "SPECIALIST");
                                        authConfig.requestMatchers("/api/bookings/**").hasAnyRole( "ADMIN","USER",
                                                "SPECIALIST");
                                        authConfig.requestMatchers("/api/schedules/**").hasAnyRole( "ADMIN",
                                                "SPECIALIST");
                                        authConfig.requestMatchers("/api/payments/**").hasAnyRole( "ADMIN","USER",
                                                "SPECIALIST");
                                        authConfig.requestMatchers("/api/clinicalHistory-histories").hasAnyRole( "ADMIN",
                                                "SPECIALIST");
                                        authConfig.requestMatchers("/api/clinical-records").hasAnyRole( "ADMIN",
                                                "SPECIALIST");
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
                                                .accessDeniedHandler(accessDeniedHandler()) // Custom handler for access
                                                                                            // denied
                                                .authenticationEntryPoint(authenticationEntryPoint()) // Custom entry
                                                                                                      // point for
                                                                                                      // authentication
                                                                                                      // errors
                                )
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthenticationFilter,
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

        /**
         * Access denied handler access denied handler.
         *
         * @return the access denied handler
         */
        @Bean
        public AccessDeniedHandler accessDeniedHandler() {
                return (request, response, accessDeniedException) -> {
                        response.setStatus(HttpStatus.FORBIDDEN.value());
                        response.getWriter().write("403 Forbidden: Acceso denegado.");
                };
        }

        /**
         * Authentication entry point authentication entry point.
         *
         * @return the authentication entry point
         */
        @Bean
        public AuthenticationEntryPoint authenticationEntryPoint() {
                return new Http403ForbiddenEntryPoint(); // This will return a 403 error if not authenticated
        }

        /**
         * Cors configuration source cors configuration source.
         *
         * @return the cors configuration source
         */
        @Bean
        CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowCredentials(true);
                configuration.setAllowedOrigins(List.of("http://localhost:5173"));
                configuration.setAllowedMethods(
                                Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"));
                configuration.setAllowedHeaders(List.of("*"));
                configuration.setMaxAge(3600L); // 1 hour

                // Add CSRF protection
                configuration.setExposedHeaders(List.of("X-CSRF-TOKEN"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }

}
