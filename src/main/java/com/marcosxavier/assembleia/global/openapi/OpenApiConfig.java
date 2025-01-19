package com.marcosxavier.assembleia.global.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Api para gerenciamento de sessões de votação para cooperativas",
                version = "1.0.0",
                description = "Esta API oference uma coleção de recursos para o o gerenciamento de sessões de votação.",
                contact = @Contact(
                        name = "Marcos Xavier",
                        email = "marcosxavier@email.com.br",
                        url = "www.google.com.br"
                ),
                license = @License(
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html",
                        name = "Apache 2.0"
                )
        )
)
@Configuration
public class OpenApiConfig {
}
