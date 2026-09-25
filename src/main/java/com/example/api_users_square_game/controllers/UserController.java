package com.example.api_users_square_game.controllers;

import com.example.api_users_square_game.dto.UserResponseDto;
import com.example.api_users_square_game.models.User;
import com.example.api_users_square_game.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@Tag(name = "Users", description = "Gestion des utilisateurs")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Lister les utilisateurs",
            description = "Retourne les utilisateurs."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des utilisateurs récupérée"),
    })
    @GetMapping
    public Collection<UserResponseDto> getUsers() {

        return userService.getUsers()
                .stream()
                .map(UserResponseDto::fromUser)
                .toList();
    }

    @Operation(
            summary = "Créer un utilisateur",
            description = "Crée un utilisateur."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur créée"),
            @ApiResponse(responseCode = "401", description = "Requête non authorisée"),
    })
    @PostMapping
    public UserResponseDto createUser(@RequestBody User user) {
        User createUser = userService.createUser(user);
        return UserResponseDto.fromUser(createUser);
    }

    @Operation(
            summary = "Récupérer un utilisateur",
            description = "Retourne les informations d'un utilisateur."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur trouvée"),
            @ApiResponse(responseCode = "404", description = "Utilisateur inconnue")
    })
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #id == authentication.principal.id)")
    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        return UserResponseDto.fromUser(user);
    }


    @Operation(
            summary = "Supprimer un utilisateur",
            description = "Supprime un utilisateur."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur supprimée"),
            @ApiResponse(responseCode = "404", description = "Utilisateur inconnue")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @Operation(
            summary = "Validité de l'utilisateur",
            description = "Retourne si l'utilisateur est valide."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status de l'utilisateur "),
    })
    @GetMapping("/{id}/valid")
    public boolean isValidUser(@PathVariable UUID id) {
        return userService.existById(id);
    }


}
