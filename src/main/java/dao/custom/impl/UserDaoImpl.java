package dao.custom.impl;

import dao.custom.UserDao;
import dao.util.HibernateUtil;
import dto.UserDto;
import entity.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        List<User> userList = session.createQuery("FROM User").list();
        session.close();
        return userList;
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User searchUser(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.byId(id);
        transaction.commit();
        session.close();
        return user;
    }
}