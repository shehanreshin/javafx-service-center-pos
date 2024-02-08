package controller;

import bo.custom.UserBo;
import bo.custom.impl.UserBoImpl;
import bo.util.ApplicationState;
import bo.util.HashConverter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dto.UserDto;
import dto.tm.UserTm;
import entity.util.UserRole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ItemsController implements Initializable {
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
        if (isFieldsEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
            return;
        }

        String password;
        boolean isUserAdded;

        try {
            password = HashConverter.getInstance().toHexString(
                    HashConverter.getInstance().getHash(txtPassword.getText())
            );
            isUserAdded = userBo.saveUser(new UserDto(
                    txtName.getText(),
                    txtEmail.getText(),
                    password,
                    cmbxRole.getSelectionModel().getSelectedItem() == "Admin" ? UserRole.ADMIN : UserRole.STAFF,
                    true
            ));
        } catch (NoSuchAlgorithmException | SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Internal server error");
            alert.showAndWait();
            return;
        }

        if (isUserAdded) {
            clearFields();
            loadItems();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Added");
            alert.setContentText("User added successfully");
            alert.showAndWait();
            return;
        }
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
        tblUsers.refresh();
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
            ComboBox<String> cmbxRole = new ComboBox<>();
            cmbxRole.getItems().add("Admin");
            cmbxRole.getItems().add("Staff");
            cmbxRole.getSelectionModel()
                    .select(userDto.getRole() == UserRole.ADMIN ? 0 : 1);

            ComboBox<String> cmbxStatus = new ComboBox<>();
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

            cmbxRole.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                try {
                    userBo.updateRole(userDto.getUserId(), (newValue.equals("Admin") ? UserRole.ADMIN : UserRole.STAFF));
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });

            cmbxStatus.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                try {
                    userBo.updateStatus(userDto.getUserId(), newValue.equals("Active"));
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });

            tmList.add(userTm);
        }

        tblUsers.setItems(tmList);
    }

    public void usersButtonOnAction(ActionEvent actionEvent) {
        if (ApplicationState.getInstance().getLoggedInUser().getRole() != UserRole.ADMIN) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insufficient Privileges");
            alert.setContentText("You do not have the authorization to do that");
            alert.showAndWait();
            return;
        }
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/user-management.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void homeButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/home-electronic.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void reportsButtonOnAction(ActionEvent actionEvent) {
        if (ApplicationState.getInstance().getLoggedInUser().getRole() != UserRole.ADMIN) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insufficient Privileges");
            alert.setContentText("You do not have the authorization to do that");
            alert.showAndWait();
            return;
        }

        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/reports.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void itemsButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/items.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void ordersButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/orders.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void settingsButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/settings.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void logoutButtonOnAction(ActionEvent actionEvent) {
        ApplicationState.getInstance().setLoggedInUser(null);
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/login-page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }
}
