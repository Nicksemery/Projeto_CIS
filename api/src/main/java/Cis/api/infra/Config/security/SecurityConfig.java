/*
package Cis.api.infra.Config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy
                        (SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/login","/usuario" ).permitAll();
                    req.requestMatchers("/v3/api-docs/**",
                            "/swagger-ui.html",
                            "/swagger-ui/**").permitAll();
                    req.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    /*
                return http.csrf(csrf -> csrf.disable()) // Desabilita o CSRF (padrão para APIs stateless)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    // ROTAS PÚBLICAS

                    req.requestMatchers(HttpMethod.POST, "/login","/usuario").permitAll(); // Acesso ao login
                    req.requestMatchers(HttpMethod.POST, "/paciente").permitAll(); // Auto-cadastro de Paciente (liberado)
                    req.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll(); // Swagger Docs

                    // ROTAS EXCLUSIVAS DA COORDENAÇÃO
                    req.requestMatchers(HttpMethod.POST, "/psicologo").hasRole("COORDENACAO"); // Cadastro de Psicólogo
                    req.requestMatchers(HttpMethod.PUT, "/sessoes/aprovar/**").hasRole("COORDENACAO"); // Aprovação de Sessão
                    req.requestMatchers(HttpMethod.POST, "/coordenacao").hasRole("COORDENACAO"); // Cadastro de Coordenação

                    // ROTAS DE PSICÓLOGO
                    req.requestMatchers("/psicologo/**").hasAnyRole("COORDENACAO", "PSICOLOGO");
                    req.requestMatchers("/agenda/psicologo/**").hasRole("PSICOLOGO");

                    // ROTAS DE PACIENTE
                    req.requestMatchers(HttpMethod.POST, "/sessoes").hasRole("PACIENTE"); // Agendamento de Sessão


                    // DEMANDA AUTENTICAÇÃO PARA QUALQUER OUTRA ROTA NÃO ESPECIFICADA
                    req.anyRequest().authenticated();
                })
                // Adiciona o filtro JWT antes do filtro padrão de login/senha do Spring
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


     */
/*
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}

 */
