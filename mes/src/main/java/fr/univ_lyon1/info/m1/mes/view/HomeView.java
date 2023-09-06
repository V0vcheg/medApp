package fr.univ_lyon1.info.m1.mes.view;


import fr.univ_lyon1.info.m1.mes.model.MES;


import javafx.stage.Stage;


public class HomeView extends View {

    /**.
     * Initialisation des données dont la vue a besoin
     * @param stage fenêtre de l'application
     * @param model modèle partagé par les vues et les controller
     */
    public HomeView(final Stage stage, final MES model) {
        super(stage, model);          
        initView("fxml/Home.fxml", false);
        getStage().setTitle("Mon Espace Santé");    
        getController().setBackView(this);
        getController().display(getScene());
    }    

    
   
}
