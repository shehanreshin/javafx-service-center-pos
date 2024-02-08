package dao.custom.impl;

import dao.custom.OrderDao;
import dao.util.HibernateUtil;
import entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(Orders entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Orders> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }
}
