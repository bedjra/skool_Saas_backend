package skool.saas.skool.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String adresse;
    private String devise;
    private String tel;
    private String cel;
    private String bp;
    @Lob
    private byte[] image;
    private Date licenceExpire;
    private Date licenceDebut;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getLicenceExpire() {
        return licenceExpire;
    }

    public void setLicenceExpire(Date licenceExpire) {
        this.licenceExpire = licenceExpire;
    }

    public Date getLicenceDebut() {
        return licenceDebut;
    }

    public void setLicenceDebut(Date licenceDebut) {
        this.licenceDebut = licenceDebut;
    }
}
