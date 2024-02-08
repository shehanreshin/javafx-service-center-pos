package dao.custom;

import dao.CrudDao;
import entity.Orders;

public interface OrderDao extends CrudDao<Orders> {
    Long saveAndGetId(Orders entity);
}
