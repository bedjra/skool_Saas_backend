package skool.saas.skool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skool.saas.skool.Entity.Configuration;
import skool.saas.skool.repository.ConfigurationRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    // Sauvegarde ou mise à jour d'une configuration
    public Configuration saveConfiguration(Configuration config) {
        return configurationRepository.save(config);
    }

    // Récupérer toutes les configurations
    public List<Configuration> getAllConfigurations() {
        return configurationRepository.findAll();
    }

    // Récupérer une configuration par ID
    public Optional<Configuration> getConfigurationById(Long id) {
        return configurationRepository.findById(id);
    }

    // Supprimer une configuration
    public void deleteConfiguration(Long id) {
        configurationRepository.deleteById(id);
    }

    public byte[] getImage() {
        return configurationRepository.findImage();
    }


    //Licence
    public boolean licenceEstValide() {
        List<Configuration> configurations = configurationRepository.findAll();
        if (configurations.isEmpty()) {
            return false; // Pas de configuration, donc licence invalide
        }

        Configuration config = configurations.get(0); // On prend la première configuration
        Date aujourdHui = new Date();

        // La licence est valide si on est entre licenceDebut et licenceExpire
        return aujourdHui.after(config.getLicenceDebut()) && aujourdHui.before(config.getLicenceExpire());
    }



}
