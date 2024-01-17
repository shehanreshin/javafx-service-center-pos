package controller;

import bo.custom.UserBo;
import bo.custom.impl.UserBoImpl;
import bo.util.Mailer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.UserDto;
import jakarta.mail.MessagingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class PasswordResetController implements Initializable {

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

    @FXML
    private Label lblOtpError;

    private UserBo userBo = new UserBoImpl();

    @FXML
    void resendOtpLabelOnClick(MouseEvent event) {
        try {
            userBo.sendOtpByEmail("asp.shehanreshin@gmail.com");
        } catch (MessagingException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Internal server error");
            alert.showAndWait();
        }
    }

    @FXML
    void resetPasswordButtonOnAction(ActionEvent event) throws SQLException, NoSuchAlgorithmException, ClassNotFoundException {
        if (!isEmailValid() | !isPasswordValid() | !isOtpValid()) {
            return;
        }

        boolean isPasswordUpdated = userBo.updatePassword(txtEmail.getText(), txtPassword.getText());

        if (!isPasswordUpdated) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Internal server error");
            alert.showAndWait();
            return;
        }

        clearFields();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Password Reset");
        alert.setContentText("The password has been successfully reset");
        alert.showAndWait();
    }

    private boolean isOtpValid() {
        if (isOtpEmpty()) {
            lblOtpError.setText("This field is required");
            lblOtpError.setVisible(true);
            return false;
        }

        int inputOtp = -1;
        try {
            inputOtp = Integer.parseInt(txtOtp.getText());
        } catch (Exception e) {
            lblOtpError.setText("Invalid OTP");
            lblOtpError.setVisible(true);
            return false;
        }

        int currentOtp = userBo.getCurrentOtp();
        if (inputOtp != currentOtp) {
            lblOtpError.setText("Incorrect OTP");
            lblOtpError.setVisible(true);
            return false;
        }

        lblOtpError.setVisible(false);
        return true;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            userBo.sendOtpByEmail("asp.shehanreshin@gmail.com");
        } catch (MessagingException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Internal server error");
            alert.showAndWait();
        }
    }

    private boolean isOtpEmpty() {
        return txtOtp.getText().isEmpty();
    }

    private void clearFields() {
        txtEmail.clear();
        txtPassword.clear();
        txtOtp.clear();
    }
}
