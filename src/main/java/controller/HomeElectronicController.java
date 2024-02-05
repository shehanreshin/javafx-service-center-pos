package controller;

import entity.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
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

    private List<Item> itemList;

    private List<Item> getData() {
        List<Item> items = new ArrayList<>();
        for (long i = 0; i < 8; i++) {
            Item item = new Item();
            item.setId(i);
            item.setName("Smart TV");
            item.setStartingPrice(5700.00);
            item.setImg("../img/dashboard/smartphone.png");
            items.add(item);
        }
        return items;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemList = new ArrayList<>(getData());
        int columns = 0, rows = 1;
        try {
            for (Item item : itemList) {
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
