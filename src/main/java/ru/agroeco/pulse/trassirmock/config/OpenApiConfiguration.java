package ru.agroeco.pulse.trassirmock.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI trassirMockOpenApi() {
        return new OpenAPI()
            .info(new Info()
                .title("TRASSIR Mock Server API")
                .description("Mock implementation of the TRASSIR SDK HTTP API")
                .version("0.0.1-SNAPSHOT"));
    }
}
