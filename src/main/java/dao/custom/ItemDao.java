package dao.custom;

import dao.CrudDao;
import entity.Item;
import entity.util.ItemType;

import java.util.List;

public interface ItemDao extends CrudDao<Item> {
    List<Item> getAllByType(ItemType type);
}
