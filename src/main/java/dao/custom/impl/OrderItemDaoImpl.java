package dao.custom.impl;

import dao.custom.OrderItemDao;
import dao.util.HibernateUtil;
import entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDaoImpl implements OrderItemDao {
    @Override
    public boolean save(OrderItem entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<OrderItem> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(OrderItem entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderItem getById(Long id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
