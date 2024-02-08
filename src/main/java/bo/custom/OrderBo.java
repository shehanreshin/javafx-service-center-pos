package bo.custom;

import dto.OrderDto;

public interface OrderBo {
    boolean saveOrder(OrderDto order);
}
