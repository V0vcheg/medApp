package fr.univ_lyon1.info.m1.mes.view;






import java.util.ArrayList;
import java.util.List;

import com.gluonhq.charm.glisten.control.CardPane;

import fr.univ_lyon1.info.m1.mes.controller.ProfilPatientController;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Meeting;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.model.Message;
import fr.univ_lyon1.info.m1.mes.model.Profil;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProfilPatientView extends View {
    
    //private Pane presc = new VBox();

    private Profil profil;

    private ProfilPatientController controller;

    /**.
     * Initialisation des données dont la vue a besoin
     * @param stage fenêtre principale de l'application
     * @param model modèle partagé par les vues et les controller
     * @param home vue de la page d'acceuil
     * @param id id du profil connecté
     */
    public ProfilPatientView(final Stage stage, final MES model, 
                            final View home, final String id) {
        super(stage, model);  
        setId(id); // = login
        initView("fxml/ProfilPatient.fxml", true);  
        controller.setBackView(home);
    }

    @Override
    public void setSession(final Profil profil) {
        this.profil = profil;
        controller = (ProfilPatientController) getController();           
        update();
 
    }

    /**.
     * Remplit la CardPane contenant les prescriptions du patient
     */
    public void showPrescription() {
        AnchorPane root = controller.getPrescriptionHPPane();        
        List<Prescription> listPresc = getModel().getPrescPatient(profil.getIdProfil());
        Pane presc = new VBox();
        
        if (!listPresc.isEmpty()) {
            for (final Prescription pr : listPresc) {
                
                final HBox pView = new HBox();
                final Label content = new Label("- From "
                + pr.getHealthProfessional()
                + ": " + pr.getContent() + " \n ");
                final Button removeBtn = new Button("x");
                removeBtn.setOnAction((event) -> { 
                    controller.notifyPrescriptionRemoved(pr.getId(), profil.getIdProfil());
                });
                pView.getChildren().addAll(content, removeBtn);
                presc.getChildren().add(pView);
            }
        } else {
            final HBox pView = new HBox();
            final Label content = new Label("Vous n'avez aucune prescription pour le moment");
            pView.getChildren().addAll(content);
            presc.getChildren().add(pView);
        }
        root.getChildren().add(presc);
    }

    /**.
     * Remplit la CardPane contenant les messages avec une conversation spécifique
     */
    public void showChat() {
        List<String> listDest = new ArrayList<>();
        List<Message> conv = getModel().getMessagesByDest(profil.getLogin());
        CardPane root = controller.getChatPane();
  
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

    /**.
     * Remplit la CardPane contenant les rdv
     */
    public void showMeeting() {
        final List<Meeting> meetings = getModel().getPatientMeetings(profil.getIdProfil());
        CardPane root = controller.getMeetPane();
        root.getItems().clear();
        for (final Meeting m : meetings) {
                String hp = m.getHP(); 
                final Label content = new Label("With " + hp + ": " + m.getDate() 
                                                                + " " + m.getHour()); 
                root.getItems().add(content);
            
        }
    }

    

    @Override
    public void update() {                
        controller.getPrescriptionHPPane().getChildren().clear();
        controller.getChatPane().getItems().clear();
        showPrescription();
        showChat();
        showMeeting();
    }

   
}
