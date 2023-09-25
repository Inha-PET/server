package ipet.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "IPET API 명세서",
                description = "IPET에 사용되는 API 명세서",
                version = "1.0.0",
                contact = @Contact(
                        name = "CJY",
                        email = "12201816@inha.edu"
                )
        )
)
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
//        SecurityScheme securityScheme = new SecurityScheme()
//                .type(SecurityScheme.Type.HTTP)
//                        .scheme("bearer")
//                        .bearerFormat("JWT")
//                        .in(SecurityScheme.In.HEADER)
//                        .name("Authorization");
//
//        SecurityRequirement securityRequirement =
//                new SecurityRequirement().addList("bearer-jwt");
//
//        return new OpenAPI()
//                .components(
//                        new Components().addSecuritySchemes("bearer-jwt", securityScheme))
//                .security(List.of(securityRequirement));

        return new OpenAPI()
                .components(new Components());
    }
}
