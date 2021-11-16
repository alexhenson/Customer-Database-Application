package tools;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIEvent {
    public static Stage stage;
    public static Parent scene;

    /** Static method that will allow user to change scenes with the click of a button.
     *  @param fileName name of the file to change the scene
     *  @param formName name of the form to place as the title
     *  @param actionEvent object to trigger actions
     *  @throws IOException If an input or output exception occurred
     */
    public static void buttonAction(String fileName, String formName, ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(GUIEvent.class.getResource(fileName));
        stage.setTitle(formName);
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
