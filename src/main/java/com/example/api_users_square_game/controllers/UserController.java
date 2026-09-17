package com.example.api_users_square_game.controllers;

import com.example.api_users_square_game.models.User;
import com.example.api_users_square_game.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public Collection<User> getUsers() {
        return userService.getUsers();
    }

    @Operation(
            summary = "Créer un utilisateur",
            description = "Crée un utilisateur."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur créée"),
    })
    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @Operation(
            summary = "Récupérer un utilisateur",
            description = "Retourne les informations d'un utilisateur."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur trouvée"),
            @ApiResponse(responseCode = "404", description = "Utilisateur inconnue")
    })
    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }


    @Operation(
            summary = "Supprimer un utilisateur",
            description = "Supprime un utilisateur."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur supprimée"),
            @ApiResponse(responseCode = "404", description = "Utilisateur inconnue")
    })
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @Operation(
            summary = "Validitéde l'utilisateur",
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
