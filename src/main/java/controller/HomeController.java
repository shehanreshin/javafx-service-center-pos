package controller;

import bo.custom.ItemBo;
import bo.custom.impl.ItemBoImpl;
import com.jfoenix.controls.JFXButton;
import db.CurrentOrder;
import dto.ItemDto;
import entity.util.ItemType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private AnchorPane pane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private ScrollPane orderScrollPane;

    @FXML
    private GridPane orderGridPane;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnCancelOrder;

    @FXML
    private Button btnElectronic;

    @FXML
    private Button btnElectrical;

    @FXML
    private Label lblNoOfItems;

    @FXML
    private Label lblBasicCost;

    @FXML
    private Label lblAdditionalCost;

    @FXML
    private Label lblTotal;

    private List<ItemDto> itemList;

    private ItemBo itemBo = new ItemBoImpl();

    private List<ItemDto> currentOrder = new ArrayList<>();

    private double x, y;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ItemType itemType;
        itemType = url.toString().endsWith("home-electronic.fxml") ? ItemType.ELECTRONIC : ItemType.ELECTRICAL;
        try {
            itemList = itemBo.allItemsByType(itemType);
        } catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Internal server error");
            alert.showAndWait();
            return;
        }
        addItemsToGrid();
        updateCurrentOrdersDisplay();
    }

    private void addItemsToGrid() {
        int columns = 0, rows = 1;
        gridPane.getChildren().clear();
        try {
            for (ItemDto item : itemList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../view/item.fxml"));

                Pane itemPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(this, item);

                if (columns == 3) {
                    columns = 0;
                    rows++;
                }

                gridPane.add(itemPane, columns++, rows);
                GridPane.setMargin(itemPane, new Insets(8));
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Internal server error");
            alert.showAndWait();
            return;
        }
    }

    public void updateCurrentOrdersDisplay() {
        orderGridPane.getChildren().clear();
        List<ItemDto> itemsInCurrentOrder = CurrentOrder.getInstance().getCurrentOrder();
        displayItemsToCurrentOrder(itemsInCurrentOrder);
        displayOrderInfo(itemsInCurrentOrder);
    }

    private void displayItemsToCurrentOrder(List<ItemDto> itemsInCurrentOrder) {
        int columns = 0, rows = 1;
        try {
            for (ItemDto item : itemsInCurrentOrder) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../view/order-item.fxml"));

                Pane currentOrderPane = fxmlLoader.load();

                CurrentOrderItemController currentOrderItemController = fxmlLoader.getController();
                currentOrderItemController.setData(this, item);

                if (columns == 1) {
                    columns = 0;
                    rows++;
                }

                orderGridPane.add(currentOrderPane, columns++, rows);
                GridPane.setMargin(currentOrderPane, new Insets(2));
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Internal server error");
            alert.showAndWait();
            return;
        }
    }

    private void displayOrderInfo(List<ItemDto> itemsInCurrentOrder) {
        int noOfItems = 0;
        double basicCost = 0, additionalCost = 0;
        for (ItemDto itemDto : itemsInCurrentOrder) {
            noOfItems++;
            basicCost += itemDto.getStartingPrice();
        }
        lblNoOfItems.setText(String.format("%d Items", noOfItems));
        lblBasicCost.setText(String.format("Rs. %.1f", basicCost));
        lblAdditionalCost.setText(String.format("Rs. %.1f", additionalCost));
        lblTotal.setText(String.format("Rs. %.1f",(basicCost+additionalCost)));
    }

    public void electricalButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/home-electrical.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void electronicButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/home-electronic.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void placeOrderButtonOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Scene scene;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/place-order.fxml"));
        try {
            scene = new Scene(fxmlLoader.load());
            PlaceOrderController placeOrderController = fxmlLoader.getController();
            placeOrderController.setHomeController(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene.setFill(Color.TRANSPARENT);

        scene.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public void cancelOrderButtonOnAction(ActionEvent actionEvent) {
        CurrentOrder.getInstance().getCurrentOrder().clear();
        updateCurrentOrdersDisplay();
    }

    public void usersButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/user-management.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }
}
