package controller;

import dbAccess.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import tools.AlertEvent;
import tools.ButtonEvent;
import tools.TextBoxEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class UpdateApptCtrl implements Initializable {

    @FXML
    private AnchorPane addAppt;
    @FXML
    private Label updateApptLbl;
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
    private ComboBox<LocalTime> endTimeCombo;
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
    private DatePicker datePkr;
    @FXML
    private ComboBox<LocalTime> startTimeCombo;
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

    ObservableList<Customer> customerList = DBCustomers.getAllCustomers();
    ObservableList<User> userList = DBUsers.getAllUsers();
    ObservableList<Contact> contactList = DBContacts.getAllContacts();
    ObservableList<String> typeList = Appointment.getTypeList();
    ObservableList<LocalTime> startTimeList = FXCollections.observableArrayList();
    ObservableList<LocalTime> endTimeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userIdCombo.setItems(userList);
        contactCombo.setItems(contactList);
        typeCombo.setItems(typeList);
        custIdCombo.setItems(customerList);

        LocalTime start = LocalTime.of(9,0);
        LocalTime end = LocalTime.of(17,0);

        while (start.isBefore(end.plusSeconds(1))) {
            startTimeList.add(start);
            start = start.plusMinutes(30);
        }
        startTimeCombo.setItems(startTimeList);
    }

    public void sendAppointment(Appointment appt) {
        idTxt.setText(String.valueOf(appt.getAppointmentId()));
        titleTxt.setText(appt.getTitle());
        descTxt.setText(appt.getDescription());
        locationTxt.setText(appt.getLocation());

        String type = appt.getType();

        if (type == null) {
            System.out.println("Type object is null for combo box!");
            return;
        } else {
            typeCombo.setValue(type);
        }

        String division = cust.getDivision();
        FirstLevelDivision selectedDivision = null;

        for (FirstLevelDivision fld : divisionList) {
            if (division.equals(fld.getDivision())) {
                selectedDivision = fld;
            }
        }

        if (selectedDivision == null) {
            System.out.println("Division object is null for combo box!");
            return;
        } else {
            divisionCombo.setValue(selectedDivision);
        }
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        ButtonEvent.cancelButtonAction("None of your changes will be saved, do you want to continue?", "Cancel button clicked", "/view/Appointments.fxml", "Appointments Table",event);
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

        if (datePkr.getValue() == null) {
            AlertEvent.alertBox("Error Dialog", "Please select a value for the Date picker box.");
            return;
        }
        LocalDate date = datePkr.getValue();

        if (startTimeCombo.getValue() == null) {
            AlertEvent.alertBox("Error Dialog", "Please select a value for the Start Time combo box.");
            return;
        }
        LocalTime startTime = startTimeCombo.getSelectionModel().getSelectedItem();

        if (endTimeCombo.getValue() == null) {
            AlertEvent.alertBox("Error Dialog", "Please select a value for the End Time combo box.");
            return;
        }
        LocalTime endTime = endTimeCombo.getSelectionModel().getSelectedItem();

        LocalDateTime localStart = LocalDateTime.of(date, startTime);
        LocalDateTime localEnd = LocalDateTime.of(date, endTime);

        Timestamp start = Timestamp.valueOf(localStart);
        Timestamp end = Timestamp.valueOf(localEnd);

        DBAppointments.addAppointment(title, description, location, type, start, end, custId, userId, contactId);
        ButtonEvent.buttonAction("/view/Appointments.fxml", "Appointment Table", event);
    }

    public void onActionStartTime(ActionEvent actionEvent) {
    }
}

