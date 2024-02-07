package controller;

import dto.ItemDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
