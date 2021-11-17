package tools;

import javafx.scene.control.TextField;

public class TextBoxEvent {
    /** This method validates a String from the text box.
     *  @param textField object used with getText() method
     *  @param labelName name of the label for alert
     *  @return String or null
     */
    public static String validateString(TextField textField, String labelName) {
        if (textField.getText().isBlank()) {
            AlertEvent.alertBox("Error Dialog", "Please enter a valid string value for the " + labelName + " field.");
            return null;
        }
        return textField.getText();
    }

    /** This method validates an int from the text box.
     *  @param textField object used with getText() method
     *  @param labelName name of the label for alert
     *  @return int or -1
     */
    public static int validateInteger(TextField textField, String labelName) {
        try {
            return Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            AlertEvent.alertBox("Error Dialog", "Please enter a valid integer value for the " + labelName + " field.");
            return -1;
        }
    }

    /** This method validates a double from the text box.
     *  @param textField object used with getText() method
     *  @param labelName name of the label for alert
     *  @return double or -1
     */
    public static double validateDouble(TextField textField, String labelName) {
        try {
            return Double.parseDouble(textField.getText());
        } catch (NumberFormatException e) {
            AlertEvent.alertBox("Error Dialog", "Please enter a valid decimal value for the " + labelName + " field.");
            return -1;
        }
    }
}
