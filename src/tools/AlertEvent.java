package tools;

import javafx.scene.control.Alert;

/** This helper class is responsible for the functionality of all the Alert objects in the program. */
public class AlertEvent {
    /** Static method that will allow the program bring up a dialog box for erroneous input or catch logical errors.
     *  @param title title of the alert
     *  @param text text of the alert
     */
    public static void alertBox(String title, String text) {
        Alert alertAssPart = new Alert(Alert.AlertType.ERROR);
        alertAssPart.setTitle(title);
        alertAssPart.setContentText(text);
        alertAssPart.showAndWait();
    }

    /** Static method that will allow the program bring up a dialog box for information pertinent to the user.
     *  @param title title of the alert
     *  @param text text of the alert
     */
    public static void infoBox(String title, String text) {
        Alert alertAssPart = new Alert(Alert.AlertType.INFORMATION);
        alertAssPart.setTitle(title);
        alertAssPart.setContentText(text);
        alertAssPart.showAndWait();
    }
}
