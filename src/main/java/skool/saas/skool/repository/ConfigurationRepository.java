package skool.saas.skool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import skool.saas.skool.Entity.Configuration;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {

    @Query("SELECT c.image FROM Configuration c WHERE c.id = (SELECT MIN(c2.id) FROM Configuration c2)")
    byte[] findImage();
}
