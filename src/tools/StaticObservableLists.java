package tools;

import dbAccess.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.time.LocalTime;

/** This helper class is responsible for keeping all of the ObservableLists in the program in one place to be accessed statically. */
public class StaticObservableLists {
    public static ObservableList<String> typeList = Appointment.getTypeList();
    public static ObservableList<Contact> contactList = DBContacts.getAllContacts();
    public static ObservableList<Customer> customerList = DBCustomers.getAllCustomers();
    public static ObservableList<Appointment> appointmentList = DBAppointments.getAllAppointments();
    public static ObservableList<Country> countryList = DBCountries.getAllCountries();
    public static ObservableList<FirstLevelDivision> divisionList = DBDivisions.getAllDivisions();
    public static ObservableList<User> userList = DBUsers.getAllUsers();
    public static ObservableList<LocalTime> startTimeList = FXCollections.observableArrayList();
    public static ObservableList<LocalTime> endTimeList = FXCollections.observableArrayList();
    public static ObservableList<Appointment> sameCustApptList = FXCollections.observableArrayList();
}
