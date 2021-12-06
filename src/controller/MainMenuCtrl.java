package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tools.ButtonEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuCtrl implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @FXML
    void onActionAppt(ActionEvent event) throws IOException {
        System.out.println("Appointments button clicked!");
        ButtonEvent.buttonAction("/view/Appointments.fxml", "Appointments Table", event);
    }

    @FXML
    void onActionCust(ActionEvent event) throws IOException {
        System.out.println("Customers button clicked!");
        ButtonEvent.buttonAction("/view/Customers.fxml", "Customers Table", event);
    }

    @FXML
    void onActionReports(ActionEvent event) throws IOException {
        System.out.println("Reports button clicked!");
        ButtonEvent.buttonAction("/view/Reports.fxml", "Reports Form", event);
    }

    public void onActionExit() {
        ButtonEvent.exitButtonAction("This will exit the program, do you want to continue?", "Exit button clicked");
    }
}
