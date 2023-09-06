package fr.univ_lyon1.info.m1.mes.utils;



import java.time.LocalDate;
import java.util.List;

import fr.univ_lyon1.info.m1.mes.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**.
 * Affichage de la fenêtre modale pour la prise de RDV
*/
public class EasyPlanner {

    private Button action = new Button("Submit");  
    private Stage newWindow = new Stage();
    private ComboBox listPatient = new ComboBox<>();
    private DatePicker date = new DatePicker();

    public EasyPlanner(final Stage primaryStage, final List<Patient> allPatient) {
            
            VBox newMeeting = new VBox();

			StackPane secondaryLayout = new StackPane();		
			
			newWindow.setTitle("Programmer un RDV ");

            HBox box = new HBox();
            ObservableList<Patient> list
                    = FXCollections.observableArrayList();         
            for (Patient p : allPatient) {
                list.add(p);
            }
            Label label = new Label("Pour : ");
            
            listPatient.setItems(list);   
            listPatient.getSelectionModel().select(0);
            box.getChildren().addAll(label, listPatient);

            HBox box2 = new HBox(date);
            HBox box3 = new HBox(action);
            newMeeting.getChildren().addAll(box, box2, box3);
            secondaryLayout.getChildren().add(newMeeting);
            Scene secondScene = new Scene(secondaryLayout, 300, 80);
            newWindow.setScene(secondScene);

			newWindow.setX(primaryStage.getX() + 200);
			newWindow.setY(primaryStage.getY() + 100);

			newWindow.show();
    }

    public Button getButton() {
        return action;
    }

    public ComboBox getPatientList() {
        return listPatient;
    }

    public String getSelectedDate() {
        LocalDate ldate = date.getValue();
        return EasyDateFormatter.ldateToString(ldate);
    }

    public String getSelectedPatient() {
        return listPatient.getSelectionModel().getSelectedItem().toString();
    }

    public void close() {
        newWindow.close();
    }
}
