package entity;

import annotation.Email;
import annotation.Entity;
import annotation.NotBlank;
import annotation.Phone;
import annotation.ValidName;

import java.io.Serializable;

@Entity(name = "etudiant.bin")
public class Etudiant implements Serializable {

    @NotBlank
    @ValidName
    private String nom;

    @NotBlank
    @ValidName
    private String prenom;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Phone
    private String telephone;

    @NotBlank
    private String filiere;

    public Etudiant(String nom, String prenom, String email, String telephone, String filiere) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.filiere = filiere;
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    @Override
    public String toString() {
        return "Étudiant{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", filiere='" + filiere + '\'' +
                '}';
    }
}
