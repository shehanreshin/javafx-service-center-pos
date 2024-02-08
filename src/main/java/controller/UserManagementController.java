package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserManagementController implements Initializable {
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton btnAddUser;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private Label lblNoOfItems;

    @FXML
    private Label lblBasicCost;

    @FXML
    private Label lblAdditionalCost;

    @FXML
    private TableView tblUsers;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colRole;

    @FXML
    private TableColumn colStatus;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private JFXComboBox<String> cmbxRole;

    public void addUserButtonOnAction(ActionEvent actionEvent) {
    }

    public void cancelButtonOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbxRole.getItems().add("Admin");
        cmbxRole.getItems().add("Staff");
    }

    private void clearFields() {
        txtEmail.clear();
        txtName.clear();
        txtPassword.clear();
        cmbxRole.getSelectionModel().clearSelection();
    }

    private boolean isFieldsEmpty() {
        return txtEmail.getText().isEmpty() || txtName.getText().isEmpty() ||
                txtPassword.getText().isEmpty() || cmbxRole.getSelectionModel().isEmpty();
    }
}
