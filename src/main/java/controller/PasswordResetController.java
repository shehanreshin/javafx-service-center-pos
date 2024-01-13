package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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

    @FXML
    void resendOtpLabelOnClick(MouseEvent event) {

    }

    @FXML
    void resetPasswordButtonOnAction(ActionEvent event) {

    }

}
