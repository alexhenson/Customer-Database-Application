package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import model.Appointment;
import tools.StaticObservableLists;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportsCtrl implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onActionCust(ActionEvent actionEvent) {
        int countPlanning = 0;
        int countDebrief = 0;
        int countFinancial = 0;
        int countBrainstorm = 0;
        int countCareer = 0;
        int[] countByMonth = new int[12];

        for (Appointment a : StaticObservableLists.appointmentList) {
            if
        }
        String str =

    }

    public void onActionMainMenu(ActionEvent actionEvent) {
    }

    public void onActionAppt(ActionEvent actionEvent) {
    }
}
