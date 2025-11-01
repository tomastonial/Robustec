package com.cesurg.enchentes.core.usecase;

import com.cesurg.enchentes.Security.Jwt.JwtUtils;
import com.cesurg.enchentes.core.dto.AcessDto;
import com.cesurg.enchentes.core.dto.AuthenticationDTO;
import com.cesurg.enchentes.core.dto.JwtResponse;
import com.cesurg.enchentes.core.usecase.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public AcessDto login(AuthenticationDTO dto) {
        try {
            // Autentica usuário + senha
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );

            // Pega detalhes do usuário autenticado
            UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

            // Gera token
            String token = jwtUtils.generateTokenFromUserDetailsImpl(userDetails);

            return new AcessDto(token, userDetails.getUsername(), userDetails.getRole().name(), userDetails.getId());

        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos");
        }
    }
}
