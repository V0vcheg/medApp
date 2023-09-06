package fr.univ_lyon1.info.m1.mes.controller;




import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.gluonhq.charm.glisten.control.CardPane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;

import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.model.Profil;
import fr.univ_lyon1.info.m1.mes.utils.EasyClipboard;
import fr.univ_lyon1.info.m1.mes.utils.EasyDialog;
import fr.univ_lyon1.info.m1.mes.utils.EasyImage;
import javafx.scene.shape.Circle;


public class ProfilPatientController extends Controller {

    private Profil profil;
 
    @FXML
    private Circle profilPicture;

    @FXML
    private Label profilName;

    @FXML
    private AnchorPane prescriptionPane;

    @FXML
    private Label profilProperty;

    @FXML 
    private CardPane chatContent;

    @FXML 
    private CardPane meetContent;

    @FXML
    private ImageView chatButton;

    @FXML
    private ImageView rdvButton;

    @FXML
    private HBox profilSSID;

    @FXML
    private ImageView  copyButton;

    @FXML
    private ImageView  refreshButton;


    
    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
            profilPicture.setFill(new ImagePattern(EasyImage
                                    .findImage("src/main/img/download.jpeg")));
            chatButton.setImage(EasyImage.findImage("src/main/img/chat.png"));
            copyButton.setImage(EasyImage.findImage("src/main/img/copyPaste.png"));
            refreshButton.setImage(EasyImage.findImage("src/main/img/refresh.png"));
	
    }


    @Override
    public void setViewId(final String login) { 
        profil = getModel().getProfil(login);          
        String name = getModel().getPatient(profil.getIdProfil()).getName();
        profilName.setText("Hello " + name + "!"); 
        Label ssid = new Label();
        ssid.setText(profil.getIdProfil() + " "); 
        ssid.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
        profilSSID.getChildren().add(ssid);    
        getCurrentView().setSession(profil); //communique la session active à la vue
        
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
     * Fonction associée à l'ImageView copyButton du fxml
     * Copie le ssid associé au profil
     */
    
    public void copySSID() {
        EasyClipboard.copy(profil.getIdProfil());
    }

    /**.
     * Fonction associée à l'ImageView chatButton fxml
     * Récupère les informations du nouveau message et les transmets au modèle
     */
    public void createMessage() {
        List<Profil> allLogin = getModel().getAllHP();
        EasyDialog dialog = new EasyDialog(getStage(), allLogin);
        Button action = dialog.getButton();
        action.setOnAction((event) -> {
            String hp = dialog.getSelectedLogin(); 
            notifyMessageCreated(profil.getLogin(), hp, dialog.getTextArea());
            dialog.close();
        });
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
     * Pour les tests
     * @param login 
     */
    public void setProfil(final String login) { 
        profil = getModel().getProfil(login);
    }

    
     /**.
     * Accesseur 
     * @return AnchorPane contenant la "sous-vue" prescription
     */
    public AnchorPane getPrescriptionHPPane() {
        return prescriptionPane;
    }

    /**.
     * Accesseur 
     * @return CardPane contenant la "sous-vue" du chat
     */
    public CardPane getChatPane() {
        return chatContent;
    }

    /**.
     * Accesseur 
     * @return CardPane contenant la "sous-vue" des rendez-vous
     */
    public CardPane getMeetPane() {
        return meetContent;
    }

    /**.
     * Accesseur 
     * @return profil associé à la vue et au controller
     */
    public Profil getProfil() {
        return profil;
    }

   
}
