package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Appointment;
import model.User;
import tools.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCtrl implements Initializable {
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

    public void onActionUsername() {
        passwordTxt.requestFocus();
    }

    public void onActionPassword() {
        submitBtn.requestFocus();
    }

    public void onActionSubmit(ActionEvent actionEvent) throws IOException {
        validateLogin(actionEvent, "Submit button clicked!");
    }

    public void validateLogin(ActionEvent actionEvent, String systemDialog) throws IOException {
        System.out.println(systemDialog);

        String userName = usernameTxt.getText();
        String password = passwordTxt.getText();

        for (User u : StaticObservableLists.userList) {
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
                loginWriter(userName, password, true);

                if (!foundAppt) {
                    AlertEvent.infoBox("No Pertinent Appointments", "User #" + u.getUserId() + " does not have any appointments within 15 minutes.");
                }

                ButtonEvent.buttonAction("/view/MainMenu.fxml", "Main Menu", actionEvent);
                return;
            }
        }
        loginWriter(userName, password, false);
        AlertEvent.alertBox(rb.getString("alertBox.title"), rb.getString("alertBox.text"));
    }

    public void loginWriter(String username, String password, boolean isSuccessful) throws IOException {
        String filename = "login_activity.txt";
        FileWriter loginFW = new FileWriter(filename, true);
        PrintWriter outputFilePW = new PrintWriter(loginFW);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
        ZonedDateTime zdt = LocalDateTime.now().atZone(ZoneId.systemDefault());

        outputFilePW.println("Username Entered: " + username + ", Password Entered: " + password + ", Date and Time: " + zdt.format(formatter) + ", Login Successful: " + isSuccessful);

        loginFW.close();
        outputFilePW.close();
    }
}
