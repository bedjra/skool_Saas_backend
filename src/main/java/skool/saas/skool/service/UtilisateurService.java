package skool.saas.skool.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skool.saas.skool.Entity.Utilisateur;
import skool.saas.skool.repository.UtilisateurRepository;

import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public boolean authenticate(String email, String password, com.eschoolback.eschool.enums.Role role) {
        Utilisateur utilisateur = utilisateurRepository.findByEmailAndPasswordAndRole(email, password, role);
        return utilisateur != null;
    }

    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public String getRoleByEmail(String email) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur != null) {
            return utilisateur.getRole().name();  // Retourne le rôle sous forme de chaîne
        }
        return null;
    }

    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }
}
