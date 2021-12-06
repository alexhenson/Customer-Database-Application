package tools;

import dbConnection.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class ButtonEvent {
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
        scene = FXMLLoader.load(ButtonEvent.class.getResource(fileName));
        stage.setTitle(formName);
        stage.setScene(new Scene(scene));
        stage.centerOnScreen();
        stage.show();
    }

    public static void cancelButtonAction(String message, String systemMsg, String fileName, String formName, ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println(systemMsg);
            buttonAction(fileName, formName, actionEvent);
        }
    }
    public static void exitButtonAction(String message, String systemMsg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println(systemMsg);
            JDBC.closeConnection();
            System.exit(0);
        }
    }


}
