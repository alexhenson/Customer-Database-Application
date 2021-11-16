package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tools.GUIEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomersCtrl implements Initializable {

    @FXML
    private Button addBtn;
    @FXML
    private TableColumn<?, ?> addressCol;
    @FXML
    private Button apptBtn;
    @FXML
    private TableColumn<?, ?> countryCol11;
    @FXML
    private TableColumn<?, ?> custIdCol;
    @FXML
    private TableView<?> custTblView;
    @FXML
    private AnchorPane customers;
    @FXML
    private Button delBtn;
    @FXML
    private TableColumn<?, ?> divisionCol;
    @FXML
    private Button mainMenuBtn;
    @FXML
    private TableColumn<?, ?> nameCol;
    @FXML
    private TableColumn<?, ?> phoneCol;
    @FXML
    private TableColumn<?, ?> postalCol;
    @FXML
    private Button reportsBtn;
    @FXML
    private TextField searchTxt;
    @FXML
    private Label titleLbl;
    @FXML
    private Button updateBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onActionAdd(ActionEvent event) {

    }

    @FXML
    void onActionAppt(ActionEvent event) throws IOException {
        System.out.println("Appointments button clicked!");
        GUIEvent.buttonAction("/view/Appointments.fxml", "Appointments Table", event);
    }

    @FXML
    void onActionDelete(ActionEvent event) {

    }

    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        System.out.println("Main Menu button clicked!");
        GUIEvent.buttonAction("/view/MainMenu.fxml", "Main Menu", event);
    }

    @FXML
    void onActionReports(ActionEvent event) {

    }

    @FXML
    void onActionSearch(ActionEvent event) {

    }

    @FXML
    void onActionUpdate(ActionEvent event) {

    }
}