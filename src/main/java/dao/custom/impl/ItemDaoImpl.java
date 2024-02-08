package dao.custom.impl;

import dao.custom.ItemDao;
import dao.util.HibernateUtil;
import entity.Item;
import entity.User;
import entity.util.ItemType;
import entity.util.ItemTypeConverter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        List<Item> itemList = session.createQuery("FROM Item").list();
        session.close();
        return itemList;
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Item getById(Long id) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Item WHERE id="+id);
        query.setMaxResults(1);
        Item item = (Item) query.uniqueResult();
        session.close();
        return item;
    }

    @Override
    public List<Item> getAllByType(ItemType type) {
        Session session = HibernateUtil.getSession();
        int typeInt = new ItemTypeConverter().convertToDatabaseColumn(type);
        List<Item> itemList = session.createQuery("FROM Item WHERE type="+typeInt).list();
        session.close();
        return itemList;
    }
}
