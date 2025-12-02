package Cis.api.infra.Config.security;

import Cis.api.domain.entity.Usuario;
import Cis.api.domain.enums.Roles;
import Cis.api.infra.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Bootstrapper {

    @Bean
    public CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Verifica se já existe algum usuário no banco de dados
            if (usuarioRepository.count() == 0) {

                System.out.println("🚨 ATENÇÃO: Nenhum usuário encontrado. Criando primeiro COORDENADOR...");

                // 1. Crie o objeto Usuario
                Usuario primeiroCoordenador = new Usuario(
                        "admin", // Login padrão para o primeiro acesso
                        passwordEncoder.encode("123456"), // Senha padrão (mude depois!)
                        Roles.COORDENACAO // Define a permissão correta
                );

                // 2. Salve no banco de dados
                usuarioRepository.save(primeiroCoordenador);

                System.out.println("✅ Coordenador inicial criado com sucesso! Login: admin | Senha: 123456");

                // Você pode adicionar a entidade Coordenacao real aqui se for obrigatório
                // ...
            }
        };
    }
}
