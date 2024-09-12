### Énoncé du Projet : Système de Gestion des Étudiants avec Validation Automatique

#### Contexte :
Vous allez développer une application Java permettant de gérer des étudiants. L'application devra respecter les principes SOLID et utiliser une architecture en trois tiers : interface utilisateur (UI), service, et repository (données). L'application permettra d'ajouter, de modifier, d'afficher, et de supprimer des étudiants avec validation automatique des données via des annotations personnalisées. Les informations des étudiants seront sauvegardées dans un fichier binaire.

#### Fonctionnalités demandées :

1. **Ajout d'un étudiant** :
    - Les utilisateurs doivent pouvoir ajouter des étudiants en renseignant les informations suivantes :
        - Nom
        - Prénom
        - Email
        - Téléphone
        - Filière

2. **Validation automatique** :
    - Utilisation de **réflexion** pour valider les données saisies par l'utilisateur à l'aide d'annotations personnalisées et d'expressions régulières.
    - Les validations suivantes doivent être effectuées :
        - **Nom et prénom** : Ne doivent pas contenir de caractères spéciaux.
        - **Email** : Doit être valide.
        - **Téléphone** : Doit respecter un format prédéfini (ex. `(xxx)-xxx-xx-xx`).
        - **Champs obligatoires** : Aucun champ ne doit être laissé vide.

3. **Modification d'un étudiant** :
    - L'application doit permettre de modifier les informations d'un étudiant existant en se basant sur l'email comme identifiant unique.

4. **Affichage de tous les étudiants** :
    - Il doit être possible d'afficher la liste complète des étudiants enregistrés dans le système.

5. **Suppression d'un étudiant** :
    - Les utilisateurs doivent pouvoir supprimer un étudiant du système en utilisant son email.

6. **Sauvegarde et chargement des données** :
    - Les informations des étudiants doivent être sauvegardées dans un fichier binaire dont le nom est extrait dynamiquement de l'annotation `@Entity(name="nom_du_fichier")`.
    - Lors du démarrage de l'application, les données doivent être chargées automatiquement depuis ce fichier.

#### Détails techniques :

1. **Annotations personnalisées pour la validation** :
    - `@NotBlank`: S'assure qu'un champ n'est pas vide.
    - `@Email`: Valide le format d'un email.
    - `@Phone`: Valide le format d'un numéro de téléphone avec une expression régulière personnalisable.
    - `@ValidName`: Valide que le nom ou prénom ne contient pas de caractères spéciaux.

2. **Architecture en trois tiers** :
    - **UI (Interface Utilisateur)** : Une interface console permettant aux utilisateurs d'interagir avec le système.
    - **Service** : Gestion de la logique métier, incluant la validation des données et la gestion des erreurs.
    - **Repository** : Gestion de la persistance des données dans un fichier binaire.

3. **Repository générique** :
    - Implémentez un repository générique (`GenericFileRepository<T>`) pour gérer la persistance des entités. Ce repository doit être capable de sauvegarder, charger, modifier, et supprimer des entités en utilisant un fichier.
    - L'entité `Etudiant` sera sauvegardée dans un fichier dont le nom est défini dans l'annotation `@Entity`.

4. **Validation via Réflexion** :
    - Le système utilisera la réflexion pour parcourir les champs de l'entité `Etudiant` et valider chaque champ selon les annotations présentes.

#### Instructions pour le développeur :

1. **Création des annotations** :
    - Implémentez les annotations `@NotBlank`, `@Email`, `@Phone`, et `@ValidName` pour gérer la validation des données saisies par l'utilisateur.

2. **Implémentation des entités** :
    - Créez la classe `Etudiant` qui représente un étudiant avec les champs suivants : nom, prénom, email, téléphone, et filière.
    - Appliquez les annotations de validation sur les champs correspondants.

3. **Mise en place du repository** :
    - Créez une interface `GenericRepository<T>` définissant les opérations CRUD (Create, Read, Update, Delete).
    - Implémentez un repository générique (`GenericFileRepository<T>`) capable de sauvegarder et charger des entités dans un fichier binaire.

4. **Service de gestion des étudiants** :
    - Créez la classe `EtudiantService` pour encapsuler la logique métier. Cette classe doit gérer l'ajout, la modification, la suppression et l'affichage des étudiants tout en s'assurant que les données sont correctement validées.

5. **Interface utilisateur (UI)** :
    - Créez une interface utilisateur simple en ligne de commande (CLI) pour permettre l'interaction avec le système. L'utilisateur doit pouvoir ajouter, modifier, supprimer et afficher des étudiants à partir de cette interface.

6. **Gestion des erreurs et validations** :
    - Lors de la saisie des données par l'utilisateur, assurez-vous que les erreurs de validation sont détectées et signalées de manière claire. Si une erreur est détectée, l'utilisateur doit être invité à saisir à nouveau les données.

#### Livrables :

1. Code source complet incluant les annotations, l'entité `Etudiant`, le repository, le service, et l'interface utilisateur.
2. Documentation expliquant comment exécuter l'application et un guide sur les différentes fonctionnalités.
3. Capture d'écran démontrant l'ajout, la modification, la suppression et l'affichage des étudiants.

### Exemple de scénario d'utilisation :

- **Utilisateur** : "Je veux ajouter un étudiant avec les informations suivantes : Nom : Dupont, Prénom : Jean, Email : jean.dupont@example.com, Téléphone : (123)-456-78-90, Filière : Informatique."
- **Système** : Vérifie automatiquement si toutes les informations sont valides (par exemple, le format de l'email et du téléphone, les champs non vides).
- **Utilisateur** : Si toutes les données sont valides, l'étudiant est ajouté avec succès.