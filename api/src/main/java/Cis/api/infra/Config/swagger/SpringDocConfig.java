package Cis.api.infra.Config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // A segurança global foi removida para tornar o Swagger não restrito.
        // Se precisar de segurança em endpoints específicos, use a anotação @SecurityRequirement.
        return new OpenAPI()
                // 1. Informações básicas da API (Metadata)
                .info(new Info()
                        .title("API CIS - Clínica Integrada de Saúde")
                        .description("API REST para gestão de usuários (Psicólogo, Paciente, Coordenação) e agendamento de sessões.")
                        .contact(new Contact()
                                .name("Time Backend CIS")
                                .email("backend@cis.com.br"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://cis/api/licenca"))
                        .version("1.0.0")
                );
    }

    /*
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // 1. Define o esquema de segurança (o tipo de autenticação)
                .components(new Components()
                        .addSecuritySchemes("bearer-key", // Nome do esquema (usado na linha 2)
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP) // Tipo de segurança: HTTP
                                        .scheme("bearer")               // Método: Bearer (para JWT)
                                        .bearerFormat("JWT")            // Formato específico
                                        .description("Informe o token JWT (apenas o token, sem 'Bearer ') para acessar esta rota.")
                        ))

                // 2. Aplica o esquema de segurança a todos os endpoints
                .addSecurityItem(new SecurityRequirement().addList("bearer-key"))

                // 3. Informações básicas da API (Metadata)
                .info(new Info()
                        .title("API CIS - Clínica Integrada de Saúde")
                        .description("API REST para gestão de usuários (Psicólogo, Paciente, Coordenação) e agendamento de sessões.")
                        .contact(new Contact()
                                .name("Time Backend CIS")
                                .email("backend@cis.com.br"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://cis/api/licenca"))
                        .version("1.0.0")
                );
    }

     */
}
