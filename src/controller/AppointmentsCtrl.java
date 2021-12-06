package controller;

import dbAccess.DBAppointments;
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
import javafx.stage.Stage;
import model.Appointment;
import tools.AlertEvent;
import tools.ButtonEvent;
import tools.TimeHelper;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

import static tools.StaticObservableLists.appointmentList;

/** This class is responsible for the functionality of the "Appointments" controller. */
public class AppointmentsCtrl implements Initializable {
    @FXML
    private TableColumn<Appointment, Integer> apptCustIdCol;
    @FXML
    private TableColumn<Appointment, Integer> apptIdCol;
    @FXML
    private TableView<Appointment> apptTblView;
    @FXML
    private TableColumn<Appointment, String> contactCol;
    @FXML
    private TableColumn<Appointment, String> descCol;
    @FXML
    private TableColumn<Appointment, String> endDateTimeCol;
    @FXML
    private TableColumn<Appointment, String> locationCol;
    @FXML
    private TableColumn<Appointment, String> startDateTimeCol;
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, Integer> userIdCol;

    /** This method activates when the scene starts.
     *  @param url for initialization
     *  @param resourceBundle for initialization
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apptTblView.setItems(appointmentList);
        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startString"));
        endDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endString"));
        apptCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    /** This method is responsible for setting the apptTblView to show all Appointments. */
    @FXML
    void onActionAll() {
        apptTblView.setItems(DBAppointments.getAllAppointments());
    }

    /** This method is responsible for setting the apptTblView to show all Appointments in the current month. */
    @FXML
    void onActionMonth() {
        ObservableList<Appointment> apptMonthList = FXCollections.observableArrayList();
        for (Appointment a : appointmentList) {
            if (TimeHelper.currentMonth.equals(a.getStart().getMonth())) {
                apptMonthList.add(a);
            }
        }
        apptTblView.setItems(apptMonthList);
    }

    /** This method is responsible for setting the apptTblView to show all Appointments in the next seven days. */
    @FXML
    void onActionWeek() {
        ObservableList<Appointment> apptWeekList = FXCollections.observableArrayList();
        for (Appointment a : appointmentList) {
            LocalDate apptDate = a.getStart().toLocalDate();
            if ((apptDate.isAfter(TimeHelper.currentDay) || apptDate.isEqual(TimeHelper.currentDay)) && (apptDate.isBefore(TimeHelper.nextWeekDay) || apptDate.isEqual(TimeHelper.nextWeekDay))) {
                apptWeekList.add(a);
            }
        }
        apptTblView.setItems(apptWeekList);
    }

    /** This method will take you to the Add Appointment Form.
     *  @param event object to trigger actions
     *  @throws IOException If an input or output exception occurred
     */
    @FXML
    void onActionAdd(ActionEvent event) throws IOException {
        System.out.println("Add button clicked!");
        ButtonEvent.buttonAction("/view/AddAppt.fxml", "Add Appointment Table", event);
    }

    /** This method will delete the selected Appointment.
     *  You must select the Appointment you wish to delete in the TableView.
     */
    @FXML
    void onActionDelete() {
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

    /** This method will take you to the Update Appointment Form.
     *  You must select the Appointment you wish to modify in the TableView.
     *  @param event object to trigger actions
     *  @throws IOException If an input or output exception occurred
     */
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
        CustomersCtrl.stage.centerOnScreen();
        CustomersCtrl.stage.show();
    }

    /** This method will change the scene to the Customers form.
     *  @param event object to trigger actions
     *  @throws IOException If an input or output exception occurred
     */
    @FXML
    void onActionCust(ActionEvent event) throws IOException {
        System.out.println("Customers button clicked!");
        ButtonEvent.buttonAction("/view/Customers.fxml", "Customers Table", event);
    }

    /** This method will change the scene to the Main Menu form.
     *  @param event object to trigger actions
     *  @throws IOException If an input or output exception occurred
     */
    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        System.out.println("Main Menu button clicked!");
        ButtonEvent.buttonAction("/view/MainMenu.fxml", "Main Menu", event);
    }

    /** This method will change the scene to the Reports form.
     *  @param event object to trigger actions
     *  @throws IOException If an input or output exception occurred
     */
    @FXML
    void onActionReports(ActionEvent event) throws IOException {
        System.out.println("Reports button clicked!");
        ButtonEvent.buttonAction("/view/Reports.fxml", "Reports Form", event);
    }
}