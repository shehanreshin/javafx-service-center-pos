package bo.custom;

import dto.ItemDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo {
    List<ItemDto> allItems() throws SQLException, ClassNotFoundException;
}
