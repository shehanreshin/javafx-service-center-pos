package db;

import dto.ItemDto;

import java.util.ArrayList;
import java.util.List;

public class CurrentOrder {
    private static CurrentOrder currentOrder;
    private List<ItemDto> itemsInOrder;
    private CurrentOrder() {
        itemsInOrder = new ArrayList<>();
    }

    public static CurrentOrder getInstance() {
        return currentOrder == null ? (currentOrder = new CurrentOrder()) : currentOrder;
    }

    public List<ItemDto> getCurrentOrder() {
        return itemsInOrder;
    }
}
