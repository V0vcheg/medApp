package fr.univ_lyon1.info.m1.mes.view;



import java.util.ArrayList;
import java.util.List;

import com.gluonhq.charm.glisten.control.CardPane;

import fr.univ_lyon1.info.m1.mes.controller.ProfilHPController;

import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Meeting;
import fr.univ_lyon1.info.m1.mes.model.Message;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.model.Profil;
import fr.univ_lyon1.info.m1.mes.strategy.Strategy;
import fr.univ_lyon1.info.m1.mes.utils.EasyAlert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProfilHPView extends View {

    private Profil profil;

    private String selectedPatient;

    private ProfilHPController controller;


                
    /**.
     * Initialisation des données dont la vue a besoin
     * @param stage fenêtre principale de l'application
     * @param model modèle partagé par les vues et les controller
     * @param home vue de la page d'accueil
     * @param id id du profil connecté
     */
    public ProfilHPView(final Stage stage, final MES model, final View home, final String id) {
        super(stage, model);  
        setId(id); // = login
        initView("fxml/ProfilHP.fxml", true);  
        controller = new ProfilHPController(); 
        controller = (ProfilHPController) getController();  
        controller.setBackView(home);
        initPanel(); 
    }

    @Override
    public void setSession(final Profil profil) {
        this.profil = profil;                 
    }


    /**.
     * Remplit la CardPane contenant les prescriptions d'un patient
     * @param strategy stratégie de la dernière recherche effectué
     */
    public void showPrescription(final Strategy strategy) {        
        AnchorPane root = controller.getPrescriptionPatientPane();  
        root.getChildren().clear();
        selectedPatient = controller.getInputSearch().getText();
        Patient p = getModel().search(strategy, selectedPatient);   
        if (p != null) {
            List<Prescription> listPresc = getModel().getPrescPatient(p.getSSID());
        Pane presc = new VBox();
        
        if (!listPresc.isEmpty()) {
            for (final Prescription pr : listPresc) {
                if (pr.getHealthProfessional().equals(profil.getIdProfil())) {
                    final HBox pView = new HBox();
                    final Label content = new Label(pr.getContent());
                    final Button removeBtn = new Button("x");
                    removeBtn.setOnAction((event) -> { 
                        controller.notifyPrescriptionRemoved(pr.getId(), 
                                                                p.getSSID());
                    });
                    pView.getChildren().addAll(content, removeBtn);
                    presc.getChildren().add(pView);
                }              
                
            }
        } else {
            final HBox pView = new HBox();
            final Label content = new Label("Vous n'avez encore rien prescrit à ce patient");
            pView.getChildren().addAll(content);
            presc.getChildren().add(pView);
        }
            root.getChildren().add(presc);
        }  else {
            EasyAlert.alert("Select Patient First");
        }  
    }

    /**.
     * Initialisation de toute la vue  
     */
    public void initPanel() {
        showDefaultPrescription();
        showMeeting();
        showChat();
    }

    /**.
     * Remplit la CardPane contenant les prescriptions par défaut
     */
    public void showDefaultPrescription() {
        CardPane root = controller.getDefaultPrescPane();
        root.getItems().clear();
        List<Prescription> defPresc = getModel().getHP(profil.getIdProfil()).getPredefPrescr();
        for (Prescription presc : defPresc) {
            final Label content = new Label(presc.getContent() + "\n(" 
                                                        + presc.getComment() + ")");  
            content.setOnMouseClicked((event) -> {
                Strategy strategy = controller.getStrategyList().getSelectionModel()
                                                                        .getSelectedItem();

                Patient p = getModel().search(strategy, selectedPatient);                
                if (p != null) {
                    controller.notifyPrescriptionAdd(p.getSSID(), presc.getContent());
                }
                
            });              
            root.getItems().add(content);
        }
    }

    /**.
     * Remplit la CardPane contenant les rdv
     */
    public void showMeeting() {
        final List<Meeting> meetings = getModel().getHPMeetings(profil.getIdProfil());
        CardPane root = controller.getMeetPane();
        root.getItems().clear();
        if (!meetings.isEmpty()) {
            for (final Meeting m : meetings) {
                
                String patient = getModel().getPatient(m.getPatient()).getName(); 
                final Label content = new Label("With " + patient + ": " + m.getDate() 
                                                                    + " " + m.getHour()); 
                root.getItems().add(content);
            
            }
        } else {
            final Label content = new Label("Vous n'avez aucun rdv prévu."); 
            root.getItems().add(content);
        }
        
    }

    /**.
     * Remplit la CardPane contenant les messages avec une conversation spécifique
     */
    public void showChat() {
        List<String> listDest = new ArrayList<>();
        List<Message> conv = getModel().getMessagesByDest(profil.getLogin());
        CardPane root = controller.getChatPane();
        root.getItems().clear();
        if (!conv.isEmpty()) {
            for (final Message m : conv) {
                if (!listDest.contains(m.getExpediteur())) {
                    listDest.add(m.getExpediteur());                    
                    final Label content = new Label(" From : " + m.getExpediteur() + ", le : " 
                                                    + m.getDate());  
                    content.setOnMouseClicked((event) -> {
                        showConversation(profil.getLogin(), m.getExpediteur());
                    });              
                    root.getItems().add(content);
                }
            }
        } else {
            final Label content = new Label("Vous n'avez aucun message pour le moment");
            root.getItems().add(content);
        }

    }

    /**.
     * Remplit la CardPane contenant les messages avec une conversation spécifique
     * @param login1 profil 1 concerné par l'échange
     * @param login2 profil 2 concerné par l'échange
     */
    public void showConversation(final String login1, final String login2) {
        
        final List<Message> conv = getModel().getMessagesConv(login1, login2);
        CardPane root = controller.getChatPane();
        root.getItems().clear();
        for (final Message m : conv) {
                String exp = m.getExpediteur(); 
                final Label content = new Label(exp + " : " + m.getText()); 
                root.getItems().add(content);
            
        }
    }

    @Override
    public void update() { 
        Strategy strategy = controller.getStrategyList().getSelectionModel().getSelectedItem();   
        if (strategy != null) {
            showPrescription(strategy); 
        }          
        showMeeting();      
        showChat(); 
    }

}
