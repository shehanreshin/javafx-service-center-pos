package controller;

import db.CurrentOrder;
import dto.ItemDto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CurrentOrderItemController {
    @FXML
    private ImageView imgItem;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblItemBasicCost;

    @FXML
    private Label lblItemCollectionDate;

    @FXML
    private Button btnRemove;

    public void setData(HomeController homeController, ItemDto item) {
        lblItemName.setText(item.getName());
        lblItemBasicCost.setText("Rs. "+ (int) item.getStartingPrice());
        imgItem.setImage(new Image(getClass().getResourceAsStream(item.getImg())));
        lblItemCollectionDate.setText("Collect on : "+item.getCollectionDate());

        btnRemove.setOnAction(event -> {
            CurrentOrder.getInstance().getCurrentOrder().remove(item);
            homeController.updateCurrentOrdersDisplay();
        });
    }
}
