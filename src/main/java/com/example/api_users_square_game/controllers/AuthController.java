package com.example.api_users_square_game.controllers;

import com.example.api_users_square_game.dao.UserDao;
import com.example.api_users_square_game.dto.LoginRequest;
import com.example.api_users_square_game.models.User;
import com.example.api_users_square_game.services.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentification", description = "Connection des utilisateurs")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDao userDao;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserDao userDao) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDao = userDao;
    }

    @Operation(
            summary = "Connecter les utilisateurs",
            description = "Connecte les utilisateurs."
    )
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            User user = userDao.findByUsername(request.getUsername());

            String token = jwtService.generateToken(
                    user.getId(),
                    user.getUsername(),
                    user.getRole()
            );

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Identifiants invalides");
        }
    }
}
