package fr.univ_lyon1.info.m1.mes.controller;

import fr.univ_lyon1.info.m1.mes.view.CreateHPView;
import fr.univ_lyon1.info.m1.mes.view.CreatePatientView;
import fr.univ_lyon1.info.m1.mes.view.LoginView;





public class HomeController extends Controller {
    
    /**.
     * Fonction associée au bouton "Créer un compte Patient" du fxml
     * Il déclenche la création et l'affichage de la scène Création Patient
     */
    public void createPatient() {       
        CreatePatientView patientView = 
            new CreatePatientView(getStage(), getModel(), getBackView());
        removeObserverToModel(getCurrentView());
        display(patientView.getScene());
    }

    /**.
     * Fonction associée au bouton "Créer un compte professionnel" du fxml
     * Il déclenche la création et l'affichage de la scène Création Patient
     */
    public void createProfessional() {
        CreateHPView hpView =
            new CreateHPView(getStage(), getModel(), getBackView());
        removeObserverToModel(getCurrentView());
        display(hpView.getScene());
    }

    /**.
     * Fonction associée au bouton "Se Connecter" du fxml
     * Il déclenche la création et l'affichage de la scène Login
     */
    public void login() {
        LoginView loginView = new LoginView(getStage(), getModel(), getBackView());
        removeObserverToModel(getCurrentView());
        display(loginView.getScene());
    }


    
}
