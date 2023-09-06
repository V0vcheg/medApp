package fr.univ_lyon1.info.m1.mes.controller;

import javafx.fxml.FXML;

import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateHPController extends Controller {

    @FXML
    private TextField nameHPCreateInput;

    @FXML
    private ComboBox domainCreateInput;

    @FXML
    private TextField loginHPCreateInput;

    @FXML
    private PasswordField passwordHPInput;



    /**.
     * Fonction  associée au bouton inscription dans le fxml. 
     * Le controller lit la vue/fxml et transmet les infos au modele
     */
    public void createProfessionalProfil() {
 
        String name = nameHPCreateInput.getText();
        String domain = domainCreateInput.getSelectionModel().getSelectedItem().toString();        
        String login = loginHPCreateInput.getText();        
        String password = passwordHPInput.getText();
        notifyProfilHPCreated(login, password, name, domain);
        display(getBackView().getScene());   
        
    }

    /**.
     * Avertit le modèle de la création d'un nouveau profil hp
     * @param login login du profil
     * @param passwd mot de passe du profil
     * @param name nom du professionnel (en général Dr.Qqchose)
     * @param domain domaine d'activité du professionnel 
     * (voir dans le dao l'initalisation de la liste)
     */
    public void notifyProfilHPCreated(final String login, final String passwd, 
                                                final String name, final String domain) {
        getModel().createHealthProfessional(domain, name);
        getModel().createProfil(login, passwd, "professional", name);
    }


    /**.
     * Supprime l'observation de la vue Création professionnel du modèle et
     * retourne à la page d'accueil
     */
    public void backHome() {
        removeObserverToModel(getCurrentView());
        display(getBackView().getScene());
    }

    /**.
     * Renvoit la liste déroulante dans le but de l'initialiser
     */
    public ComboBox getDomainList() {
        return domainCreateInput;
    }


}
