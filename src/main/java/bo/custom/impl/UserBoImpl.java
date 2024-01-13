package bo.custom.impl;

import bo.custom.UserBo;
import dao.custom.UserDao;
import dao.custom.impl.UserDaoImpl;
import dto.UserDto;
import entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserBoImpl implements UserBo {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException {
        return userDao.save(
                new User(
                        dto.getUserId(),
                        dto.getName(),
                        dto.getEmail(),
                        dto.getPassword(),
                        dto.getRole()
                )
        );
    }

    @Override
    public List<UserDto> allUsers() {
        return null;
    }

    @Override
    public boolean updateUser(UserDto dto) {
        return false;
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }

    @Override
    public UserDto searchUser(String id) {
        return null;
    }

    @Override
    public UserDto searchUserByEmail(String email) throws SQLException, ClassNotFoundException {
        List<User> userList = userDao.getAll();
        User selectedUser = null;
        for (User user : userList) {
            if (email.equals(user.getEmail())) {
                selectedUser = user;
                break;
            }
        }
        return selectedUser == null ? null : new UserDto(
                selectedUser.getUserId(),
                selectedUser.getName(),
                selectedUser.getEmail(),
                selectedUser.getPassword(),
                selectedUser.getRole()
        );
    }
}
