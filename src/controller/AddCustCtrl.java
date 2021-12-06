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
import model.FirstLevelDivision;
import tools.AlertEvent;
import tools.ButtonEvent;
import tools.TextBoxEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustCtrl implements Initializable {
    @FXML
    private TextField addrTxt;
    @FXML
    private TextField postalTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField phoneTxt;
    @FXML
    private ComboBox<Country> countryCombo;
    @FXML
    private ComboBox<FirstLevelDivision> divisionCombo;

    ObservableList<FirstLevelDivision> divisionList = DBDivisions.getAllDivisions();
    ObservableList<FirstLevelDivision> filteredDivisionList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Country> countryList = DBCountries.getAllCountries();
        countryCombo.setPromptText("First, select a country.");
        divisionCombo.setPromptText("Second, select a division.");
        countryCombo.setItems(countryList);
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        ButtonEvent.cancelButtonAction("This will clear all field values, do you want to continue?", "Cancel button clicked", "/view/Customers.fxml", "Customers Table",event);
    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        System.out.println("Save button clicked!");

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
        int division = divisionCombo.getSelectionModel().getSelectedItem().getDivisionId();

        DBCustomers.addCustomer(name, address, division, postalCode, phoneNumber);
        ButtonEvent.buttonAction("/view/Customers.fxml", "Customers Table", event);
    }

    public void onActionCountry() {
        Country selectedCountry = countryCombo.getSelectionModel().getSelectedItem();
        filteredDivisionList.clear();
        for (FirstLevelDivision d : divisionList) {
            if (d.getCountryId() == selectedCountry.getCountryId()) {
                filteredDivisionList.add(d);
            }
        }
        divisionCombo.setItems(filteredDivisionList);
        divisionCombo.setVisibleRowCount(5);
    }
}
