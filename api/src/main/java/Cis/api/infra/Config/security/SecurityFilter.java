package Cis.api.infra.Config.security;

import Cis.api.infra.repository.UsuarioRepository;
import Cis.api.infra.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService service;
    private final UsuarioRepository repository;

    public SecurityFilter(TokenService service, UsuarioRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Extrai o token do Header da Requisição
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            // 2. Valida o token e recupera o login (Subject)
            var subject = service.getSubject(tokenJWT);

            // 3. Busca o usuário no DB pelo login
            var usuario = repository.findByLogin(subject).get();

            // 4. Cria o objeto de autenticação com o usuário e suas permissões
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            // 5. Força a autenticação (coloca o usuário logado no contexto do Spring Security)
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 6. Continua o fluxo (vai para o Controller ou para o próximo filtro)
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // O token vem no formato "Bearer <token>"
            return authorizationHeader.substring(7);
        }
        return null;
    }

}
