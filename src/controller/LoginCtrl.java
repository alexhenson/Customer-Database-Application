package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tools.GUIEvent;

import java.io.IOException;
import java.net.URL;
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
    }

    public void onActionSubmit(ActionEvent actionEvent) throws IOException {
        System.out.println("Submit button clicked!");
        GUIEvent.buttonAction("/view/CustAppt.fxml", "Customer Appointment Form", actionEvent);
    }
}
