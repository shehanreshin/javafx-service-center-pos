package dao.custom.impl;

import dao.custom.CustomerDao;
import dao.util.HibernateUtil;
import dto.CustomerDto;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    public Customer getByContactNumber(String contactNumber) {
        Session session = HibernateUtil.getSession();
        List<Customer> customerList = session.createQuery("FROM Customer WHERE contactNumber="+contactNumber).list();
        session.close();
        return customerList.isEmpty() ? null : customerList.get(0);
    }
}
