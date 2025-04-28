package skool.saas.skool.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skool.saas.skool.Entity.Utilisateur;
import skool.saas.skool.service.UtilisateurService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/roles")
    public List<com.eschoolback.eschool.enums.Role> getAllRoles() {
        return Arrays.asList(com.eschoolback.eschool.enums.Role.values());
    }

//    @GetMapping("/utilisateur")
//    public List<com.eschoolback.eschool.Entity.Utilisateur> getAllUtilisateur() {
//        return utilisateurService.getAll();
//    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Utilisateur utilisateur) {
        System.out.println("Email: " + utilisateur.getEmail() + ", Password: " + utilisateur.getPassword() + ", Role: " + utilisateur.getRole());
        boolean isAuthenticated = utilisateurService.authenticate(utilisateur.getEmail(), utilisateur.getPassword(), utilisateur.getRole());

        Map<String, Object> response = new HashMap<>();
        if (isAuthenticated) {
            response.put("success", true);
            response.put("message", "Login réussi");
            response.put("role", utilisateur.getRole());
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Identifiants ou rôle incorrects");
            return ResponseEntity.status(401).body(response);
        }
    }

    @PostMapping("/save")
    public Utilisateur saveUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.saveUtilisateur(utilisateur);
    }

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
