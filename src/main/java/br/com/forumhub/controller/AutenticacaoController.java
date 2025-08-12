package br.com.forumhub.controller;

import br.com.forumhub.dto.LoginRequest;
import br.com.forumhub.dto.TokenResponse;
import br.com.forumhub.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> autenticar(@RequestBody @Valid LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        String token = tokenService.gerarToken(authentication.getName());

        return ResponseEntity.ok(new TokenResponse(token));
    }
}
