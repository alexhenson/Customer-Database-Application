package controller;

import dbAccess.DBCustomers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import tools.ButtonEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomersCtrl implements Initializable {

    @FXML
    private Button addBtn;
    @FXML
    private TableColumn<Customer, String> addressCol;
    @FXML
    private Button apptBtn;
    @FXML
    private TableColumn<Customer, String> countryCol;
    @FXML
    private TableColumn<Customer, Integer> custIdCol;
    @FXML
    private TableView<Customer> custTblView;
    @FXML
    private AnchorPane customers;
    @FXML
    private Button delBtn;
    @FXML
    private TableColumn<Customer, String> divisionCol;
    @FXML
    private Button mainMenuBtn;
    @FXML
    private TableColumn<Customer, String> nameCol;
    @FXML
    private TableColumn<Customer, String> phoneCol;
    @FXML
    private TableColumn<Customer, String> postalCol;
    @FXML
    private Button reportsBtn;
    @FXML
    private Label titleLbl;
    @FXML
    private Button updateBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Customer> customerList = DBCustomers.getAllCustomers();
        custTblView.setItems(customerList);
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        postalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    @FXML
    void onActionAdd(ActionEvent event) throws IOException {
        System.out.println("Add button clicked!");
        ButtonEvent.buttonAction("/view/AddCust.fxml", "Add Customers Table", event);
    }

    @FXML
    void onActionAppt(ActionEvent event) throws IOException {
        System.out.println("Appointments button clicked!");
        ButtonEvent.buttonAction("/view/Appointments.fxml", "Appointments Table", event);
    }

    @FXML
    void onActionDelete(ActionEvent event) {

    }

    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        System.out.println("Main Menu button clicked!");
        ButtonEvent.buttonAction("/view/MainMenu.fxml", "Main Menu", event);
    }

    @FXML
    void onActionReports(ActionEvent event) {

    }


    @FXML
    void onActionUpdate(ActionEvent event) throws IOException {
        System.out.println("Update button clicked!");
        ButtonEvent.buttonAction("/view/UpdateCust.fxml", "Update Customers Table", event);
    }
}