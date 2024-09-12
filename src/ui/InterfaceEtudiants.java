package ui;

import entity.Etudiant;
import repository.EtudiantRepository;
import service.EtudiantService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class InterfaceEtudiants {

    private final EtudiantService etudiantService;

    public InterfaceEtudiants() {
        String nomFichier = "etudiant.bin";  // Dynamique via annotation @Entity
        EtudiantRepository etudiantRepository = new EtudiantRepository(nomFichier);
        etudiantService = new EtudiantService(etudiantRepository);
    }

    public void demarrer() {
        Scanner scanner = new Scanner(System.in);
        String choix;

        do {
            afficherMenu();
            choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    ajouterEtudiant(scanner);
                    break;
                case "2":
                    afficherEtudiants();
                    break;
                case "3":
                    modifierEtudiant(scanner);
                    break;
                case "4":
                    supprimerEtudiant(scanner);
                    break;
                case "0":
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (!choix.equals("0"));

        scanner.close();
    }

    private void afficherEtudiants() {
        etudiantService.getTousLesEtudiants().forEach(System.out::println);
    }

    private void modifierEtudiant(Scanner scanner) {
        System.out.print("Email de l'étudiant à modifier: ");
        String email = scanner.nextLine();
        Optional<Etudiant> foundEtudiant = etudiantService.getEtudiantParEmail(email);
        if (foundEtudiant.isEmpty()) {
            System.out.println("Erreur : Aucun étudiant trouvé avec cet email.");
            return;
        }
        System.out.print("Nouveau nom: ");
        String nouveauNom = scanner.nextLine();
        System.out.print("Nouveau prénom: ");
        String nouveauPrenom = scanner.nextLine();
        System.out.print("Nouveau téléphone: ");
        String nouveauTelephone = scanner.nextLine();
        System.out.print("Nouvelle filière: ");
        String nouvelleFiliere = scanner.nextLine();

        Etudiant etudiant = foundEtudiant.get();
        if (!nouveauNom.isBlank()) {
            etudiant.setNom(nouveauNom);
        }
        if (!nouveauPrenom.isBlank()) {
            etudiant.setPrenom(nouveauPrenom);
        }
        if (!email.isBlank()) {
            etudiant.setEmail(email);
        }
        if (!nouveauTelephone.isBlank()) {
            etudiant.setTelephone(nouveauTelephone);
        }
        if (!nouvelleFiliere.isBlank()) {
            etudiant.setFiliere(nouvelleFiliere);
        }
        etudiantService.modifierEtudiant(etudiant);

    }

    private void supprimerEtudiant(Scanner scanner) {
        System.out.print("Email de l'étudiant à supprimer: ");
        String email = scanner.nextLine();
        etudiantService.supprimerEtudiant(email);
        System.out.println("Etudiant supprimé.");
    }

    private void ajouterEtudiant(Scanner scanner) {
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Prénom: ");
        String prenom = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Téléphone: ");
        String telephone = scanner.nextLine();
        System.out.print("Filière: ");
        String filiere = scanner.nextLine();

        Etudiant etudiant = new Etudiant(nom, prenom, email, telephone, filiere);
        List<String> erreurs = etudiantService.ajouterEtudiant(etudiant);

        if (!erreurs.isEmpty()) {
            // Display errors if any
            System.out.println("Erreurs trouvées : ");
            erreurs.forEach(System.out::println);
            // Optionally, prompt the user to re-enter the fields with errors
            ajouterEtudiant(scanner);  // Recursively prompt for input again if validation failed
        }
    }

    private void afficherMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Ajouter un étudiant");
        System.out.println("2. Afficher tous les étudiants");
        System.out.println("3. Modifier un étudiant");
        System.out.println("4. Supprimer un étudiant");
        System.out.println("0. Quitter");
        System.out.print("Choisissez une option : ");
    }

    public static void main(String[] args) {
        InterfaceEtudiants app = new InterfaceEtudiants();
        app.demarrer();
    }
}
