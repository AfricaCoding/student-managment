package service;

import entity.Etudiant;
import repository.GenericRepository;
import validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EtudiantService {

    private final GenericRepository<Etudiant, String> etudiantRepository;
    private final Validator validator;

    public EtudiantService(GenericRepository<Etudiant, String> etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
        this.validator = new Validator();
    }

    public List<String> ajouterEtudiant(Etudiant etudiant) {
        List<String> errors = new ArrayList<>();
        try {
            // Validate the Etudiant object
            errors = validator.validate(etudiant);
            if (errors.isEmpty()) {
                // If no validation errors, save the student
                etudiantRepository.save(etudiant);
                System.out.println("Étudiant ajouté avec succès.");
            } else {
                System.out.println("Échec de l'ajout de l'étudiant en raison des erreurs de validation.");
            }
        } catch (IllegalAccessException e) {
            System.out.println("Erreur lors de la validation : " + e.getMessage());
        }
        return errors;  // Return errors to the UI to be displayed
    }

    public void modifierEtudiant(Etudiant etudiantModifie) {
        etudiantRepository.save(etudiantModifie);
    }

    public void supprimerEtudiant(String email) {
        etudiantRepository.deleteById(email);
    }

    public List<Etudiant> getTousLesEtudiants() {
        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> getEtudiantParEmail(String email) {
        return etudiantRepository.findById(email);
    }
}
