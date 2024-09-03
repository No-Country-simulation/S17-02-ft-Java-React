package com.nocountry.telemedicina.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${application-description}")
    private String appDescription;
    @Value("${application-version}")
    private String appVersion;
    Contact contact = new Contact().name("s16-02-ft-java-react") .url("https://github.com/No-Country-simulation/S17-02-ft-Java-React");
    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    private final Info customInfo = new Info().title("TeleMedicina")
            .version(appVersion)
            .contact(contact)
            .description(appDescription)
            .license(mitLicense);

    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )).info(customInfo);
    }
}