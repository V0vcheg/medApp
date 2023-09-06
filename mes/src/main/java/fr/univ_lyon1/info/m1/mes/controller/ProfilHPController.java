package fr.univ_lyon1.info.m1.mes.controller;




import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.gluonhq.charm.glisten.control.CardPane;
import com.gluonhq.charm.glisten.control.TextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.model.Profil;
import fr.univ_lyon1.info.m1.mes.strategy.Strategy;
import fr.univ_lyon1.info.m1.mes.strategy.StrategyByName;
import fr.univ_lyon1.info.m1.mes.strategy.StrategyByPrefixe;
import fr.univ_lyon1.info.m1.mes.strategy.StrategyBySSID;
import fr.univ_lyon1.info.m1.mes.utils.EasyAlert;
import fr.univ_lyon1.info.m1.mes.utils.EasyDialog;
import fr.univ_lyon1.info.m1.mes.utils.EasyImage;
import fr.univ_lyon1.info.m1.mes.utils.EasyPlanner;
import javafx.scene.shape.Circle;


public class ProfilHPController extends Controller {

    private Profil profil;
 
    @FXML
    private Circle profilPicture;

    @FXML
    private Label profilName;


    @FXML
    private AnchorPane prescriptionPatientPane;


    @FXML
    private ComboBox<Strategy> strategyList; 

    @FXML
    private TextField searchInput;

    @FXML
    private TextField prescriptionInput;
    
    @FXML
    private CardPane defaultPrescriptionPane;

    @FXML
    private CardPane meetPane;

    @FXML
    private CardPane chatPane;
    
    @FXML
    private ImageView chatButton;

    @FXML
    private ImageView refreshButton;

    @FXML
    private ImageView meetingButton;
    


    @Override
    public void initialize(final URL url, final ResourceBundle rb) {

            profilPicture.setFill(new ImagePattern(EasyImage
                                                    .findImage("src/main/img/profilHP.png")));
            ObservableList<Strategy> list
                = FXCollections.observableArrayList(new StrategyBySSID(), 
                         new StrategyByName(), new StrategyByPrefixe());           

            strategyList.setItems(list);
            chatButton.setImage(EasyImage.findImage("src/main/img/chat.png"));
            refreshButton.setImage(EasyImage.findImage("src/main/img/refresh.png"));
            meetingButton.setImage(EasyImage.findImage("src/main/img/calendar.png"));
  
    }


    @Override
    public void setViewId(final String login) {
        profil = getModel().getProfil(login);        
        profilName.setText("Hello " + profil.getIdProfil() + "!");
        getCurrentView().setSession(profil);
        
    }


    /**.
     * Fonction associée à l'ImageView refreshButton du fxml
     * Remet la page principal du chat avec toutes les conversations
     */
    public void refresh() {
        getCurrentView().update();
    }


    /**.
     * Fonction associée au bouton logout du fxml
     * Déconnecte l'user et affiche la scène de la page d'accueil
     */
    public void logout() {
        removeObserverToModel(getCurrentView());
        display(getBackView().getScene());
    }



    /**.
     * Fonction associée à l'ImageView meetingButton fxml
     * Récupère les informations de rdv et les soumets au modèle
     */
    public void scheduleMeeting() {
        List<Patient> allPatient = getModel().getPatients();

        EasyPlanner planner = new EasyPlanner(getStage(), allPatient);
        Button action = (Button) planner.getButton();
        action.setOnAction((event) -> {
            String date = planner.getSelectedDate(); 
            String ssid = getModel()
                    .getPatientByName(planner.getSelectedPatient())
                    .getSSID();
            notifyMeetingCreated(date, ssid, profil.getIdProfil());
            planner.close();
        });
    }

    /**.
     * Fonction associée à l'ImageView chatButton fxml
     * Récupère les informations du nouveau message et les transmets au modèle
     */
    public void createMessage() {
        List<Profil> allLogin = getModel().getAllPatientProfil();
        
        EasyDialog dialog = new EasyDialog(getStage(), allLogin);
        Button action = (Button) dialog.getButton();
        action.setOnAction((event) -> {
            String patient = dialog.getSelectedLogin(); 
            notifyMessageCreated(profil.getLogin(), patient, dialog.getTextArea());
            dialog.close();
        });
    }

    /**.
     * Fonction associée au bouton "Prescrire" du fxml
     * Récupère les informations de la prescription et les transmets au modèle
     */
    public void prescribe() {
        String selectedPatient = searchInput.getText();
        if (selectedPatient == "") {
            EasyAlert.alert("Please select a patient first");
            return;
        }
        Strategy strategy = (Strategy) strategyList.getSelectionModel().getSelectedItem();
        String ssid = getModel().search(strategy, selectedPatient).getSSID();
        String text = prescriptionInput.getText();
        notifyPrescriptionAdd(ssid, text);

    }



    /**.
     * Notifie le modèle de la supression d'une nouvelle prescription
     * @param prescId id de la prescription
     * @param ssid du patient concerné par la prescription
     */
    public void notifyPrescriptionRemoved(final String prescId, final String ssid) {
        getModel().removePrescription(prescId);
    }

    /**.
     * Notifie le modèle de la création d'une nouvelle prescription
     * @param content contenu de la prescription
     * @param ssid patient concerné par la prescription
     */
    public void notifyPrescriptionAdd(final String ssid, final String content) {
        getModel().createPrescription(new Prescription(ssid, profil.getIdProfil(), content));
    }

    /**.
     * Demande à la vue de rafraîchir la pae
     */
    public void notifySearch() {
        getCurrentView().update();
       
    }

    /**.
     * Notifie le modèle de la création d'un nouveau message
     * @param exp expediteur du message
     * @param dst destinataire du message
     * @param text contenu du message
     */
    public void notifyMessageCreated(final String exp, final String dst, final String text) {
        getModel().createMessage(exp, dst, text);
    }

    /**.
     * Notifie le modèle de la création d'un nouveau rendez-vous
     * @param date date du rdv
     * @param ssid du patient concerné par le rdv
     * @param hp professionnel ayant planifié le rdv
     */
    public void notifyMeetingCreated(final String date, final String ssid, final String hp) {
        getModel().createMeeting(date, ssid, hp);
    }


    /**.
    * Pour les tests
    */
    public void setProfil(final String login) { 
        profil = getModel().getProfil(login);
    }
    
    public AnchorPane getPrescriptionPatientPane() {
        return prescriptionPatientPane;
    }

    public CardPane getChatPane() {
        return chatPane;
    }

    /**.
     * Accesseur 
     * @return CardPane contenant la "sous-vue" des rendez-vous
     */
    public CardPane getMeetPane() {
        return meetPane;
    }
    
    public ComboBox<Strategy> getStrategyList() {
        return strategyList;
    }

    /**.
     * Accesseur 
     * @return TextField recherche d'un patient
     */
    public TextField getInputSearch() {
        return searchInput;
    }

    /**.
     * Accesseur 
     * @return CardPane contenant la "sous-vue" des prescriptions par défaut
     */
    public CardPane getDefaultPrescPane() {
        return defaultPrescriptionPane;
    }


}
