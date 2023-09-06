package fr.univ_lyon1.info.m1.mes.view;


import java.io.IOException;


import fr.univ_lyon1.info.m1.mes.controller.Controller;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Profil;
import fr.univ_lyon1.info.m1.mes.observer.Observer;
import fr.univ_lyon1.info.m1.mes.utils.EasyImage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public abstract class View implements Observer {
    private Controller controller;
    private Scene scene;
    private MES model;    
    private Stage stage;
    private View backView;
    private String id;
    
    /**.
     * Initialation de la fenêtre principale et du modèle
     * @param stage fenêtre principale de l'application
     * @param model modèle partagé par les vues et les controller
     */
    public View(final Stage stage, final MES model) {
        super();
        this.stage = stage;
        this.model = model;        
    
    }

    /**.
     * Mutateurs des données de la vue
     */


    public final void setScene(final Scene scene) {
        this.scene = scene;
    }

    public final void setBackView(final View view) {
        this.backView = view;
    }

    public void setController(final Controller c) {
        this.controller = c;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setSession(final Profil p) {  }
 
    /**.
     * Accesseurs des données de la vue
     */


    public final Stage getStage() {
        return stage;
    }

    public final Scene getScene() {
        return scene;
    }

    public final MES getModel() {
        return model;
    }

    public Controller getController() {
        return controller;
    }

    public String getId() {
        return id;
    }

    /**.
     * Initialisation des données en récupérant les informations du controller
     * @param chemin vers le fichier fxml associé à la vue
     * @param modifFXML true si la vue effectue des modifications sur le fxml false sinon
     */
    public void initView(final String chemin, final boolean modifFXML) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(chemin));
            AnchorPane root = (AnchorPane) loader.load();                           
            controller = loader.getController();
            controller.setCurrentView(this);            
            controller.setModel(model);
            controller.addObserverToModel(this);
            controller.setStage(stage);
            if (modifFXML) {                
                controller.setViewId(id); 
            }    
            stage.getIcons().setAll(EasyImage.findImage("src/main/img/healthcare.png"));
            scene = new Scene(root);                                

            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**.
     * Foncton implémentée par les Observer, toutes les vues n'ont pas besoin d'être update
     */
    public void update() { }

 
}
