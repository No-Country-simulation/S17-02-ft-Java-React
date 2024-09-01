package com.nocountry.telemedicina.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Value("${application-description}")
    private String appDescription;
    @Value("${application-version}")
    private String appVersion;
    private final Info customInfo = new Info().title("TeleMedicina")
            .version(appVersion)
            .description(appDescription);
//            .license(new License().name("GNU General Public License v3.0")
//                    .url("https://www.gnu.org/licenses/gpl-3.0-standalone.html"));

    @Bean
    public GroupedOpenApi configOpenAPI() {
        return GroupedOpenApi.builder().group("main")
                .pathsToMatch("/**").displayName("Componentes de TeleMedicina API")
                .addOpenApiCustomizer(openApi -> openApi.info(customInfo))
                .build();
    }
}
