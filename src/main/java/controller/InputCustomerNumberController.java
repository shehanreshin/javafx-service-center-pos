package controller;

import bo.custom.CustomerBo;
import bo.custom.impl.CustomerBoImpl;
import dto.CustomerDto;
import entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InputCustomerNumberController {
    @FXML
    private TextField txtContact;

    @FXML
    private Button btnSelect;

    @FXML
    private AnchorPane pane;

    private CustomerBo customerBo = new CustomerBoImpl();
    private PlaceOrderController placeOrderController;

    public void selectButtonOnAction(ActionEvent actionEvent) {
        if (txtContact.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Contact Number Field Is Empty");
            alert.setContentText("Please input the contact number of the customer you want to select");
            alert.showAndWait();
            return;
        }

        CustomerDto customer = customerBo.getCustomerByContactNumber(txtContact.getText());

        if (customer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Non Existing Customer");
            alert.setContentText("This customer does not exist in the database. Please add them before selecting");
            alert.showAndWait();
            return;
        }

        placeOrderController.setCustomerInfo(customer);
        ((Stage) pane.getScene().getWindow()).close();
    }

    public void setPlaceOrderController(PlaceOrderController placeOrderController) {
        this.placeOrderController = placeOrderController;
    }
}
