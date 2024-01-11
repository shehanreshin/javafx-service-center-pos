package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController {
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

    @FXML
    void forgotPasswordLabelOnClick(MouseEvent event) {

    }

    @FXML
    void loginButtonOnAction(ActionEvent event) {
        boolean isEmailValid = isEmailValid();
    }

    private boolean isEmailValid() {
        if (isEmailEmpty()) {
            lblEmailError.setVisible(true);
            return false;
        }
        lblEmailError.setVisible(false);
        return true;
    }

    private boolean isEmailEmpty() {
        return txtEmail.getText().isEmpty();
    }
}
