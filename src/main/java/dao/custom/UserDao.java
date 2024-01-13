package dao.custom;

import dao.CrudDao;
import dto.UserDto;
import entity.User;

import java.sql.SQLException;

public interface UserDao extends CrudDao<User> {
    User searchUser(String id);
}
