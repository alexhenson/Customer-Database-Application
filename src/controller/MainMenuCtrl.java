package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tools.ButtonEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class is responsible for the functionality of the "Main Menu" controller. */
public class MainMenuCtrl implements Initializable {

    /** This method activates when the scene starts.
     *  @param url for initialization
     *  @param resourceBundle for initialization
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    /** This method will take you to the Appointments Form.
     *  @param event object to trigger actions
     *  @throws IOException If an input or output exception occurred
     */
    @FXML
    void onActionAppt(ActionEvent event) throws IOException {
        System.out.println("Appointments button clicked!");
        ButtonEvent.buttonAction("/view/Appointments.fxml", "Appointments Table", event);
    }

    /** This method will take you to the Customers Form.
     *  @param event object to trigger actions
     *  @throws IOException If an input or output exception occurred
     */
    @FXML
    void onActionCust(ActionEvent event) throws IOException {
        System.out.println("Customers button clicked!");
        ButtonEvent.buttonAction("/view/Customers.fxml", "Customers Table", event);
    }

    /** This method will take you to the Reports Form.
     *  @param event object to trigger actions
     *  @throws IOException If an input or output exception occurred
     */
    @FXML
    void onActionReports(ActionEvent event) throws IOException {
        System.out.println("Reports button clicked!");
        ButtonEvent.buttonAction("/view/Reports.fxml", "Reports Form", event);
    }

    /** This method will exit the program. */
    public void onActionExit() {
        ButtonEvent.exitButtonAction("This will exit the program, do you want to continue?", "Exit button clicked");
    }
}
