package dao.custom.impl;

import dao.custom.CustomerDao;
import dao.util.HibernateUtil;
import dto.CustomerDto;
import entity.Customer;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        List<Customer> customerList = session.createQuery("FROM Customer").list();
        session.close();
        return customerList;
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Customer getById(Long id) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Customer WHERE id="+id);
        query.setMaxResults(1);
        Customer customer = (Customer) query.uniqueResult();
        session.close();
        return customer;
    }

    @Override
    public Customer getByContactNumber(String contactNumber) {
        Session session = HibernateUtil.getSession();
        Query query = session.createNativeQuery("SELECT * FROM Customer where contactNumber = ?", Customer.class);
        // Had to use native query here because of a bug with hibernate queries
        query.setParameter(1, contactNumber);
        query.setMaxResults(1);
        Customer customer = (Customer) query.uniqueResult();
        session.close();
        return customer;
    }
}
