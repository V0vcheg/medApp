# Gestion de Projet et Génie Logiciel, M1, département informatique, Lyon 1

## Projet Mon espace santé

### Fonctionnalités implémentées sous forme d'arbre décisionnel  :

![Alt text](/mes/src/main/img/arbre_décisionnel.jpg?raw=true "Arbre de décision")


### Aperçu de l'application : 

![Alt text](/mes/src/main/img/ProfilView.png?raw=true "Profil Patient et Pro")

### NB  : 
![Alt text](/mes/src/main/img/Capture.PNG?raw=true "Erreur fxml")

Nous avons rencontré la même erreur plusieurs fois sur le chargement des FXML. La seule solution que nous avons trouvé pour enlever cette erreur est de ctrl+A puis ctrl+X puis ctrl+V sur tous les fichiers fxml du dossier view/fxml et mvn compile. Nous avons rencontré le problème sur 3 machines différentes et cette technique a fait disparaître (miraculeusement) l'erreur à chaque fois. 

Cette erreur est de source inconnu et aléaatoire donc difficila à prévoir, voici un lien vers un foum qui regroupe quelques solutions (ce problème est apparemment récurent) : [Forum] https://stackoverflow.com/questions/17228487/javafx-location-is-not-set-error-message


Nous fournissions une documentation détaillée accessible depuis apidocs/index.html générée avec maven (mvn javadoc:javadoc).  

Pour tester l'application il y a des comptes pré-configurés avec login/password dans initConfig.xml. Exemple: login = alice.foo psswd = aa