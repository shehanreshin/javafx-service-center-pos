package bo.custom.impl;

import bo.custom.ItemBo;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;
import dto.ItemDto;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {
    private ItemDao itemDao = new ItemDaoImpl();
    @Override
    public List<ItemDto> allItems() throws SQLException, ClassNotFoundException {
        List<Item> itemList = itemDao.getAll();
        List<ItemDto> itemDtoList = new ArrayList<>();
        for (Item item : itemList) {
            itemDtoList.add(new ItemDto(
                    item.getId(),
                    item.getType(),
                    item.getName(),
                    item.getStartingPrice(),
                    item.getImg()
            ));
        }
        return itemDtoList;
    }
}
