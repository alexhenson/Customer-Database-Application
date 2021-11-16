package controller;

import dbAccess.DBCountries;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.Country;
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
    @FXML
    private Button exitBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onActionAppt(ActionEvent event) throws IOException {
        System.out.println("Appointments button clicked!");
        GUIEvent.buttonAction("/view/Appointments.fxml", "Appointments Table", event);
    }

    @FXML
    void onActionCust(ActionEvent event) throws IOException {
        System.out.println("Customers button clicked!");
        GUIEvent.buttonAction("/view/Customers.fxml", "Customers Table", event);
    }

    @FXML
    void onActionLogin(ActionEvent event) throws IOException {
        GUIEvent.cancelButtonAction("You will be taken to the login screen again, do you want to continue?", "Login button clicked", "/view/Login.fxml", "Login Screen",event);

    }

    @FXML
    void onActionReports(ActionEvent event) {

    }


    public void onActionExit(ActionEvent actionEvent) {
        GUIEvent.exitButtonAction("This will exit the program, do you want to continue?", "Exit button clicked");
    }
}
