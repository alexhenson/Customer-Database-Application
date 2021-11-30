package controller;

import dbAccess.DBAppointments;
import dbAccess.DBCustomers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;
import tools.AlertEvent;
import tools.ButtonEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
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
    private TableColumn<Appointment, Integer> apptCustIdCol;
    @FXML
    private Button delBtn;
    @FXML
    private TableColumn<Appointment, Integer> apptIdCol;
    @FXML
    private TableView<Appointment> apptTblView;
    @FXML
    private Button updateBtn;
    @FXML
    private TableColumn<Appointment, String> contactCol;
    @FXML
    private TableColumn<Appointment, String> descCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> endDateTimeCol;
    @FXML
    private TableColumn<Appointment, String> locationCol;
    @FXML
    private Button mainMenuBtn;
    @FXML
    private RadioButton monthRBtn;
    @FXML
    private Button reportsBtn;
    @FXML
    private TableColumn<Appointment, LocalDateTime> startDateTimeCol;
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private Label titleLbl;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, Integer> userIdCol;
    @FXML
    private RadioButton weekRBtn;

    ObservableList<Appointment> apptList = DBAppointments.getAllAppointments();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apptTblView.setItems(apptList);
        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));  //get method (start method, "get" will be prepended) getStartFormatted (in appointment class)
        endDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    @FXML
    void onActionAll(ActionEvent event) {
        apptTblView.setItems(DBAppointments.getAllAppointments());
    }

    @FXML
    void onActionAdd(ActionEvent event) throws IOException {
        System.out.println("Add button clicked!");
        ButtonEvent.buttonAction("/view/AddAppt.fxml", "Add Appointment Table", event);
    }

    @FXML
    void onActionDelete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected appointment, do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Appointment delete button clicked");
            Appointment selectedAppt = apptTblView.getSelectionModel().getSelectedItem();

            if (selectedAppt == null) {
                AlertEvent.alertBox("Error Dialog", "Please select the appointment that you want to delete.");
                return;
            }
            int appointmentId = selectedAppt.getAppointmentId();
            String type = selectedAppt.getType();

            DBAppointments.deleteAppointment(appointmentId);
            apptTblView.setItems(DBAppointments.getAllAppointments());
            AlertEvent.infoBox("Info Dialog", "You have canceled a(n) " + type + " with Appointment ID# " + appointmentId);
        }
    }

    @FXML
    void onActionUpdate(ActionEvent event) throws IOException {
        System.out.println("Update button clicked!");
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/view/UpdateAppt.fxml"));
        loader.load();

        UpdateApptCtrl UApptController = loader.getController();

        if (apptTblView.getSelectionModel().getSelectedItem() == null) {
            System.out.println("Selected appointment was null.");
            AlertEvent.alertBox("Error Dialog", "Please select an appointment to update.");
            return;
        }

        UApptController.sendAppointment(apptTblView.getSelectionModel().getSelectedItem());
        CustomersCtrl.stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        CustomersCtrl.stage.setScene(new Scene(scene));
        CustomersCtrl.stage.show();
    }

    @FXML
    void onActionCust(ActionEvent event) throws IOException {
        System.out.println("Customers button clicked!");
        ButtonEvent.buttonAction("/view/Customers.fxml", "Customers Table", event);
    }

    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        System.out.println("Main Menu button clicked!");
        ButtonEvent.buttonAction("/view/MainMenu.fxml", "Main Menu", event);
    }

    @FXML
    void onActionMonth(ActionEvent event) {
        ObservableList<Appointment> apptMonthList = FXCollections.observableArrayList();

        for (Appointment a : apptList) {
            //if (a.getStart().toLocalDate() ) //need to figure out how to filter by month
        }

        apptTblView.setItems(apptMonthList);
    }

    @FXML
    void onActionReports(ActionEvent event) {

    }

    @FXML
    void onActionWeek(ActionEvent event) {

    }
}