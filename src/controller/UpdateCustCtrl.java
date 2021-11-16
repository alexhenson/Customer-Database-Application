package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tools.GUIEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateCustCtrl implements Initializable {

    @FXML
    private Button addBtn;
    @FXML
    private AnchorPane addCust;
    @FXML
    private TableColumn<?, ?> btmApptCustIdCol;
    @FXML
    private TableColumn<?, ?> btmApptIdCol;
    @FXML
    private TableView<?> btmApptTblView;
    @FXML
    private TableColumn<?, ?> btmContactCol;
    @FXML
    private TableColumn<?, ?> btmDescCol;
    @FXML
    private TableColumn<?, ?> btmEndCol;
    @FXML
    private TableColumn<?, ?> btmLocationCol;
    @FXML
    private TableColumn<?, ?> btmStartCol;
    @FXML
    private TableColumn<?, ?> btmTitleCol;
    @FXML
    private TableColumn<?, ?> btmTypeCol;
    @FXML
    private TableColumn<?, ?> btmUserIdCol;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label idLbl;
    @FXML
    private TextField idTxt;
    @FXML
    private Label invLbl;
    @FXML
    private TextField invTxt;
    @FXML
    private Label maxLbl;
    @FXML
    private TextField maxTxt;
    @FXML
    private Label nameLbl;
    @FXML
    private TextField nameTxt;
    @FXML
    private Label priceLbl;
    @FXML
    private TextField priceTxt;
    @FXML
    private Button removeBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private TextField searchTxt;
    @FXML
    private Label titleLbl;
    @FXML
    private TableColumn<?, ?> topApptCustIdCol;
    @FXML
    private TableColumn<?, ?> topApptIdCol;
    @FXML
    private TableView<?> topApptTblView;
    @FXML
    private TableColumn<?, ?> topContactCol;
    @FXML
    private TableColumn<?, ?> topDescCol;
    @FXML
    private TableColumn<?, ?> topEndCol;
    @FXML
    private TableColumn<?, ?> topLocationCol;
    @FXML
    private TableColumn<?, ?> topStartCol;
    @FXML
    private TableColumn<?, ?> topTitleCol;
    @FXML
    private TableColumn<?, ?> topTypeCol;
    @FXML
    private TableColumn<?, ?> topUserIdCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onActionAdd(ActionEvent event) {

    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        GUIEvent.cancelButtonAction("None of your changes will be saved, do you want to continue?", "Cancel button clicked", "/view/Customers.fxml", "Customer Appointment Form",event);
    }

    @FXML
    void onActionRemove(ActionEvent event) {

    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        System.out.println("Save button clicked!");
        GUIEvent.buttonAction("/view/Customers.fxml", "Customer Appointment Form", event);
    }

    @FXML
    void onActionSearch(ActionEvent event) {

    }
}
