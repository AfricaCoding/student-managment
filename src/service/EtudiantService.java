package service;

import entity.Etudiant;
import repository.GenericRepository;
import validator.Validator;

import java.util.List;
import java.util.Optional;

public class EtudiantService {

    private final GenericRepository<Etudiant, String> etudiantRepository;

    public EtudiantService(GenericRepository<Etudiant, String> etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        try {
            if (Validator.validate(etudiant)) {
                etudiantRepository.save(etudiant);
                System.out.println("Étudiant ajouté avec succès.");
            } else {
                System.out.println("Erreur de validation. Veuillez vérifier les informations saisies.");
            }
        } catch (IllegalAccessException e) {
            System.out.println("Erreur de validation : " + e.getMessage());
        }
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
