package skool.saas.skool.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skool.saas.skool.Entity.Utilisateur;
import skool.saas.skool.enums.Role;
import skool.saas.skool.repository.UtilisateurRepository;

import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ConfigurationService configurationService;


    public boolean authenticate(String email, String password, Role role) {
        // Vérifier si la licence est encore valide
        if (!configurationService.licenceEstValide()) {
            return false; // On bloque la connexion
        }

        // Si la licence est valide, on continue normalement
        Utilisateur utilisateur = utilisateurRepository.findByEmailAndPasswordAndRole(email, password, role);
        return utilisateur != null;
    }


    public String getRoleByEmail(String email) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur != null && utilisateur.getRole() != null) {
            return utilisateur.getRole().name();
        }
        return null;
    }

    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }

}
