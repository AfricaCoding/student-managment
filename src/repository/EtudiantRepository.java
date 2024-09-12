package repository;

import annotation.Entity;
import entity.Etudiant;

public class EtudiantRepository extends GenericFileRepository<Etudiant, String> {

    public EtudiantRepository(String nomFichier) {
        super(nomFichier, Etudiant::getEmail);
    }

    public EtudiantRepository() {
        super(getNomFichierDepuisAnnotation(), Etudiant::getEmail);
    }

    private static String getNomFichierDepuisAnnotation() {
        Entity annotation = Etudiant.class.getAnnotation(Entity.class);
        return annotation.name();
    }
}
