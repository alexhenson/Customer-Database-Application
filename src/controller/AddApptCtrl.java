package controller;

import dbAccess.DBContacts;
import dbAccess.DBCustomers;
import dbAccess.DBDivisions;
import dbAccess.DBUsers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.*;
import tools.ButtonEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddApptCtrl implements Initializable {

    @FXML
    private AnchorPane addAppt;
    @FXML
    private Label addApptLbl;
    @FXML
    private Button cancelBtn;
    @FXML
    private ComboBox<Contact> contactCombo;
    @FXML
    private Label contactLbl;
    @FXML
    private ComboBox<Customer> custIdCombo;
    @FXML
    private Label descLbl;
    @FXML
    private TextField descTxt;
    @FXML
    private DatePicker endDatePkr;
    @FXML
    private ComboBox<?> endTimeCombo;
    @FXML
    private Label idLbl;
    @FXML
    private TextField idTxt;
    @FXML
    private Label locationLbl;
    @FXML
    private TextField locationTxt;
    @FXML
    private Label minLbl;
    @FXML
    private Button saveBtn;
    @FXML
    private DatePicker startDatePkr;
    @FXML
    private ComboBox<?> startTimeCombo;
    @FXML
    private Label titleLbl;
    @FXML
    private TextField titleTxt;
    @FXML
    private ComboBox<String> typeCombo;
    @FXML
    private Label typeLbl;
    @FXML
    private ComboBox<User> userIdCombo;

    ObservableList<String> typeList = Appointment.getTypeList();
    ObservableList<Contact> contactList = DBContacts.getAllContacts();
    ObservableList<Customer> customerList = DBCustomers.getAllCustomers();
    ObservableList<User> userList = DBUsers.getAllUsers();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userIdCombo.setPromptText("User ID");
        contactCombo.setPromptText("Contact Name");
        typeCombo.setPromptText("Meeting Type");
        custIdCombo.setPromptText("Customer ID");
        userIdCombo.setItems(userList);
        contactCombo.setItems(contactList);
        typeCombo.setItems(typeList);
        custIdCombo.setItems(customerList);
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        ButtonEvent.cancelButtonAction("This will clear all field values, do you want to continue?", "Cancel button clicked", "/view/Appointments.fxml", "Appointments Table",event);
    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        System.out.println("Save button clicked!");
        ButtonEvent.buttonAction("/view/Appointments.fxml", "Appointment Table", event);
    }
}

