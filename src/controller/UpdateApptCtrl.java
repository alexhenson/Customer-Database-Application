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
import tools.*;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userIdCombo.setItems(StaticObservableLists.userList);
        contactCombo.setItems(StaticObservableLists.contactList);
        typeCombo.setItems(StaticObservableLists.typeList);
        custIdCombo.setItems(StaticObservableLists.customerList);

        LocalTime start = TimeHelper.etLocalOpen.toLocalTime();
        LocalTime end = TimeHelper.etLocalClose.toLocalTime();

        while (start.isBefore(end.plusSeconds(1))) {
            StaticObservableLists.startTimeList.add(start);
            start = start.plusMinutes(15);
        }
        startTimeCombo.setItems(StaticObservableLists.startTimeList);
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

        String contact = appt.getContact();
        Contact selectedContact = null;

        for (Contact c : StaticObservableLists.contactList) {
            if (contact.equals(c.getContactName())) {
                selectedContact = c;
            }
        }

        if (selectedContact == null) {
            System.out.println("Contact object is null for combo box!");
            return;
        } else {
            contactCombo.setValue(selectedContact);
        }

        int customerId = appt.getCustomerId();
        Customer selectedCustomer = null;

        for (Customer c : StaticObservableLists.customerList) {
            if (customerId == c.getCustomerId()) {
                selectedCustomer = c;
            }
        }

        if (selectedCustomer == null) {
            System.out.println("Customer object is null for combo box!");
            return;
        } else {
            custIdCombo.setValue(selectedCustomer);
        }

        int userId = appt.getUserId();
        User selectedUser = null;

        for (User u : StaticObservableLists.userList) {
            if (userId == u.getUserId()) {
                selectedUser = u;
            }
        }

        if (selectedUser == null) {
            System.out.println("User object is null for combo box!");
            return;
        } else {
            userIdCombo.setValue(selectedUser);
        }

        LocalDateTime start = appt.getStart();
        LocalDateTime end = appt.getEnd();
        LocalDate selectedDate = start.toLocalDate();
        LocalTime selectedStartTime = start.toLocalTime();
        LocalTime selectedEndTime = end.toLocalTime();

        if (selectedDate == null) {
            System.out.println("Date object is null for date picker!");
            return;
        } else if (selectedStartTime == null) {
            System.out.println("Start time object is null for combo box!");
            return;
        } else if (selectedEndTime == null) {
            System.out.println("End time object is null for combo box!");
            return;
        } else {
            datePkr.setValue(selectedDate);
            startTimeCombo.setValue(selectedStartTime);
            endTimeCombo.setValue(selectedEndTime);
        }
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        ButtonEvent.cancelButtonAction("None of your changes will be saved, do you want to continue?", "Cancel button clicked", "/view/Appointments.fxml", "Appointments Table",event);
    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        System.out.println("Save button clicked!");
        int appointmentId = Integer.parseInt(idTxt.getText());
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

        for (Appointment a : StaticObservableLists.appointmentList) {
            if (a.getCustomerId() == custId) {
                StaticObservableLists.sameCustApptList.add(a);
            }
        }

        for (Appointment a : StaticObservableLists.sameCustApptList) {
            if ((localStart.isAfter(a.getStart()) || localStart.isEqual(a.getStart())) && localStart.isBefore(a.getEnd())) {
                AlertEvent.alertBox("Error Dialog", "You are creating an appointment whose start time conflicts with an existing meeting for customer #" + custId + ".");
                return;
            }
            if (localEnd.isAfter(a.getStart()) && (localEnd.isBefore(a.getEnd()) || localEnd.isEqual(a.getEnd()))) {
                AlertEvent.alertBox("Error Dialog", "You are creating an appointment whose end time conflicts with an existing meeting for customer #" + custId + ".");
                return;
            }
            if ((localStart.isBefore(a.getStart()) || localStart.isEqual(a.getStart())) && (localEnd.isAfter(a.getEnd()) || localEnd.isEqual(a.getEnd()))) {
                AlertEvent.alertBox("Error Dialog", "You are creating an appointment whose start time and end time completely envelope an existing meeting for customer #" + custId + ".");
                return;
            }
        }

        Timestamp start = Timestamp.valueOf(localStart);
        Timestamp end = Timestamp.valueOf(localEnd);

        DBAppointments.updateAppointment(appointmentId, title, description, location, type, start, end, custId, userId, contactId);
        ButtonEvent.buttonAction("/view/Appointments.fxml", "Appointment Table", event);
    }

    public void onActionStartTime(ActionEvent actionEvent) {
        LocalTime selectedStartTime = startTimeCombo.getSelectionModel().getSelectedItem();
        StaticObservableLists.endTimeList.clear();

        LocalTime start = selectedStartTime;
        LocalTime end = TimeHelper.etLocalClose.toLocalTime();

        while (start.isBefore(end.plusSeconds(1))) {
            start = start.plusMinutes(15);
            StaticObservableLists.endTimeList.add(start);
        }
        endTimeCombo.setItems(StaticObservableLists.endTimeList);
        endTimeCombo.setValue(selectedStartTime.plusMinutes(30));
    }
}

