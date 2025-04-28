package skool.saas.skool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skool.saas.skool.Entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmailAndPasswordAndRole(String email, String password, com.eschoolback.eschool.enums.Role role);
    Utilisateur findByEmail(String email);

}
