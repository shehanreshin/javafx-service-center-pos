package controller;

import bo.custom.ItemBo;
import bo.custom.impl.ItemBoImpl;
import dto.ItemDto;
import entity.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
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

    private List<ItemDto> itemList;

    private ItemBo itemBo = new ItemBoImpl();

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

        int columns = 0, rows = 1;
        try {
            for (ItemDto item : itemList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../view/item.fxml"));

                Pane itemPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(item);

                if (columns == 3) {
                    columns = 0;
                    rows++;
                }

                gridPane.add(itemPane, columns++, rows);
                GridPane.setMargin(itemPane, new Insets(8));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
