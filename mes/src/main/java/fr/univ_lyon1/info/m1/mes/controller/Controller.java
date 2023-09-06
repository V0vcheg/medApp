package fr.univ_lyon1.info.m1.mes.controller;


import java.net.URL;
import java.util.ResourceBundle;

import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.view.View;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;



public abstract class Controller implements Initializable {
    private MES model;
    private View view;
    private Stage stage;
    private View currentView;
    private String id;


    /**.
     * Mutateur : initialise la fenêtre actuellement gérer par le controller
     * @param stage Stage primaire
     */
    public void setStage(final Stage stage) { 
        this.stage = stage;
    }

    /**.
     * Mutateur : Initalise le modèle du controller
     * @param model model primaire
     */
    public void setModel(final MES model) {
        this.model = model;
    }

    /**.
     * Mutateur : initialise la vue précédente
     * @param view pointe vers la vue précédente
     */
    public void setBackView(final View view) {
        this.view = view;
    }

    /**.
     * Mutateur : Initalise la vue actuelle associée au controller
     * @param view pointe vers la vue actuelle
     */
    public void setCurrentView(final View view) {
        this.currentView = view;
    }

    /**.
     * Mutateur : Initalise l'id du profil associé à la vue
     * @param login id du profil associé à la vue
     */
    public void setViewId(final String login) {
        this.id = login;
    }

    /**.
     * Accesseur 
     * @return fenêtre associé au controller
     */
    public Stage getStage() {
        return stage;
    }

    /**.
     * Accesseur 
     * @return modèle associé au controller
     */
    public MES getModel() {
        return model;
    }

    /**.
     * Accesseur 
     * @return vue précédente associée au controller
     */
    public View getBackView() {
        return view;
    }

    /**.
     * Accesseur 
     * @return vue actuelle associée au controller
     */
    public View getCurrentView() {
        return currentView;
    }

    /**.
     * Accesseur 
     * @return id du profil associé à la vue
     */
    public String getId() {
        return id;
    }


    /**.
     * Fonction native de FXML 
     * Pas nécessaire dans tous les controllers
     */
    @Override
    public void initialize(final URL url, final ResourceBundle rb) { }
    

    /**.
     * Ajoute un Observer (View) au modèle
     * @param view la vue qui vient d'être créée
     */
    public void addObserverToModel(final View view) {
        model.registerObserver(view);
    }

    /**.
     * Supprime un Observer (View) au modèle (close, logout, switch view)
     * @param view la vue qui doit être supprimée
     */
    public void removeObserverToModel(final View view) {
        model.unRegisterObserver(view);
    }


    /**.
     * Affiche sur la fenêtre une scène propre à la vue
     * @param scene la scène paramétrée 
     */
    public void display(final Scene scene) {
        stage.setScene(scene);
        stage.show();
    }


    
    
}
