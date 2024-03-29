package controller;

import bo.custom.OrderBo;
import bo.custom.impl.OrderBoImpl;
import bo.util.ApplicationState;
import db.CurrentOrder;
import dto.CustomerDto;
import dto.ItemDto;
import dto.OrderDto;
import entity.Customer;
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
import java.sql.Date;
import java.sql.SQLException;
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

    private double total = 0;
    private CustomerDto selectedCustomer;
    private OrderBo orderBo = new OrderBoImpl();
    private HomeController homeController;

    public void closeButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }

    public void placeOrderButtonOnAction(ActionEvent actionEvent) {
        if (isAnyFieldEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
            return;
        }

        List<ItemDto> selectedItems = CurrentOrder.getInstance().getCurrentOrder();
        if (selectedItems.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Items Selected");
            alert.setContentText("Please select some items to add to the order");
            alert.showAndWait();
            return;
        }

        if(selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Customer not selected");
            alert.setContentText("Please select a customer");
            alert.showAndWait();
            return;
        }

        OrderDto order = new OrderDto(
                selectedCustomer,
                ApplicationState.getInstance().getLoggedInUser(),
                CurrentOrder.getInstance().getCurrentOrder(),
                txtDescription.getText(),
                new Date(System.currentTimeMillis()),
                total,
                0,
                total + 0
        );

        try {
            orderBo.saveOrder(order);
        } catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Internal server error");
            alert.showAndWait();
            return;
        }

        CurrentOrder.getInstance().getCurrentOrder().clear();
        homeController.updateCurrentOrdersDisplay();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Successfully Added");
        alert.setContentText("The order was added successfully");
        alert.showAndWait();

        ((Stage) pane.getScene().getWindow()).close();
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
        selectedCustomer = customer;
        txtCustomerContact.setText(selectedCustomer.getContactNumber());
        txtCustomerName.setText(selectedCustomer.getName());
        txtCustomerEmail.setText(selectedCustomer.getEmail());
    }

    private boolean isAnyFieldEmpty() {
        return txtCustomerContact.getText().isEmpty() || txtCustomerEmail.getText().isEmpty() ||
                txtCustomerName.getText().isEmpty() || txtDescription.getText().isEmpty();
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
