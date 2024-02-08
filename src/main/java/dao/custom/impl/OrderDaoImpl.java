package dao.custom.impl;

import dao.custom.OrderDao;
import dao.util.HibernateUtil;
import entity.Customer;
import entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    @Override
    public Long saveAndGetId(Orders entity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public Orders getById(Long id) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Orders WHERE id="+id);
        query.setMaxResults(1);
        Orders order = (Orders) query.uniqueResult();
        session.close();
        return order;
    }
}
