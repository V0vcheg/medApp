package fr.univ_lyon1.info.m1.mes.view;



import fr.univ_lyon1.info.m1.mes.controller.CreatePatientController;
import fr.univ_lyon1.info.m1.mes.model.MES;
import javafx.stage.Stage;

public class CreatePatientView extends View {

    private CreatePatientController controller;

    /**.
     * Initialisation des données dont la vue a besoin
     * @param stage fenêtre de l'application
     * @param model modèle partagé par les vues et les controller
     * @param home vue de la page d'accueil
     */
    public CreatePatientView(final Stage stage, final MES model, final View home) {
        super(stage, model);       
        initView("fxml/CreatePatient.fxml", false); 
        controller = (CreatePatientController) getController();
        controller.setBackView(home);
    }    

    @Override
    public void update() {
        controller.backHome();
    }
    
}
