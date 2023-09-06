package fr.univ_lyon1.info.m1.mes.utils;



import java.util.List;

import fr.univ_lyon1.info.m1.mes.model.Profil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**.
 * Affichage de la fenêtre modale pour l'envoit des messages 
*/
public class EasyDialog {

    private Button action = new Button("Envoyer");  
    private Stage newWindow = new Stage();
    private ComboBox<Profil> listLogin = new ComboBox<Profil>();
    private TextArea text = new TextArea();

    public EasyDialog(final Stage primaryStage, final List<Profil> allLogin) {
     
            VBox newMessage = new VBox();
            Label secondLabel = new Label("Message \n");

			StackPane secondaryLayout = new StackPane();		
			
			newWindow.setTitle("Nouveau Message");

            HBox box = new HBox();
            ObservableList<Profil> list
                    = FXCollections.observableArrayList();         
            for (Profil p : allLogin) {
                list.add(p);
            }
            Label label = new Label("À : ");
            
            listLogin.setItems(list);   
            listLogin.getSelectionModel().select(0);
            box.getChildren().addAll(label, listLogin);
            

           
            text.setPrefWidth(300);
            text.setPrefHeight(100);
            HBox box2 = new HBox(secondLabel);
            HBox box3 = new HBox(text);         
            
            HBox box4 = new HBox(action);
            newMessage.getChildren().addAll(box, box2, box3, box4);
            secondaryLayout.getChildren().add(newMessage);
            Scene secondScene = new Scene(secondaryLayout, 300, 200);
            newWindow.setScene(secondScene);

			newWindow.setX(primaryStage.getX() + 200);
			newWindow.setY(primaryStage.getY() + 100);

			newWindow.show();
    }

    public Button getButton() {
        return action;
    }


    public String getSelectedLogin() {
       return listLogin.getSelectionModel().getSelectedItem().toString();
    }

    public String getTextArea() {
        return text.getText();
    }

    public void close() {
        newWindow.close();
    }
}
