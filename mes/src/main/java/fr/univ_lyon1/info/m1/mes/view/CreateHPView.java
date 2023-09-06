package fr.univ_lyon1.info.m1.mes.view;

import fr.univ_lyon1.info.m1.mes.controller.CreateHPController;
import fr.univ_lyon1.info.m1.mes.model.MES;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class CreateHPView extends View {

    private CreateHPController controller;

    /**.
     * Initialisation des données dont la vue a besoin
     * @param stage fenêtre de l'application
     * @param model modèle partagé par les vues et les controller
     * @param home vue de la page d'accueil
     */
    public CreateHPView(final Stage stage, final MES model, final View home) {
        super(stage, model);       
        initView("fxml/CreateHP.fxml", false); 
        controller = (CreateHPController) getController();
        controller.setBackView(home);
        initList();
    }   

    /**.
     * Initialise la ComboBox contenant les domaines d'expertise
     */
    public void initList() {
        ObservableList<String> list
                    = FXCollections.observableArrayList();         
        for (String p : getModel().getAllDomainHP()) {
             list.add(p);
        }        
        controller.getDomainList().setItems(list);   
        controller.getDomainList().getSelectionModel().select(0);   

    }

   
  
}
