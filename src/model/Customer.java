package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer {
    private ObservableList<Appointment> associatedAppt = FXCollections.observableArrayList();
    private int customerId;
    private String customerName;
    private String address;
    private String division;
    private String postalCode;
    private String country;
    private String phone;

    public Customer(int customerId, String customerName, String address, String division, String postalCode, String country, String phone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.division = division;
        this.postalCode = postalCode;
        this.country = country;
        this.phone = phone;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /** This method adds an Appointment object to the ObservableList associatedAppt.
     *  @param appt Appointment object to add to associatedAppt Observable List
     */
    public void addAssociatedAppt(Appointment appt) {
        associatedAppt.add(appt);
    }

    /** This method deletes an Appointment object from the ObservableList associatedAppt.
     * @param selectedAssociatedAppt Appointment object selected in the TableView object
     *  @return boolean value for whether Appointment was removed or not
     */
    public boolean deleteAssociatedAppt(Appointment selectedAssociatedAppt) {
        return associatedAppt.remove(selectedAssociatedAppt);
    }

    /** This method returns the ObservableList associatedAppt.
     * @return associatedAppt
     */
    public ObservableList<Appointment> getAllAssociatedAppt() {
        return associatedAppt;
    }
}
