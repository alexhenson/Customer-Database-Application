package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tools.GUIEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsCtrl implements Initializable {

    @FXML
    private Button custBtn;
    @FXML
    private RadioButton allRBtn;
    @FXML
    private AnchorPane appointments;
    @FXML
    private Button addBtn;
    @FXML
    private TableColumn<?, ?> apptCustIdCol;
    @FXML
    private Button delBtn;
    @FXML
    private TableColumn<?, ?> apptIdCol;
    @FXML
    private TextField apptSearchTxt;
    @FXML
    private TableView<?> apptTblView;
    @FXML
    private Button updateBtn;
    @FXML
    private TableColumn<?, ?> contactCol;
    @FXML
    private TableColumn<?, ?> descCol;
    @FXML
    private TableColumn<?, ?> endDateCol;
    @FXML
    private TableColumn<?, ?> endTimeCol;
    @FXML
    private TableColumn<?, ?> locationCol;
    @FXML
    private Button mainMenuBtn;
    @FXML
    private RadioButton monthRBtn;
    @FXML
    private Button reportsBtn;
    @FXML
    private TableColumn<?, ?> startDateCol;
    @FXML
    private TableColumn<?, ?> startTimeCol;
    @FXML
    private TableColumn<?, ?> titleCol;
    @FXML
    private Label titleLbl;
    @FXML
    private TableColumn<?, ?> typeCol;
    @FXML
    private TableColumn<?, ?> userIdCol;
    @FXML
    private RadioButton weekRBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onActionAll(ActionEvent event) {

    }

    @FXML
    void onActionAdd(ActionEvent event) {

    }

    @FXML
    void onActionDelete(ActionEvent event) {

    }

    @FXML
    void onActionApptSearch(ActionEvent event) {

    }

    @FXML
    void onActionUpdate(ActionEvent event) {

    }

    @FXML
    void onActionCust(ActionEvent event) throws IOException {
        System.out.println("Customers button clicked!");
        GUIEvent.buttonAction("/view/Customers.fxml", "Customers Table", event);
    }

    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        System.out.println("Main Menu button clicked!");
        GUIEvent.buttonAction("/view/MainMenu.fxml", "Main Menu", event);
    }

    @FXML
    void onActionMonth(ActionEvent event) {

    }

    @FXML
    void onActionReports(ActionEvent event) {

    }

    @FXML
    void onActionWeek(ActionEvent event) {

    }
}