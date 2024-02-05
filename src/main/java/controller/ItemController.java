package controller;

import dto.ItemDto;
import entity.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemController {
    @FXML
    private ImageView imgItem;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblStartingPrice;


    public void setData(ItemDto item) {
        lblItemName.setText(item.getName());
        lblStartingPrice.setText("Rs. "+ (int) item.getStartingPrice());
        imgItem.setImage(new Image(getClass().getResourceAsStream(item.getImg())));
    }
}
