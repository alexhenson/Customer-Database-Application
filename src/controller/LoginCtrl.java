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
import java.time.ZonedDateTime;
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
