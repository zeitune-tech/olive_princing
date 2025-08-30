package sn.zeitune.olive_insurance_pricing.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Olive Insurance Pricing API")
                        .version("1.0")
                        .description("API pour la gestion des tarifs d'assurance Olive"))
                .servers(List.of(
                        new Server().url("http://localhost:8040/api/v1").description("Local server")
                ));
    }
}
