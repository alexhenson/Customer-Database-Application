package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import tools.GUIEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuCtrl implements Initializable {
    @FXML
    private Button apptBtn;
    @FXML
    private Button custBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private Button reportsBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onActionAppt(ActionEvent event) {

    }

    @FXML
    void onActionCust(ActionEvent event) {

    }

    @FXML
    void onActionLogin(ActionEvent event) throws IOException {
        GUIEvent.cancelButtonAction("You will be taken to the login screen again, do you want to continue?", "Login button clicked", "/view/CustAppt.fxml", "Customer Appointment Form",event);

    }

    @FXML
    void onActionReports(ActionEvent event) {

    }


}
