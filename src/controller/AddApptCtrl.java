package controller;

import dbAccess.*;
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
import tools.AlertEvent;
import tools.ButtonEvent;
import tools.TextBoxEvent;

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
    void onActionSave(ActionEvent event) throws IOException {
        System.out.println("Save button clicked!");

        String title = TextBoxEvent.validateString(titleTxt, "Title");
        String description = TextBoxEvent.validateString(descTxt, "Description");
        String location = TextBoxEvent.validateString(locationTxt, "Location");

        // Checks return values for each field to ensure they are valid
        if (title == null || description == null || location == null) {
            return;
        }

        if (userIdCombo.getValue() == null) {
            AlertEvent.alertBox("Error Dialog", "Please select a value for the User ID combo box.");
            return;
        }
        int userId = userIdCombo.getSelectionModel().getSelectedItem().getUserId();

        if (custIdCombo.getValue() == null) {
            AlertEvent.alertBox("Error Dialog", "Please select a value for the Customer ID combo box.");
            return;
        }
        int custId = custIdCombo.getSelectionModel().getSelectedItem().getCustomerId();

        if (contactCombo.getValue() == null) {
            AlertEvent.alertBox("Error Dialog", "Please select a value for the Contact combo box.");
            return;
        }
        int contactId = contactCombo.getSelectionModel().getSelectedItem().getContactId();

        if (typeCombo.getValue() == null) {
            AlertEvent.alertBox("Error Dialog", "Please select a value for the Meeting Type combo box.");
            return;
        }
        String type = typeCombo.getSelectionModel().getSelectedItem();

        //need to combine date and time into datetime objects

        DBAppointments.addAppointment(title, description, location, type, start, end, custId, userId, contactId);
        ButtonEvent.buttonAction("/view/Appointments.fxml", "Appointment Table", event);
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        ButtonEvent.cancelButtonAction("This will clear all field values, do you want to continue?", "Cancel button clicked", "/view/Appointments.fxml", "Appointments Table",event);
    }
}

