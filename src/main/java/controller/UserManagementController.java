package controller;

import bo.custom.UserBo;
import bo.custom.impl.UserBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dto.UserDto;
import dto.tm.UserTm;
import entity.util.UserRole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
    private TableView<UserTm> tblUsers;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colEmail;

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

    private UserBo userBo = new UserBoImpl();

    public void addUserButtonOnAction(ActionEvent actionEvent) {
    }

    public void cancelButtonOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbxRole.getItems().add("Admin");
        cmbxRole.getItems().add("Staff");

        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("cmbxRole"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("cmbxStatus"));
        loadItems();
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

    private void loadItems() {
        ObservableList<UserTm> tmList = FXCollections.observableArrayList();
        List<UserDto> dtoList = null;
        try {
            dtoList = userBo.allUsers();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (UserDto userDto : dtoList) {
            JFXComboBox<String> cmbxRole = new JFXComboBox<>();
            cmbxRole.getItems().add("Admin");
            cmbxRole.getItems().add("Staff");
            cmbxRole.getSelectionModel()
                    .select(userDto.getRole() == UserRole.ADMIN ? 0 : 1);

            JFXComboBox<String> cmbxStatus = new JFXComboBox<>();
            cmbxStatus.getItems().add("Active");
            cmbxStatus.getItems().add("Disabled");
            cmbxStatus.getSelectionModel()
                    .select(userDto.isActive() ? 0 : 1);

            UserTm userTm = new UserTm(
                    userDto.getUserId(),
                    userDto.getName(),
                    userDto.getEmail(),
                    cmbxRole,
                    cmbxStatus
            );

            tmList.add(userTm);
        }

        tblUsers.setItems(tmList);
    }
}
