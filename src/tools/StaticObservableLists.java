package tools;

import dbAccess.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.time.LocalTime;

/** This helper class is responsible for keeping all of the ObservableLists in the program in one place to be accessed statically. */
public class StaticObservableLists {
    private static ObservableList<LocalTime> startTimeList = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> endTimeList = FXCollections.observableArrayList();
    private static ObservableList<Appointment> sameCustApptList = FXCollections.observableArrayList();

    public static ObservableList<Customer> getCustomerList() {
        return DBCustomers.getAllCustomers();
    }
    public static ObservableList<Appointment> getAppointmentList() {
        return DBAppointments.getAllAppointments();
    }

    public static ObservableList<Contact> getContactList() {
        return DBContacts.getAllContacts();
    }

    public static ObservableList<Country> getCountryList() {
        return DBCountries.getAllCountries();
    }

    public static ObservableList<FirstLevelDivision> getDivisionList() {
        return DBDivisions.getAllDivisions();
    }

    public static ObservableList<User> getUserList() {
        return DBUsers.getAllUsers();
    }

    public static ObservableList<LocalTime> getStartTimeList() {
        return startTimeList;
    }

    public static ObservableList<LocalTime> getEndTimeList() {
        return endTimeList;
    }

    public static ObservableList<Appointment> getSameCustApptList() {
        return sameCustApptList;
    }

    public static void setStartTimeList(LocalTime start) {
        startTimeList.add(start);
    }

    public static void setEndTimeList(LocalTime end) {
        endTimeList.add(end);
    }

    public static void setSameCustApptList(Appointment sameCustAppt) {
        sameCustApptList.add(sameCustAppt);
    }

    public static void clearEndTimeList() {
        endTimeList.clear();
    }
}
