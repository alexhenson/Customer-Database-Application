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

import static tools.StaticObservableLists.getAppointmentList;
import static tools.StaticObservableLists.getUserList;
import static tools.TimeHelper.getCurrentTime;

/** This class is responsible for the functionality of the "Login" controller. */
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

    /** This method activates when the scene starts.
     *  @param url for initialization
     *  @param resourceBundle for initialization
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("LoginForm is initialized!");

        String zoneId = String.valueOf(ZonedDateTime.now().getZone());
        zoneIdLbl.setText("[" + zoneId + "]");
    }

    /** This method will shift the cursor to the passwordTxt text box. */
    public void onActionUsername() {
        passwordTxt.requestFocus();
    }

    /** This method will shift the focus to the Submit button. */
    public void onActionPassword() {
        submitBtn.requestFocus();
    }

    /** This method will call the validateLogin method.
     *  @param actionEvent object to trigger actions
     *  @throws IOException If an input or output exception occurred
     */
    public void onActionSubmit(ActionEvent actionEvent) throws IOException {
        validateLogin(actionEvent, "Submit button clicked!");
    }

    /** This method will validate the login information for the username and password.
     *  Additionally this method also contains code to set up an alert for any appointment within 15 minutes of login.
     *  Code also calls loginWriter method.
     *  @param actionEvent object to trigger actions
     *  @param systemDialog The string passed to the System.out.println() function.
     *  @throws IOException If an input or output exception occurred
     */
    public void validateLogin(ActionEvent actionEvent, String systemDialog) throws IOException {
        System.out.println(systemDialog);

        String userName = usernameTxt.getText();
        String password = passwordTxt.getText();

        for (User u : getUserList()) {
            if (u.getUserName().equals(userName) && u.getPassword().equals(password)) {
                boolean foundAppt = false;

                for (Appointment a : getAppointmentList()) {
                    if (u.getUserId() == a.getUserId() && a.getStart().toLocalTime().isAfter(getCurrentTime().minusSeconds(1))) {
                        long timeDifference = ChronoUnit.MINUTES.between(getCurrentTime(), a.getStart().toLocalTime());
                        if (timeDifference >= 0 && timeDifference <= 15) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
                            ZonedDateTime startZDT = a.getStart().atZone(ZoneId.systemDefault());
                            AlertEvent.infoBox("Appointment Soon!", "User #" + u.getUserId() + " has an appointment #" + a.getAppointmentId() + " starting in about " + timeDifference + " minutes!  The appointment is on " + startZDT.format(formatter) + ".");
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

    /** This method writes the login activity of every login attempt to a text file.
     *  @param username object to trigger actions
     *  @param password The string passed to the System.out.println() function.
     *  @param isSuccessful The string passed to the System.out.println() function.
     *  @throws IOException If an input or output exception occurred
     */
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
