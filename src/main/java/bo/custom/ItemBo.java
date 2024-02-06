package bo.custom;

import dto.ItemDto;
import entity.util.ItemType;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo {
    List<ItemDto> allItems() throws SQLException, ClassNotFoundException;
    List<ItemDto> allItemsByType(ItemType type) throws SQLException, ClassNotFoundException;
}
