package fr.univ_lyon1.info.m1.mes.controller;

import fr.univ_lyon1.info.m1.mes.utils.EasyAlert;
import fr.univ_lyon1.info.m1.mes.view.ProfilHPView;
import fr.univ_lyon1.info.m1.mes.view.ProfilPatientView;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends Controller {

    @FXML
    private TextField loginInput;

    @FXML
    private PasswordField passwordInput;
    
    /**.
     * Cette fonction est associé au bouton "Se connecter" du fxml
     * Lecture de la vue avec les champs entrés par l'utilisateur et 
     * création du profil en fonction de ces champs puis affichage de la scène
     */
    public void login() {
        final String login = loginInput.getText();
        final String password = passwordInput.getText();
        if (getModel().login(login, password)) {
            removeObserverToModel(getCurrentView());
            if (getModel().getProfilType(login).equals("patient")) {
                ProfilPatientView profil = 
                new ProfilPatientView(getStage(), getModel(), getBackView(), login);            
                display(profil.getScene());
            } else {
                ProfilHPView profil = 
                new ProfilHPView(getStage(), getModel(), getBackView(), login);            
                display(profil.getScene());
            }
            
        } else {
            EasyAlert.alert("Aucun compte trouvé.");
            return;
        }
    }

 
    /**.
     * Supprime l'observation de la vue Login du modèle et
     * retourne à la page d'accueil
     */
    public void backHome() {
        removeObserverToModel(getCurrentView());
        display(getBackView().getScene());
    }

    
}

