package controller;

import bo.custom.UserBo;
import bo.custom.impl.UserBoImpl;
import com.jfoenix.controls.JFXButton;
import dto.UserDto;
import dto.wrapper.UserDtoWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController {
    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label lblLogin;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Label lblForgotPassword;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblEmailError;

    @FXML
    private Label lblPasswordError;

    UserBo userBo = new UserBoImpl();

    @FXML
    void forgotPasswordLabelOnClick(MouseEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/password-reset-page.fxml"))));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Internal server error");
            alert.showAndWait();
            return;
        }
    }

    @FXML
    void loginButtonOnAction(ActionEvent event) {
        if (!isEmailValid() | !isPasswordValid()) {
            return;
        }

        boolean isValidUser;
        UserDtoWrapper userCredentials = new UserDtoWrapper(txtEmail.getText(), txtPassword.getText());
        try {
            isValidUser = userBo.isUserCredentialsValid(userCredentials);
        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Internal server error");
            alert.showAndWait();
            return;
        }

        if (!isValidUser) {
            lblPasswordError.setText("Incorrect password");
            lblPasswordError.setVisible(true);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login successful");
        alert.setContentText("You're in!");
        alert.showAndWait();
    }

    private boolean isEmailValid() {
        if (isEmailEmpty()) {
            lblEmailError.setText("This field is required");
            lblEmailError.setVisible(true);
            return false;
        }

        Optional<UserDto> userDto;

        try {
            userDto = Optional.ofNullable(userBo.searchUserByEmail(txtEmail.getText()));
        } catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Internal server error");
            alert.showAndWait();
            return false;
        }

        if (!userDto.isPresent()) {
            lblEmailError.setText("Couldn't find your account");
            lblEmailError.setVisible(true);
            return false;
        }

        lblEmailError.setVisible(false);
        return true;
    }

    private boolean isPasswordValid() {
        if (isPasswordEmpty()) {
            lblPasswordError.setText("This field is required");
            lblPasswordError.setVisible(true);
            return false;
        }

        lblPasswordError.setVisible(false);
        return true;
    }

    private boolean isEmailEmpty() {
        return txtEmail.getText().isEmpty();
    }

    private boolean isPasswordEmpty() {
        return txtPassword.getText().isEmpty();
    }
}
