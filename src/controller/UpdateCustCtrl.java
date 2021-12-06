package controller;

import dbAccess.DBCountries;
import dbAccess.DBCustomers;
import dbAccess.DBDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;
import tools.AlertEvent;
import tools.ButtonEvent;
import tools.TextBoxEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static tools.StaticObservableLists.countryList;
import static tools.StaticObservableLists.divisionList;

/** This class is responsible for the functionality of the "Update Cust" controller. */
public class UpdateCustCtrl implements Initializable {
    @FXML
    private TextField custIdTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField addrTxt;
    @FXML
    private TextField postalTxt;
    @FXML
    private TextField phoneTxt;
    @FXML
    private ComboBox<Country> countryCombo;
    @FXML
    private ComboBox<FirstLevelDivision> divisionCombo;

    ObservableList<FirstLevelDivision> filteredDivisionList = FXCollections.observableArrayList();

    /** This method activates when the scene starts.
     *  @param url for initialization
     *  @param resourceBundle for initialization
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryCombo.setItems(countryList);
    }

    public void sendCustomer(Customer cust) {
        custIdTxt.setText(String.valueOf(cust.getCustomerId()));
        nameTxt.setText(cust.getCustomerName());
        addrTxt.setText(cust.getAddress());
        postalTxt.setText(cust.getPostalCode());
        phoneTxt.setText(cust.getPhone());

        String country = cust.getCountry();
        Country selectedCountry = null;

        for (Country c : countryList) {
            if (country.equals(c.getCountryName())) {
                selectedCountry = c;
            }
        }

        if (selectedCountry == null) {
            System.out.println("Country object is null for combo box!");
            return;
        } else {
            countryCombo.setValue(selectedCountry);
        }

        filterDivisions(selectedCountry);
        divisionCombo.setItems(filteredDivisionList);

        String division = cust.getDivision();
        FirstLevelDivision selectedDivision = null;

        for (FirstLevelDivision fld : divisionList) {
            if (division.equals(fld.getDivision())) {
                selectedDivision = fld;
            }
        }

        if (selectedDivision == null) {
            System.out.println("Division object is null for combo box!");
            return;
        } else {
            divisionCombo.setValue(selectedDivision);
        }
    }

    /** This method activates when the Cancel button is clicked.
     *  This will clear all input fields and go back to the Customer controller.
     *  @param event object to trigger actions
     *  @throws IOException If an input or output exception occurred
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        ButtonEvent.cancelButtonAction("None of your changes will be saved, do you want to continue?", "Cancel button clicked", "/view/Customers.fxml", "Customers Table",event);
    }

    /** This method activates when the Save button is clicked.
     *  The input in the text boxes and combo boxes will be validated and then saved to an existing customer.
     *  The customer will be saved in the the database with Java based SQL methods.
     *  @param event object to trigger actions
     *  @throws IOException If an input or output exception occurred
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        System.out.println("Save button clicked!");

        int customerId = Integer.parseInt(custIdTxt.getText());
        String name = TextBoxEvent.validateString(nameTxt, "Name");
        String address = TextBoxEvent.validateString(addrTxt, "Address");
        String postalCode = TextBoxEvent.validateString(postalTxt, "Postal Code");
        String phoneNumber = TextBoxEvent.validateString(phoneTxt, "Phone Number");

        // Checks return values for each field to ensure they are valid
        if (name == null || address == null || postalCode == null || phoneNumber == null) {
            return;
        }

        if (countryCombo.getValue() == null) {
            AlertEvent.alertBox("Error Dialog", "Please select a value for the Country combo box.");
            return;
        }

        if (divisionCombo.getValue() == null) {
            AlertEvent.alertBox("Error Dialog", "Please select a value for the First Level Division combo box.");
            return;
        }
        int divisionId = divisionCombo.getSelectionModel().getSelectedItem().getDivisionId();

        DBCustomers.updateCustomer(customerId, name, address, divisionId, postalCode, phoneNumber);
        ButtonEvent.buttonAction("/view/Customers.fxml", "Customers Table", event);
    }

    public void onActionCountry() {
        Country selectedCountry = countryCombo.getSelectionModel().getSelectedItem();
        filterDivisions(selectedCountry);
    }

    public void filterDivisions(Country country) {
        filteredDivisionList.clear();
        for (FirstLevelDivision d : divisionList) {
            if (d.getCountryId() == country.getCountryId()) {
                filteredDivisionList.add(d);
            }
        }
        divisionCombo.setItems(filteredDivisionList);
        divisionCombo.setVisibleRowCount(5);
    }
}
