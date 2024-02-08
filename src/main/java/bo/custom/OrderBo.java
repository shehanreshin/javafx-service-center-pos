package bo.custom;

import dto.OrderDto;

import java.sql.SQLException;

public interface OrderBo {
    boolean saveOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException;
    Long saveOrderAndGetId(OrderDto orderDto) throws SQLException, ClassNotFoundException;
}
