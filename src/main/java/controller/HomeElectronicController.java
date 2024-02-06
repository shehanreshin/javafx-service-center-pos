package controller;

import bo.custom.ItemBo;
import bo.custom.impl.ItemBoImpl;
import com.jfoenix.controls.JFXButton;
import db.CurrentOrder;
import dto.ItemDto;
import entity.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeElectronicController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            itemList = itemBo.allItems();
        } catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Internal server error");
            alert.showAndWait();
            return;
        }
        addItemsToGrid();
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
        int columns = 0, rows = 1;
        orderGridPane.getChildren().clear();
        List<ItemDto> itemsInCurrentOrder = CurrentOrder.getInstance().getCurrentOrder();
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
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Internal server error");
            alert.showAndWait();
            return;
        }
    }
}
