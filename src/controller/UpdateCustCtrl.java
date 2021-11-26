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
import javafx.scene.layout.AnchorPane;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;
import tools.AlertEvent;
import tools.ButtonEvent;
import tools.TextBoxEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateCustCtrl implements Initializable {

    @FXML
    private AnchorPane updateCust;
    @FXML
    private Label titleLbl;
    @FXML
    private Label idLbl;
    @FXML
    private Label nameLbl;
    @FXML
    private Label addrLbl;
    @FXML
    private Label postalLbl;
    @FXML
    private Label phoneLbl;
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
    private TextField Txt;
    @FXML
    private Label countryLbl;
    @FXML
    private Label divisionLbl;
    @FXML
    private ComboBox<Country> countryCombo;
    @FXML
    private ComboBox<FirstLevelDivision> divisionCombo;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;

    ObservableList<Country> countryList = DBCountries.getAllCountries();
    ObservableList<FirstLevelDivision> divisionList = DBDivisions.getAllDivisions();

    ObservableList<FirstLevelDivision> filteredDivisionList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryCombo.setItems(countryList);
        divisionCombo.setItems(divisionList);
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

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        ButtonEvent.cancelButtonAction("None of your changes will be saved, do you want to continue?", "Cancel button clicked", "/view/Customers.fxml", "Customers Table",event);
    }

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
        String country = countryCombo.getSelectionModel().getSelectedItem().getCountryName();

        if (divisionCombo.getValue() == null) {
            AlertEvent.alertBox("Error Dialog", "Please select a value for the First Level Division combo box.");
            return;
        }
        int divisionId = divisionCombo.getSelectionModel().getSelectedItem().getDivisionId();

        DBCustomers.modifyCustomer(customerId, name, address, divisionId, postalCode, phoneNumber);
        ButtonEvent.buttonAction("/view/Customers.fxml", "Customers Table", event);
    }

    public void onActionCountry(ActionEvent actionEvent) {
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

    public void onActionDivision(ActionEvent actionEvent) {
    }
}
