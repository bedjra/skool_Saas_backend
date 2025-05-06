package skool.saas.skool.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skool.saas.skool.Entity.Utilisateur;
import skool.saas.skool.config.JwtUtil;
import skool.saas.skool.enums.Role;
import skool.saas.skool.service.ConfigurationService;
import skool.saas.skool.service.UtilisateurService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")

@Tag(name = "user", description = "Gestion des utilisateurs")

public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private JwtUtil jwtUtil;

    // Obtenir tous les rôles
    @Operation(summary = "Récupérer tous les roles")
    @GetMapping("/roles")
    public ResponseEntity<Role[]> getAllRoles() {
        return ResponseEntity.ok(Role.values());
    }

    // Obtenir tous les utilisateurs
    @Operation(summary = "Récupérer tous les utilisateurs")
    @GetMapping("/utilisateur")
    public ResponseEntity<List<Utilisateur>> getAllUtilisateur() {
        List<Utilisateur> utilisateurs = utilisateurService.getAll();
        return ResponseEntity.ok(utilisateurs);
    }

    // Authentification utilisateur
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(@RequestBody Utilisateur utilisateur) {
//        System.out.println("Email: " + utilisateur.getEmail() + ", Password: " + utilisateur.getPassword() + ", Role: " + utilisateur.getRole());
//
//        Map<String, Object> response = new HashMap<>();
//
//        // Vérifier si la licence est encore valide
//        if (!configurationService.licenceEstValide()) {
//            response.put("success", false);
//            response.put("message", "Licence expirée. Veuillez contacter l’administration.");
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response); // 403 interdit
//        }
//
//        // Authentification de l'utilisateur
//        boolean isAuthenticated = utilisateurService.authenticate(utilisateur.getEmail(), utilisateur.getPassword(), utilisateur.getRole());
//
//        if (isAuthenticated) {
//            response.put("success", true);
//            response.put("message", "Login réussi");
//            response.put("role", utilisateur.getRole());
//            return ResponseEntity.ok(response);
//        } else {
//            response.put("success", false);
//            response.put("message", "Identifiants ou rôle incorrects");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//        }
//    }

    @Operation(summary = "Connexion ")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Utilisateur utilisateur) {
        Map<String, Object> response = new HashMap<>();

        if (!configurationService.licenceEstValide()) {
            response.put("success", false);
            response.put("message", "Licence expirée. Veuillez contacter l’administration.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        boolean isAuthenticated = utilisateurService.authenticate(utilisateur.getEmail(), utilisateur.getPassword(), utilisateur.getRole());

        if (isAuthenticated) {
            String token = jwtUtil.generateToken(utilisateur.getEmail(), utilisateur.getRole().name());
            response.put("success", true);
            response.put("token", token);
            response.put("message", "Login réussi");
            response.put("role", utilisateur.getRole());
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Identifiants ou rôle incorrects");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }



    // Enregistrement d'un utilisateur
    @Operation(summary = "inscription d'un compte")
    @PostMapping("/save")
    public ResponseEntity<Utilisateur> saveUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur savedUser = utilisateurService.saveUtilisateur(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Obtenir le rôle d'un utilisateur par son email
    @Operation(summary = "Récupérer tous le role qui s est connecté")
    @GetMapping("/role/{email}")
    public ResponseEntity<String> getRoleByEmail(@PathVariable String email) {
        String role = utilisateurService.getRoleByEmail(email);
        if (role != null) {
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rôle introuvable");
        }
    }


}
