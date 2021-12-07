package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/** This class is responsible for the functionality of the Appointment class. */
public class Appointment {

    // This Observable list will a typeList of Strings to fill the 'Meeting Type' combo boxes in Add and Update Appointments
    private static ObservableList<String> typeList = FXCollections.observableArrayList();

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerId;
    private int userId;

    /** This constructor initializes the fields from the ten parameters.
     *   @param appointmentId to set appointmentId
     *   @param title to set title
     *   @param description to set description
     *   @param location to set location
     *   @param contact to set contact
     *   @param type to set type
     *   @param start to set start
     *   @param end to set end
     *   @param customerId to set customerId
     *   @param userId to set userId
     */
    public Appointment(int appointmentId, String title, String description, String location, String contact, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
    }

    public static ObservableList<String> getTypeList() {
        return typeList;
    }

    public static void setTypeList(ObservableList<String> typeList) {
        Appointment.typeList = typeList;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
        ZonedDateTime startZDT = start.atZone(ZoneId.systemDefault());
        return startZDT.format(formatter);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public String getEndString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
        ZonedDateTime endZDT = end.atZone(ZoneId.systemDefault());
        return endZDT.format(formatter);
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String toString() {
        return "Appointment ID: " + appointmentId + ", Title: " + title + ", Type: " + type +
                ", Description: " + description + ", Start Date and Time: " + getStartString() +
                ", End Date and Time: " + getEndString() + ", Customer ID " + customerId;
    }
}
