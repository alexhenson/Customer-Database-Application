package tools;

import dbAccess.DBAppointments;
import dbAccess.DBContacts;
import dbAccess.DBCustomers;
import dbAccess.DBUsers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;

import java.time.LocalTime;

public class StaticObservableLists {
    public static ObservableList<String> typeList = Appointment.getTypeList();
    public static ObservableList<Contact> contactList = DBContacts.getAllContacts();
    public static ObservableList<Customer> customerList = DBCustomers.getAllCustomers();
    public static ObservableList<Appointment> appointmentList = DBAppointments.getAllAppointments();
    public static ObservableList<User> userList = DBUsers.getAllUsers();
    public static ObservableList<LocalTime> startTimeList = FXCollections.observableArrayList();
    public static ObservableList<LocalTime> endTimeList = FXCollections.observableArrayList();
    public static ObservableList<Appointment> sameCustApptList = FXCollections.observableArrayList();
}
