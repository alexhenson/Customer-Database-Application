package tools;

import javafx.scene.control.Alert;

public class AlertEvent {
    /** Static method that will allow user bring up a dialog box for erroneous input or catch logical errors.
     *  @param title title of the alert
     *  @param text text of the alert
     */
    public static void alertBox(String title, String text) {
        Alert alertAssPart = new Alert(Alert.AlertType.ERROR);
        alertAssPart.setTitle(title);
        alertAssPart.setContentText(text);
        alertAssPart.showAndWait();
    }

    public static void infoBox(String title, String text) {
        Alert alertAssPart = new Alert(Alert.AlertType.INFORMATION);
        alertAssPart.setTitle(title);
        alertAssPart.setContentText(text);
        alertAssPart.showAndWait();
    }
}
