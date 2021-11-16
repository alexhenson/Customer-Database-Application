package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CustApptCtrl {
    @FXML
    private AnchorPane CustomerAppoint;
    @FXML
    private TableColumn<?, ?> addressCol;
    @FXML
    private Button apptAddBtn;
    @FXML
    private TableColumn<?, ?> apptCustIdCol;
    @FXML
    private Button apptDelBtn;
    @FXML
    private TableColumn<?, ?> apptIdCol;
    @FXML
    private Label apptLbl;
    @FXML
    private TextField apptSearchTxt;
    @FXML
    private TableView<?> apptTblView;
    @FXML
    private Button apptUpdateBtn;
    @FXML
    private TableColumn<?, ?> contactCol;
    @FXML
    private TableColumn<?, ?> countryCol11;
    @FXML
    private Button custAddBtn;
    @FXML
    private Button custDelBtn;
    @FXML
    private TableColumn<?, ?> custIdCol;
    @FXML
    private Label custLbl;
    @FXML
    private TextField custSearchTxt;
    @FXML
    private TableView<?> custTblView;
    @FXML
    private Button custUpdateBtn;
    @FXML
    private TableColumn<?, ?> descCol;
    @FXML
    private TableColumn<?, ?> divisionCol;
    @FXML
    private TableColumn<?, ?> endCol;
    @FXML
    private Button exitBtn;
    @FXML
    private TableColumn<?, ?> locationCol;
    @FXML
    private TableColumn<?, ?> nameCol;
    @FXML
    private TableColumn<?, ?> phoneCol;
    @FXML
    private TableColumn<?, ?> postalCol;
    @FXML
    private TableColumn<?, ?> startCol;
    @FXML
    private TableColumn<?, ?> titleCol;
    @FXML
    private Label titleLbl;
    @FXML
    private TableColumn<?, ?> typeCol;
    @FXML
    private TableColumn<?, ?> userIdCol;

    public void onActionCustAdd(ActionEvent actionEvent) {
    }

    public void onActionCustUpdate(ActionEvent actionEvent) {
    }

    public void onActionCustDelete(ActionEvent actionEvent) {
    }

    public void onActionCustSearch(ActionEvent actionEvent) {
    }

    public void onActionApptAdd(ActionEvent actionEvent) {
    }

    public void onActionApptUpdate(ActionEvent actionEvent) {
    }

    public void onActionApptDelete(ActionEvent actionEvent) {
    }

    public void onActionApptSearch(ActionEvent actionEvent) {
    }

    public void onActionExit(ActionEvent actionEvent) {
    }
}
