package controller;

import bo.custom.UserBo;
import bo.custom.impl.UserBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.Optional;

public class PasswordResetController {

    @FXML
    private TextField txtEmail;

    @FXML
    private Label lblTitle1;

    @FXML
    private JFXButton btnResetPassword;

    @FXML
    private Label lblResendOtp;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblEmailError;

    @FXML
    private Label lblTitle2;

    @FXML
    private Label lblTitle3;

    @FXML
    private JFXTextField txtOtp;

    @FXML
    private Label lblPasswordError;

    private UserBo userBo = new UserBoImpl();

    @FXML
    void resendOtpLabelOnClick(MouseEvent event) {

    }

    @FXML
    void resetPasswordButtonOnAction(ActionEvent event) {
        if (!isEmailValid() | !isPasswordValid()) {
            return;
        }
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