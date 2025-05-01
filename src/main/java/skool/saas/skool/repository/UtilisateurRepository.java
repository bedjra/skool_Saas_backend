package skool.saas.skool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skool.saas.skool.Entity.Utilisateur;

import javax.management.relation.Role;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Utilisateur findByEmail(String email);
    Utilisateur findByEmailAndPasswordAndRole(String email, String password, skool.saas.skool.enums.Role role);
}

