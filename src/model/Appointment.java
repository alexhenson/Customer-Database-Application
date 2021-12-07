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

    /**
     * @return the typeList
     */
    public static ObservableList<String> getTypeList() {
        return typeList;
    }

    /**
     * @param typeList the typeList to set
     */
    public static void setTypeList(ObservableList<String> typeList) {
        Appointment.typeList = typeList;
    }

    /**
     * @return the appointmentId
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * @param appointmentId the appointmentId to set
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the LocalDateTime object as a String
     */
    public String getStartString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
        ZonedDateTime startZDT = start.atZone(ZoneId.systemDefault());
        return startZDT.format(formatter);
    }

    /**
     * @return the start
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * @return the LocalDateTime object as a String
     */
    public String getEndString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
        ZonedDateTime endZDT = end.atZone(ZoneId.systemDefault());
        return endZDT.format(formatter);
    }

    /**
     * @return the end
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the object as a String
     */
    public String toString() {
        return "Appointment ID: " + appointmentId + ", Title: " + title + ", Type: " + type +
                ", Description: " + description + ", Start Date and Time: " + getStartString() +
                ", End Date and Time: " + getEndString() + ", Customer ID " + customerId;
    }
}
