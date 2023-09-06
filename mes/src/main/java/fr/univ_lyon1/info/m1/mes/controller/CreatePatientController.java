package fr.univ_lyon1.info.m1.mes.controller;




import javafx.fxml.FXML;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;



public class CreatePatientController extends Controller {


    @FXML
    private TextField namePatientCreateInput;

    @FXML
    private TextField ssidCreateInput;

    @FXML
    private TextField loginPatientCreateInput;

    @FXML
    private PasswordField passwordInput;



    /**.
     * Fonction associée au bouton "S'inscrire" du fxml. 
     * Le controller lit la vue/fxml et transmet les infos au modèle
     */
    public void createPatient() {
 
        String name = namePatientCreateInput.getText();
        String ssid = ssidCreateInput.getText();         
        String login = loginPatientCreateInput.getText();
        String passwd = passwordInput.getText();  
        notifyProfilPatientCreated(login, passwd, name, ssid);
        display(getBackView().getScene());
    }

    /**.
     * Notifie le modèle de la création d'un nouveau profil patient
     * @param login login du profil
     * @param passwd mot de passe du profil
     * @param name nom du patient
     * @param ssid ssid du patient
     */
    public void notifyProfilPatientCreated(final String login, final String passwd, 
                                                    final String name, final String ssid) {
        getModel().createPatient(name, ssid);
        getModel().createProfil(login, passwd, "patient", ssid);

    }


    /**.
     * Supprime l'observation de la vue Création Patient du modèle et
     * retourne à la page d'accueil
     */
    public void backHome() {
        removeObserverToModel(getCurrentView());
        display(getBackView().getScene());
    }
   


    
}
