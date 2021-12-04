package controller;

import dbAccess.DBUsers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Appointment;
import model.User;
import tools.*;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.IllformedLocaleException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCtrl implements Initializable {
    @FXML
    private Label loginLbl;
    @FXML
    private TextField passwordTxt;
    @FXML
    private Button submitBtn;
    @FXML
    private TextField usernameTxt;
    @FXML
    private Label zoneIdLbl;

    Locale currentLocale = Locale.getDefault();
    ResourceBundle rb = ResourceBundle.getBundle("Nat", currentLocale);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("LoginForm is initialized!");

        String zoneId = String.valueOf(ZonedDateTime.now().getZone());
        zoneIdLbl.setText("[" + zoneId + "]");
    }

    public void onActionUsername(ActionEvent actionEvent) throws IOException {
        passwordTxt.requestFocus();
    }

    public void onActionPassword(ActionEvent actionEvent) throws IOException {
        submitBtn.requestFocus();
    }

    public void onActionSubmit(ActionEvent actionEvent) throws IOException {
        validateLogin(actionEvent, "Submit button clicked!");
    }

    public void validateLogin(ActionEvent actionEvent, String systemDialog) throws IOException {
        System.out.println(systemDialog);
        ObservableList<User> userList = DBUsers.getAllUsers();

        String userName = usernameTxt.getText();
        String password = passwordTxt.getText();

        for (User u : userList) {
            if (u.getUserName().equals(userName) && u.getPassword().equals(password)) {
                boolean foundAppt = false;
                for (Appointment a : StaticObservableLists.appointmentList) {
                    if (u.getUserId() == a.getUserId() && a.getStart().toLocalTime().isAfter(TimeHelper.currentTime.minusSeconds(1))) {
                        long timeDifference = ChronoUnit.MINUTES.between(TimeHelper.currentTime, a.getStart().toLocalTime());
                        if (timeDifference >= 0 && timeDifference <= 15) {
                            AlertEvent.infoBox("Appointment Soon!", "User #" + u.getUserId() + " has an appointment starting in about " + timeDifference + " minutes!");
                            foundAppt = true;
                        }
                    }
                }
                if (!foundAppt) {
                    AlertEvent.infoBox("No Pertinent Appointments", "User #" + u.getUserId() + " does not have any appointments within 15 minutes.");
                }
                ButtonEvent.buttonAction("/view/MainMenu.fxml", "Main Menu", actionEvent);
                return;
            }
        }
        AlertEvent.alertBox(rb.getString("alertBox.title"), rb.getString("alertBox.text"));
    }
}
