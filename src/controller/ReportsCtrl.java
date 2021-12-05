package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import model.Appointment;
import model.Contact;
import tools.ButtonEvent;
import tools.StaticObservableLists;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.EnumSet;
import java.util.Locale;
import java.util.ResourceBundle;

public class ReportsCtrl implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private RadioButton custApptRBtn;
    @FXML
    private RadioButton contactRBtn;
    @FXML
    private RadioButton dayRButton;
    @FXML
    private Button mainMenuBtn;
    @FXML
    private Button apptBtn;
    @FXML
    private Button custBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void onActionCust(ActionEvent actionEvent) throws IOException {
        System.out.println("Customers button clicked!");
        ButtonEvent.buttonAction("/view/Customers.fxml", "Customers Table", actionEvent);
    }

    public void onActionMainMenu(ActionEvent actionEvent) throws IOException {
        System.out.println("Main Menu button clicked!");
        ButtonEvent.buttonAction("/view/MainMenu.fxml", "Main Menu", actionEvent);
    }

    public void onActionAppt(ActionEvent actionEvent) throws IOException {
        System.out.println("Appointments button clicked!");
        ButtonEvent.buttonAction("/view/Appointments.fxml", "Appointments Table", actionEvent);
    }

    public void onActionCustApptRBtn(ActionEvent actionEvent) {
        System.out.println("Customer Radio button selected.");
        textArea.clear();
        int countPlanning = 0;
        int countDebrief = 0;
        int countFinancial = 0;
        int countBrainstorm = 0;
        int countCareer = 0;
        int[] countByMonth = new int[12];

        for (Appointment a : StaticObservableLists.appointmentList) {
            if (a.getType().equals("Planning Session")) {
                countPlanning++;
            } else if (a.getType().equals("De-Briefing")) {
                countDebrief++;
            } else if (a.getType().equals("Financial Advisory")) {
                countFinancial++;
            } else if (a.getType().equals("Brainstorming Session")) {
                countBrainstorm++;
            } else if (a.getType().equals("Career Planning")) {
                countCareer++;
            }

            int month = a.getStart().getMonthValue();
            countByMonth[month - 1]++;
        }
        String custStr = "Customer Appointment Counts by Meeting Type: \n" +
                "Planning Session: " + countPlanning + "\n" +
                "De-Briefing: " + countDebrief + "\n" +
                "Financial Advisory: " + countFinancial + "\n" +
                "Brainstorming Session: " + countBrainstorm + "\n" +
                "Career Planning: " + countCareer + "\n\n";

        custStr += "Customer Appointment Counts by Month:\n";

        for (int i = 0; i < countByMonth.length; i++) {
            custStr += "Month " + (i + 1) + ": " + countByMonth[i] + "\n";
        }
        textArea.setText(custStr);
    }

    public void onActionContactRBtn(ActionEvent actionEvent) {
        System.out.println("Contact Radio button selected.");
        textArea.clear();

        String contactStr = "";
        for (Contact c : StaticObservableLists.contactList) {
            contactStr += "Contact Name - " + c + ":\n";
            for (Appointment a : StaticObservableLists.appointmentList) {
                if (a.getContact().equals(c.getContactName())) {
                    contactStr += a + "\n";
                }
            }
            contactStr += "\n";
        }
        textArea.setText(contactStr);
    }

    public void onActionDayRBtn(ActionEvent actionEvent) {
        System.out.println("Day Radio button selected.");
        textArea.clear();

        String dayStr = "";

        EnumSet<DayOfWeek> dows = EnumSet.allOf(DayOfWeek.class);
        dayStr += "Appointment Counts by Days of the Week:\n";
        for(DayOfWeek dow : dows) {
            String dowName = dow.getDisplayName(TextStyle.FULL , Locale.US);
            int apptCount = 0;
            for (Appointment a : StaticObservableLists.appointmentList) {
                if (a.getStart().getDayOfWeek().equals(dow)) {
                    apptCount++;
                }
            }
            dayStr += dowName + ": " + apptCount + "\n";
            apptCount = 0;
        }
        textArea.setText(dayStr);
    }
}
