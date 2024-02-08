package bo.custom.impl;

import bo.custom.OrderBo;
import dao.custom.*;
import dao.custom.impl.*;
import dao.util.HibernateUtil;
import dto.ItemDto;
import dto.OrderDto;
import entity.*;
import entity.util.ItemStatus;
import org.hibernate.Session;

import java.sql.SQLException;

public class OrderBoImpl implements OrderBo {
    private CustomerDao customerDao = new CustomerDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    private ItemDao itemDao = new ItemDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Override
    public boolean saveOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        Long orderId = saveOrderAndGetId(orderDto);
        Orders order = orderDao.getById(orderId);
        for (ItemDto itemDto : orderDto.getItems()) {
            Item item = itemDao.getById(itemDto.getId());
            boolean isOrderSaved = orderItemDao.save(new OrderItem(
                    order,
                    item,
                    ItemStatus.ORANGE
            ));
            if (!isOrderSaved) return false;
        }
        return true;
    }

    @Override
    public Long saveOrderAndGetId(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        Orders order = new Orders(
                customerDao.getById(orderDto.getCustomer().getId()),
                userDao.getById(orderDto.getUser().getUserId()),
                orderDto.getDescription(),
                orderDto.getDate(),
                orderDto.getBasicCost(),
                orderDto.getAdditionalCost()
        );
        return orderDao.saveAndGetId(order);
    }
}
