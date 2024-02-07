package controller;

import db.CurrentOrder;
import dto.CustomerDto;
import dto.ItemDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceOrderController implements Initializable {
    @FXML
    private AnchorPane pane;

    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private Label lblOrderId;

    @FXML
    private TextField txtCustomerContact;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerEmail;

    @FXML
    private TextField txtDescription;

    @FXML
    private TableView<ItemDto> tblItems;

    @FXML
    private TableColumn colItem;

    @FXML
    private TableColumn colBasicCost;

    @FXML
    private Label lblTotal;

    @FXML
    private Button btnClose;

    public void closeButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }

    public void placeOrderButtonOnAction(ActionEvent actionEvent) {
    }

    public void addCustomerButtonOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/input-customer-number.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputCustomerNumberController inputCustomerNumberController = fxmlLoader.getController();
        inputCustomerNumberController.setPlaceOrderController(this);
        stage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<ItemDto> currentOrder = CurrentOrder.getInstance().getCurrentOrder();
        int total = 0;
        for (ItemDto itemDto : currentOrder) {
            total += itemDto.getStartingPrice();
        }
        lblTotal.setText(""+total);

        colItem.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBasicCost.setCellValueFactory(new PropertyValueFactory<>("startingPrice"));
        loadItemTable(currentOrder);
    }

    private void loadItemTable(List<ItemDto> currentOrder) {
        ObservableList<ItemDto> observableList = FXCollections.observableArrayList(currentOrder);
        tblItems.setItems(observableList);
    }

    public void setCustomerInfo(CustomerDto customer) {
        txtCustomerContact.setText(customer.getContactNumber());
        txtCustomerName.setText(customer.getName());
        txtCustomerEmail.setText(customer.getEmail());
    }
}
