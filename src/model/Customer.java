package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer {
    private ObservableList<Appointment> associatedAppt = FXCollections.observableArrayList();
    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private FirstLevelDivision firstLevelDivision;
    //private Country country; // probably don't need this, and can access through the first level division object

    public Customer(int customerID, String customerName, String address, String postalCode, String phone, FirstLevelDivision firstLevelDivision) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.firstLevelDivision = firstLevelDivision;
        //this.country = country;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

    public FirstLevelDivision getFirstLevelDivision() {
        return firstLevelDivision;
    }

    public void setFirstLevelDivision(FirstLevelDivision firstLevelDivision) {
        this.firstLevelDivision = firstLevelDivision;
    }

    /*
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

     */

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
