package tools;

import javafx.scene.control.ComboBox;

public class ComboBoxEvent {
    public static String validateComboBoxString(ComboBox comboBox, String labelName, String methodName) {
        if (comboBox.getSelectionModel().getSelectedItem().methodName == null) {
            AlertEvent.alertBox("Error Dialog", "Please select a value for the " + labelName + " combo box.");
            return null;
        }
        return comboBox.getSelectionModel().getSelectedItem().toString();
    }

    public static String validateComboBoxInt(ComboBox comboBox, String labelName, ) {
        if (comboBox.getSelectionModel().getSelectedItem().toString() == null) {
            AlertEvent.alertBox("Error Dialog", "Please select a value for the " + labelName + " combo box.");
            return null;
        }
        return comboBox.getSelectionModel().getSelectedItem().toString();
    }
}
