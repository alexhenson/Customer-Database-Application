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

    /**
     * @return ObservableList<Customer> via DBCustomers.getAllCustomers()
     */
    public static ObservableList<Customer> getCustomerList() {
        return DBCustomers.getAllCustomers();
    }

    /**
     * @return ObservableList<Appointment> via DBAppointments.getAllAppointments()
     */
    public static ObservableList<Appointment> getAppointmentList() {
        return DBAppointments.getAllAppointments();
    }

    /**
     * @return ObservableList<Appointment> via DBContacts.getAllContacts()
     */
    public static ObservableList<Contact> getContactList() {
        return DBContacts.getAllContacts();
    }

    /**
     * @return ObservableList<Country> via DBCountries.getAllCountries()
     */
    public static ObservableList<Country> getCountryList() {
        return DBCountries.getAllCountries();
    }

    /**
     * @return ObservableList<FirstLevelDivision> via DBDivisions.getAllDivisions()
     */
    public static ObservableList<FirstLevelDivision> getDivisionList() {
        return DBDivisions.getAllDivisions();
    }

    /**
     * @return ObservableList<User> via DBUsers.getAllUsers()
     */
    public static ObservableList<User> getUserList() {
        return DBUsers.getAllUsers();
    }

    /**
     * @return ObservableList<LocalTime> via startTimeList
     */
    public static ObservableList<LocalTime> getStartTimeList() {
        return startTimeList;
    }

    /**
     * @return ObservableList<LocalTime> via endTimeList
     */
    public static ObservableList<LocalTime> getEndTimeList() {
        return endTimeList;
    }

    /**
     * @return ObservableList<Appointment> via sameCustApptList
     */
    public static ObservableList<Appointment> getSameCustApptList() {
        return sameCustApptList;
    }

    /**
     * @param start the start to add to the list
     */
    public static void addStartTimeList(LocalTime start) {
        startTimeList.add(start);
    }

    /**
     * @param end the end to add to the list
     */
    public static void addEndTimeList(LocalTime end) {
        endTimeList.add(end);
    }

    /**
     * @param sameCustAppt the sameCustAppt to add to the list
     */
    public static void setSameCustApptList(Appointment sameCustAppt) {
        sameCustApptList.add(sameCustAppt);
    }

    /** Clears endTimeList */
    public static void clearEndTimeList() {
        endTimeList.clear();
    }

    /** Clears sameCustApptList */
    public static void clearSameCustApptList() {
        sameCustApptList.clear();
    }
}
