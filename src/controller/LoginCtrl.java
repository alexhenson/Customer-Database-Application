package controller;

import dbAccess.DBUsers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.User;
import tools.AlertEvent;
import tools.ButtonEvent;
import tools.TextBoxEvent;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("LoginForm is initialized!");
        /*
        ZoneId z = ZoneId.of(); //need to figure this out
        ZonedDateTime zdt = ZonedDateTime.now( z ) ; // Capture the current moment as seen through the wall-clock time used by the people of a particular region (a time zone).
        Instant instant = zdt.toInstant() ;  // Adjust from a zone to UTC, if needed. An `Instant` is always in UTC by definition.         Locale loc = Locale.getDefault();
        DateTimeFormatter format = DateTimeFormatter.ofLocalizedDateTime( FormatStyle.FULL ).withLocale(loc);

        String zoneId = String.valueOf(ZonedDateTime.now().getZone());
        String output = zoneId.format(String.valueOf(format));
         */
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
                ButtonEvent.buttonAction("/view/MainMenu.fxml", "Main Menu", actionEvent);
                return;
            }
        }
        AlertEvent.alertBox("Error Dialog", "Please enter a valid username and password.");
    }
}
